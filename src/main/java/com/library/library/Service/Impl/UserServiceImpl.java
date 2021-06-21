package com.library.library.Service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.library.library.DTO.AuthorDTO;
import com.library.library.DTO.BookDTO;
import com.library.library.DTO.CategoryDTO;
import com.library.library.DTO.UserDTO;
import com.library.library.DTO.User_BookDTO;
import com.library.library.Entity.Author_Book;
import com.library.library.Entity.Category;
import com.library.library.Entity.User;
import com.library.library.Entity.User_Book;
import com.library.library.Repository.Author_Book_Repository;
import com.library.library.Repository.CategoryRepository;
import com.library.library.Repository.UserRepository;
import com.library.library.Repository.User_Book_Repository;
import com.library.library.Service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final User_Book_Repository user_book_repository;

	private final CategoryRepository categoryRepository;

	private final Author_Book_Repository author_book_repository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, User_Book_Repository user_book_repository,
			CategoryRepository categoryRepository, Author_Book_Repository author_book_repository) {
		this.userRepository = userRepository;
		this.user_book_repository = user_book_repository;
		this.categoryRepository = categoryRepository;
		this.author_book_repository = author_book_repository;
	}

	@Override
	public ResponseEntity getAllUser() {
		List<User> list = userRepository.findAll();
		List<UserDTO> uList = new ArrayList<>();
		
		if (list != null && list.size() > 0) {
			for (User l : list) {
				UserDTO uDTO = new UserDTO();
				uDTO.setId(l.getId());
				uDTO.setFirstName(l.getFirstName());
				uDTO.setLastName(l.getLastName());
				
				uList.add(uDTO);
			}
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@Override
	public ResponseEntity getAllUserBook() {
		List<User> list = userRepository.findAll();
		Set<UserDTO> uDTOList = new HashSet();
		if (list != null && list.size() > 0) {

			for (User l : list) {
				UserDTO uDTO = new UserDTO();
				uDTO.setId(l.getId());
				uDTO.setFirstName(l.getFirstName());
				uDTO.setLastName(l.getLastName());
				uDTOList.add(uDTO);

				List<User_Book> ubList = user_book_repository.findAllByBookId(l.getId());
				List<BookDTO> bkList = new ArrayList<>();
				
				for (User_Book ub : ubList) {
					BookDTO bkDTO = new BookDTO();
					bkDTO.setId(ub.getBookId().getId());
					bkDTO.setName(ub.getBookId().getName());

					List<Category> ntsLists = categoryRepository
							.findAllByBookId(ub.getBookId().getCategory().getId());
					List<CategoryDTO> notList = new ArrayList<>();

					for (Category n : ntsLists) {
						CategoryDTO notDTO = new CategoryDTO();
						notDTO.setId(n.getId());
						notDTO.setName(n.getName());
						notList.add(notDTO);
					}

					List<User_Book> userbookList = user_book_repository.findAllDate(ub.getId());
					List<User_BookDTO> ubDTOList = new ArrayList<>();

					for (User_Book u : userbookList) {
						User_BookDTO ubDTO = new User_BookDTO();
						ubDTO.setId(u.getId());
						ubDTO.setStartDate(u.getStartDate());
						ubDTO.setEndDate(u.getEndDate());
						ubDTOList.add(ubDTO);
					}

					List<Author_Book> authorList = author_book_repository.findAllByAuthorId(ub.getBookId().getId());
					List<AuthorDTO> autList = new ArrayList<>();

					for (Author_Book a : authorList) {
						AuthorDTO authorDTO = new AuthorDTO();
						authorDTO.setId(a.getAuthor_id().getId());
						authorDTO.setName(a.getAuthor_id().getName());
						authorDTO.setSurname(a.getAuthor_id().getSurname());
						autList.add(authorDTO);
					}

					bkDTO.setDate(ubDTOList);
					bkDTO.setAuthors(autList);
					bkDTO.setCategories(notList);
					bkList.add(bkDTO);
				}

				uDTO.setBooks(bkList);
				uDTOList.add(uDTO);
			}

			return ResponseEntity.ok(uDTOList);

		} else {
			return ResponseEntity.noContent().build();
		}

	}

	@Override
	public User getById(Integer id) {
		return userRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<User> addUser(UserDTO userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}

	@Override
	public ResponseEntity<User> delete(Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.noContent().build();
		}

	}

	@Override
	public ResponseEntity<User> updateUser(Integer id, UserDTO userDTO) {
		User user = userRepository.getById(id);
		if (user != null) {
			if (userDTO.getFirstName() != null) {
				user.setFirstName(userDTO.getFirstName());
			}
			if (userDTO.getLastName() != null) {
				user.setLastName(userDTO.getLastName());
			}
			

		} else {
			return ResponseEntity.noContent().build();
		}
			userRepository.save(user);
		    return ResponseEntity.ok(user);
		
	}

	@Override
	public ResponseEntity getUserBookDetail(Integer id) {
		User user = userRepository.getAuthorDetail(id);
		List<UserDTO> uList = new ArrayList<>();

		if (user == null) {
			return ResponseEntity.noContent().build();
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());

		List<User_Book> ubList = user_book_repository.findAllByBookId(user.getId());
		List<BookDTO> bkList = new ArrayList<>();
		
		for (User_Book ub : ubList) {
			BookDTO bkDTO = new BookDTO();
			bkDTO.setId(ub.getBookId().getId());
			bkDTO.setName(ub.getBookId().getName());

			List<Category> ntsLists = categoryRepository.findAllByBookId(ub.getBookId().getCategory().getId());
			List<CategoryDTO> notList = new ArrayList<>();

			for (Category n : ntsLists) {
				CategoryDTO notDTO = new CategoryDTO();
				notDTO.setId(n.getId());
				notDTO.setName(n.getName());
				notList.add(notDTO);
			}

			List<User_Book> userbookList = user_book_repository.findAllDate(ub.getId());
			List<User_BookDTO> ubDTOList = new ArrayList<>();

			for (User_Book u : userbookList) {
				User_BookDTO ubDTO = new User_BookDTO();
				ubDTO.setId(u.getId());
				ubDTO.setStartDate(u.getStartDate());
				ubDTO.setEndDate(u.getEndDate());
				ubDTOList.add(ubDTO);
			}

			List<Author_Book> authorList = author_book_repository.findAllByAuthorId(ub.getBookId().getId());
			List<AuthorDTO> autList = new ArrayList<>();

			for (Author_Book a : authorList) {
				AuthorDTO authorDTO = new AuthorDTO();
				authorDTO.setId(a.getAuthor_id().getId());
				authorDTO.setName(a.getAuthor_id().getName());
				authorDTO.setSurname(a.getAuthor_id().getSurname());
				autList.add(authorDTO);
			}

			bkDTO.setDate(ubDTOList);
			bkDTO.setAuthors(autList);
			bkDTO.setCategories(notList);
			bkList.add(bkDTO);
		}
		userDTO.setBooks(bkList);
		uList.add(userDTO);
		return ResponseEntity.ok(uList);
	}

	@Override
	public ResponseEntity<User> getUserDetail(Integer id) {
		User user = userRepository.getAuthorDetail(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

}

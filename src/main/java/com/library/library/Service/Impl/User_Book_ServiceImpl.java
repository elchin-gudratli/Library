package com.library.library.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.library.library.DTO.BookDTO;
import com.library.library.DTO.UserDTO;
import com.library.library.DTO.User_BookDTO;
import com.library.library.Entity.Book;
import com.library.library.Entity.User;
import com.library.library.Entity.User_Book;
import com.library.library.Repository.BookRepository;
import com.library.library.Repository.UserRepository;
import com.library.library.Repository.User_Book_Repository;
import com.library.library.Service.User_Book_Service;

@Service
@Transactional
public class User_Book_ServiceImpl implements User_Book_Service{

	private final User_Book_Repository user_book_Repository;
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public User_Book_ServiceImpl(User_Book_Repository user_book_Repository,BookRepository bookRepository, UserRepository userRepository) {
		this.user_book_Repository = user_book_Repository;
		this.bookRepository=bookRepository;
		this.userRepository=userRepository;
	}

	@Override
	public ResponseEntity getAllUserBook() {
		List<User_Book> list = user_book_Repository.findAll();
		if (list != null && list.size() > 0) {
			for (User_Book l : list) {
				List<User> userList = userRepository.findAllByUserId(l.getUserId().getId());
				List<UserDTO> uList = new ArrayList<>();
				for (User u : userList) {
					UserDTO uDTO = new UserDTO();
					uDTO.setId(u.getId());
					uDTO.setFirstName(u.getFirstName());
					uDTO.setLastName(u.getLastName());
					uList.add(uDTO);
					List<Book> bookList = bookRepository.findAllByBookId(l.getBookId().getId());
					List<BookDTO> bList = new ArrayList<>();
					for (Book b : bookList) {
						BookDTO bDTO = new BookDTO();
						bDTO.setId(b.getId());
						bDTO.setName(b.getName());
						bList.add(bDTO);
					}
					uDTO.setBooks(bList);
				}
			}
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@Override
	public User_Book getById(Integer id) {
		return user_book_Repository.findById(id).get();
	}

	@Override
	public ResponseEntity<User_Book> addUserBook(User_BookDTO user_bookDTO) {
		User_Book userbook = new User_Book();
		userbook.setId(user_bookDTO.getId());
		userbook.setStartDate(user_bookDTO.getStartDate());
		userbook.setEndDate(user_bookDTO.getEndDate());
		user_book_Repository.save(userbook);
		List<UserDTO> userDTO = user_bookDTO.getUsers();
		for (UserDTO u : userDTO) {
			User users = userRepository.getById(u.getId());
			userbook.setUserId(users);
			userRepository.save(users);
		}
		List<BookDTO> bookDTO = user_bookDTO.getBooks();
		for (BookDTO b : bookDTO) {
			Book books = bookRepository.getById(b.getId());
			userbook.setBookId(books);
			bookRepository.save(books);
		}
		return ResponseEntity.ok(userbook);
	}

	@Override
	public ResponseEntity<User_Book> delete(Integer id) {
		Optional<User_Book> user = user_book_Repository.findById(id);
		if (user.isPresent()) {
			user_book_Repository.deleteById(id);
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@Override
	public ResponseEntity<User_Book> updateUserBook(Integer id, User_BookDTO user_bookDTO) {
		User_Book user_book = user_book_Repository.getById(id);
		if (user_book != null) {
			if (user_bookDTO.getStartDate() != null) {
				user_book.setStartDate(user_bookDTO.getStartDate());
			}
			if (user_bookDTO.getEndDate() != null) {
				user_book.setEndDate(user_bookDTO.getEndDate());
			}
		} else {
			return ResponseEntity.noContent().build();
		}
		user_book_Repository.save(user_book);
		return ResponseEntity.ok(user_book);
	}
}

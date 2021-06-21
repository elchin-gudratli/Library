package com.library.library.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.library.library.DTO.AuthorDTO;
import com.library.library.DTO.BookDTO;
import com.library.library.DTO.CategoryDTO;
import com.library.library.Entity.Author;
import com.library.library.Entity.Author_Book;
import com.library.library.Entity.Category;
import com.library.library.Repository.AuthorRepository;
import com.library.library.Repository.Author_Book_Repository;
import com.library.library.Repository.CategoryRepository;
import com.library.library.Service.AuthorService;


@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
	
	private final AuthorRepository authorRepository;
	
	private final Author_Book_Repository author_book_repository;
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public AuthorServiceImpl(AuthorRepository authorRepository, Author_Book_Repository author_book_repository,CategoryRepository categoryRepository) {
		this.authorRepository = authorRepository;
		this.author_book_repository=author_book_repository;
		this.categoryRepository=categoryRepository;
	}

	@Override
	public ResponseEntity<List<Author>> getAllAuthor() {
		List<Author> list=authorRepository.findAll();
		
		if(list != null && list.size()>0) {
			return ResponseEntity.ok(list);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
	}

	@Override
	public ResponseEntity getAllAuthorBook() {
		List<Author> list = authorRepository.findAll();
		List<AuthorDTO> aList = new ArrayList<>();

		if (list != null && list.size() > 0) {
			for (Author l : list) {
				AuthorDTO authorDTO = new AuthorDTO();
				authorDTO.setId(l.getId());
				authorDTO.setName(l.getName());
				authorDTO.setSurname(l.getSurname());
				List<Author_Book> authorList = author_book_repository.findAllByBookId(l.getId());
				List<BookDTO> bDTOList = new ArrayList<>();
				for (Author_Book b : authorList) {
					BookDTO bDTO = new BookDTO();
					bDTO.setId(b.getBook_id().getId());
					bDTO.setName(b.getBook_id().getName());

					List<Category> ctList = categoryRepository.findAllByBookId(b.getBook_id().getCategory().getId());
					List<CategoryDTO> ctgList = new ArrayList();
					for (Category c : ctList) {
						CategoryDTO ctgDTO = new CategoryDTO();
						ctgDTO.setId(c.getId());
						ctgDTO.setName(c.getName());
						ctgList.add(ctgDTO);
					}
					bDTO.setCategories(ctgList);
					bDTOList.add(bDTO);
				}
				authorDTO.setBooks(bDTOList);
				aList.add(authorDTO);
			}
			return ResponseEntity.ok(aList);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

	}

	@Override
	public ResponseEntity<Author> addAuthor(AuthorDTO authorDTO) {
		Author author = new Author();
		author.setName(authorDTO.getName());
		author.setSurname(authorDTO.getSurname());
		authorRepository.save(author);

		return ResponseEntity.ok(author);
	}

	@Override
	public Author getById(Integer id) {
		return authorRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<Author> updateAuthor(Integer id, AuthorDTO authorDTO) {
		Author author = authorRepository.getById(id);
		if (author != null) {
			if (authorDTO.getName() != null) {
				author.setName(authorDTO.getName());
			}
			if (authorDTO.getSurname() != null) {
				author.setSurname(authorDTO.getSurname());
			}

		} else {
			return ResponseEntity.noContent().build();
		}
		authorRepository.save(author);
		return ResponseEntity.ok(author);
	}



	@Override
	public ResponseEntity<Author> getAuthorDetail(Integer id) {
		Author author = authorRepository.getAuthorDetail(id);
		if (author != null) {
			return ResponseEntity.ok(author);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity getAuthorBooksDetail(Integer id) {
		Author author = authorRepository.getAuthorDetail(id);
		List<AuthorDTO> aList = new ArrayList<>();
		if(author != null){
			AuthorDTO aDTO = new AuthorDTO();
			aDTO.setId(author.getId());
			aDTO.setName(author.getName());
			aDTO.setSurname(author.getSurname());
			
			List<Author_Book> authorList = author_book_repository.findAllByBookId(author.getId());
			List<BookDTO> bDTOList = new ArrayList<>();
			for(Author_Book b : authorList) {
				BookDTO bDTO=new BookDTO();
				bDTO.setId(b.getBook_id().getId());
				bDTO.setName(b.getBook_id().getName());
				
				List<Category> ctList = categoryRepository.findAllByBookId(b.getBook_id().getCategory().getId());
				List<CategoryDTO> ctgList = new ArrayList();
				for (Category c : ctList) {
					CategoryDTO ctgDTO = new CategoryDTO();
					ctgDTO.setId(c.getId());
					ctgDTO.setName(c.getName());
					ctgList.add(ctgDTO);

				}
				bDTO.setCategories(ctgList);
				bDTOList.add(bDTO);
			}
			aDTO.setBooks(bDTOList);
			aList.add(aDTO);
			return ResponseEntity.ok(aList);
			}else {
				return ResponseEntity.noContent().build();
			}
			
		}
		
		@Override
		public ResponseEntity<Author> delete(Integer id) {
			Optional<Author> authorOptional = authorRepository.findById(id);
			if (authorOptional.isPresent()) {
				authorRepository.deleteById(id);
				return ResponseEntity.ok(authorOptional.get());
			}
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		@Override
		public ResponseEntity<Author> findByName(String name) {
			Author author = authorRepository.findByName(name);
			if (author != null) {
				return ResponseEntity.ok(author);
			}
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		@Override
		public ResponseEntity getAllAuthor(int pageNo, int pageSize) {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			return ResponseEntity.ok(authorRepository.findAll(pageable).getContent());
		}


}

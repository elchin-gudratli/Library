package com.library.library.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.library.library.Entity.Book;
import com.library.library.Entity.Category;
import com.library.library.Repository.AuthorRepository;
import com.library.library.Repository.Author_Book_Repository;
import com.library.library.Repository.BookRepository;
import com.library.library.Repository.CategoryRepository;
import com.library.library.Service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;
	
	private final AuthorRepository authorRepository;
	
	private final CategoryRepository categoryRepository;
	
	private final Author_Book_Repository author_book_repository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, Author_Book_Repository author_book_repository,AuthorRepository authorRepository) {
		this.authorRepository=authorRepository;
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
		this.author_book_repository=author_book_repository;
	}

	@Override
	public ResponseEntity getAllBook(String name) {
		List<Book> list = bookRepository.getAll(name);
		List<BookDTO> bksList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (Book l : list) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setId(l.getId());
				bookDTO.setName(l.getName());
				List<Category> ntsLists = categoryRepository.findAllByBookId(l.getCategory().getId());
				List<CategoryDTO> notList = new ArrayList<>();
				for (Category n : ntsLists) {
					CategoryDTO notDTO = new CategoryDTO();
					notDTO.setId(n.getId());
					notDTO.setName(n.getName());
					notList.add(notDTO);
				}
				List<Author_Book> authorList = author_book_repository.findAllByAuthorId(l.getId());
				List<AuthorDTO> autList = new ArrayList<>();
				for (Author_Book a : authorList) {
					AuthorDTO authorDTO = new AuthorDTO();
					authorDTO.setId(a.getAuthor_id().getId());
					authorDTO.setName(a.getAuthor_id().getName());
					authorDTO.setSurname(a.getAuthor_id().getSurname());
					autList.add(authorDTO);
				}
				bookDTO.setAuthors(autList);
				bookDTO.setCategories(notList);
				bksList.add(bookDTO);
			}
			return ResponseEntity.ok(bksList);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity getAllBook(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<Book> list = bookRepository.findAll(pageable).getContent();
		Long totalCount = bookRepository.totalCount();
		Map mapBook = new HashMap<>();
		List<BookDTO> bksList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (Book l : list) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setId(l.getId());
				bookDTO.setName(l.getName());
				List<Category> ntsLists = categoryRepository.findAllByBookId(l.getCategory().getId());
				List<CategoryDTO> notList = new ArrayList<>();
				for (Category n : ntsLists) {
					CategoryDTO notDTO = new CategoryDTO();
					notDTO.setId(n.getId());
					notDTO.setName(n.getName());
					notList.add(notDTO);
				}
				List<Author_Book> authorList = author_book_repository.findAllByAuthorId(l.getId());
				List<AuthorDTO> autList = new ArrayList<>();
				for (Author_Book a : authorList) {
					AuthorDTO authorDTO = new AuthorDTO();
					authorDTO.setId(a.getAuthor_id().getId());
					authorDTO.setName(a.getAuthor_id().getName());
					authorDTO.setSurname(a.getAuthor_id().getSurname());
					autList.add(authorDTO);
				}
				bookDTO.setAuthors(autList);
				bookDTO.setCategories(notList);
				bksList.add(bookDTO);
			}
			mapBook.put("list", bksList);
			mapBook.put("totalCount", totalCount);
		}
		return ResponseEntity.ok(mapBook);
	}

	@Override
	public Book getById(Integer id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<Book> addBook(BookDTO bookDTO) {

		Book book = new Book();
		book.setName(bookDTO.getName());
		bookRepository.save(book);
		List<CategoryDTO> categoryDTO = bookDTO.getCategories();
		for (CategoryDTO n : categoryDTO) {
			Category categories = categoryRepository.getById(n.getId());
			book.setCategory(categories);
			categoryRepository.save(categories);
		}
		List<AuthorDTO> authorDTO = bookDTO.getAuthors();
		for (AuthorDTO a : authorDTO) {
			Author author = authorRepository.getById(a.getId());
			Author_Book author_book = new Author_Book();
			author_book.setBook_id(book);
			author_book.setAuthor_id(author);
			author_book_repository.save(author_book);
		}
		return ResponseEntity.ok(book);
	}

	@Override
	public ResponseEntity<Book> delete(Integer id) {
		Optional<Book> authorOptional = bookRepository.findById(id);
		if (authorOptional.isPresent()) {
			bookRepository.deleteById(id);
			return ResponseEntity.ok(authorOptional.get());
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Book> updateBook(Integer id, BookDTO bookDTO) {
		Book book = bookRepository.getById(id);
		if (book != null) {
			book.setName(bookDTO.getName());
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		bookRepository.save(book);
		List<CategoryDTO> categoryDTO = bookDTO.getCategories();
		if (categoryDTO != null) {
			for (CategoryDTO c : categoryDTO) {
				Optional<Category> categoryOptional = categoryRepository.findById(c.getId());
				if (categoryOptional.isPresent()) {
					categoryOptional.get().setId(book.getCategory().getId());
					categoryOptional.get().setName(c.getName());
				} else {
					return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
				}
			}
		}
		return ResponseEntity.ok(book);
	}

	@Override
	public ResponseEntity getBookDetail(Integer id) {
		Book book = bookRepository.getBookDetail(id);
		List<BookDTO> bkList = new ArrayList<>();
		if (book == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setName(book.getName());
		List<Category> ctList = categoryRepository.findAllByBookId(book.getCategory().getId());
		List<CategoryDTO> ctgList = new ArrayList<>();
		for (Category c : ctList) {
			CategoryDTO ctgDTO = new CategoryDTO();
			ctgDTO.setId(c.getId());
			ctgDTO.setName(c.getName());
			ctgList.add(ctgDTO);
		}
		List<Author_Book> authorList = author_book_repository.findAllByAuthorId(book.getId());
		List<AuthorDTO> autList = new ArrayList<>();
		for (Author_Book a : authorList) {
			AuthorDTO authorDTO = new AuthorDTO();
			authorDTO.setId(a.getAuthor_id().getId());
			authorDTO.setName(a.getAuthor_id().getName());
			authorDTO.setSurname(a.getAuthor_id().getSurname());
			autList.add(authorDTO);
		}
		bookDTO.setAuthors(autList);
		bookDTO.setCategories(ctgList);
		bkList.add(bookDTO);
		return ResponseEntity.ok(bkList);
	}

	@Override
	public ResponseEntity<Book> findByName(String name) {
		Book book = bookRepository.findByName(name);
		if (book != null) {
			return ResponseEntity.ok(book);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity getAuthor(Integer id) {
		Author author = authorRepository.getById(id);
		if (author == null) {
			return ResponseEntity.noContent().build();
		}
		List<Book> bList = author_book_repository.findByAuthorId(id);
		List<BookDTO> bDTOList = new ArrayList<>();
		for (Book b : bList) {
			BookDTO bookDTO = new BookDTO();
			bookDTO.setId(b.getId());
			bookDTO.setName(b.getName());
			List<Category> ctList = categoryRepository.findAllByBookId(b.getCategory().getId());
			List<CategoryDTO> ctgList = new ArrayList<>();
			for (Category c : ctList) {
				CategoryDTO ctgDTO = new CategoryDTO();
				ctgDTO.setId(c.getId());
				ctgDTO.setName(c.getName());
				ctgList.add(ctgDTO);
			}
			bookDTO.setCategories(ctgList);
			bDTOList.add(bookDTO);
		}
		return ResponseEntity.ok(bDTOList);
	}
}

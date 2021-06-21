package com.library.library.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.library.library.DTO.BookDTO;
import com.library.library.Entity.Book;



public interface BookService {
	ResponseEntity<List<Book>> getAllBook(String name);
	
	ResponseEntity<List<Book>> getAuthor(Integer id);
	
	ResponseEntity getAllBook(int pageNo, int pageSize);
	
	Book getById(Integer id);
	
	ResponseEntity<Book> addBook(BookDTO bookDTO);
	
	ResponseEntity<Book> delete(Integer id);
	
	ResponseEntity<Book> updateBook(Integer id, BookDTO bookDTO);
	
	ResponseEntity<Book> getBookDetail(Integer id);
	
	ResponseEntity<Book> findByName(String name);
}

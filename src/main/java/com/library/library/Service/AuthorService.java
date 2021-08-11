package com.library.library.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.library.library.DTO.AuthorDTO;
import com.library.library.Entity.Author;

public interface AuthorService {
	
	ResponseEntity<List<Author>> getAllAuthor();
	
	ResponseEntity<List<Author>> getAllAuthorBook();
	
	ResponseEntity getAllAuthor(int pageNo, int pageSize);
	
	Author getById(Integer id);
	
	ResponseEntity<Author> addAuthor(AuthorDTO authorDTO);
	
	ResponseEntity<Author> delete(Integer id);
	
	ResponseEntity<Author> updateAuthor(Integer id,AuthorDTO authorDTO);
	
	ResponseEntity<Author> getAuthorDetail(Integer id);
	
	ResponseEntity<Author> getAuthorBooksDetail(Integer id);
	
	ResponseEntity<Author> findByName(String name);

	ResponseEntity getAllAuthorBook(int pageNo, int pageSize);

}

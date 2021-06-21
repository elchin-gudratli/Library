package com.library.library.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.library.library.DTO.User_BookDTO;
import com.library.library.Entity.User_Book;

public interface User_Book_Service {

	    ResponseEntity<List<User_Book>> getAllUserBook();
		
	    User_Book getById(Integer id);
		
		ResponseEntity<User_Book> addUserBook(User_BookDTO user_bookDTO);
		
		ResponseEntity<User_Book> delete(Integer id);
		
		ResponseEntity<User_Book> updateUserBook(Integer id, User_BookDTO user_bookDTO);
}

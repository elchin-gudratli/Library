package com.library.library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.library.DTO.User_BookDTO;
import com.library.library.Entity.User_Book;
import com.library.library.Service.User_Book_Service;

@RestController
@RequestMapping("api/userbook")
public class UserBookController {

	private final User_Book_Service user_book_Service;

	@Autowired
	public UserBookController(User_Book_Service user_book_Service) {
		this.user_book_Service = user_book_Service;
	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<User_Book>> getAllUser_Book(){
		return user_book_Service.getAllUserBook();
	}

	@PostMapping("/addUserBook")
	public ResponseEntity<User_Book> addUserBookPost(@RequestBody User_BookDTO user_bookDTO){
		return user_book_Service.addUserBook(user_bookDTO);
	}	
	@DeleteMapping("/deleteUserBook/{id}")
	public ResponseEntity<User_Book> deleteUserBook(@PathVariable Integer id){
		return user_book_Service.delete(id);
	}

	@PutMapping("/editUserBook/{id}")
	public ResponseEntity<User_Book> updateUserBook(@PathVariable Integer id, @RequestBody User_BookDTO user_bookDTO){
		return user_book_Service.updateUserBook(id, user_bookDTO);
	}
	
}

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

import com.library.library.DTO.UserDTO;
import com.library.library.Entity.Book;
import com.library.library.Entity.User;
import com.library.library.Service.UserService;



@RestController
@RequestMapping("api/user")
public class UserController {

	private final UserService userService;
    
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<User>> getAllUser(){
		return userService.getAllUser();
	}
	
	@GetMapping("getAllUserBook")
	public ResponseEntity<List<User>> getAllUserBook(){
		return userService.getAllUserBook();
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUserPost(@RequestBody UserDTO userDTO){
		return userService.addUser(userDTO);
	}
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id){
		return userService.delete(id);
	}
	@PutMapping("/editUser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO){
		return userService.updateUser(id, userDTO);
	}
	@GetMapping("/userProfile/{id}")
	public ResponseEntity<User> getProfileUser(@PathVariable Integer id){
		return userService.getUserDetail(id);
	}
	@GetMapping("/userbookProfile/{id}")
	public ResponseEntity<User> getProfileUserBook(@PathVariable Integer id){
		return userService.getUserBookDetail(id);
	}
	
	
	
}

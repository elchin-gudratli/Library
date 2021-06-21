package com.library.library.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.library.library.DTO.UserDTO;
import com.library.library.Entity.User;


public interface UserService {

	
	ResponseEntity<List<User>> getAllUserBook();
    
	ResponseEntity<List<User>> getAllUser();
	
    User getById(Integer id);
    
    ResponseEntity<User> getUserBookDetail(Integer id);
    
    ResponseEntity<User> getUserDetail(Integer id);
    
	ResponseEntity<User> addUser(UserDTO userDTO);
	
	ResponseEntity<User> delete(Integer id);
	
	ResponseEntity<User> updateUser(Integer id, UserDTO userDTO);
	
}

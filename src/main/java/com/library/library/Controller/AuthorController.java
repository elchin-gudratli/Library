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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DTO.AuthorDTO;
import com.library.library.Entity.Author;
import com.library.library.Service.AuthorService;

@RestController
@RequestMapping("api/author")
public class AuthorController {
	
	private final AuthorService authorService;
    
	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<Author>> getAllAuthor(){
		return authorService.getAllAuthor();
	}
	
	@GetMapping("getAllAuthorBook")
	public ResponseEntity<List<Author>> getAllAuthorBook(){
		return authorService.getAllAuthorBook();
	}
	@GetMapping("getAllPage")
	public ResponseEntity<List<Author>> getAllAuthor(@RequestParam int pageNo,@RequestParam int pageSize){
		return authorService.getAllAuthor(pageNo,pageSize);	
	}
	@GetMapping("getAuthorBooksPage")
	public ResponseEntity<List<Author>> getAllAuthorBook(@RequestParam int pageNo,@RequestParam int pageSize){
		return authorService.getAllAuthorBook(pageNo,pageSize);	
	}
	@GetMapping("/authorProfile/{id}")
	public ResponseEntity<Author> getProfileAuthor(@PathVariable Integer id){
		return authorService.getAuthorDetail(id);	
	}
	@GetMapping("/authorbooksProfile/{id}")
	public ResponseEntity<Author> getProfileAuthorBooks(@PathVariable Integer id){
		return authorService.getAuthorBooksDetail(id);
	}
	@GetMapping("/findByName")
	public ResponseEntity<Author> getAuthorName(@RequestParam String name){
		return authorService.findByName(name);
	}
	@PostMapping("/addAuthor")
	public ResponseEntity<Author> addAuthorPost(@RequestBody AuthorDTO authorDTO){
		return authorService.addAuthor(authorDTO);
	}
	@DeleteMapping("/deleteAuthor/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id){
		return authorService.delete(id);
	}
	@PutMapping("/editAuthor/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDTO authorDTO){
		return authorService.updateAuthor(id, authorDTO);
	}
	
}

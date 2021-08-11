package com.library.library.Controller;

import java.util.List;

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
import com.library.library.DTO.BookDTO;
import com.library.library.Entity.Book;
import com.library.library.Service.BookService;

@RestController
@RequestMapping("api/book")
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	@GetMapping("getAll")
	public ResponseEntity<List<Book>> getAllBook(@RequestParam(value="name", required = false, defaultValue = "") String name){
		return bookService.getAllBook(name);
	}
	@GetMapping("getAllPage")
	public ResponseEntity<List<Book>> getAllBook(@RequestParam int pageNo,@RequestParam int pageSize){
		return bookService.getAllBook(pageNo,pageSize);
	}
	@GetMapping("/bookProfile/{id}")
	public ResponseEntity<Book> getProfileBook(@PathVariable Integer id){
		return bookService.getBookDetail(id);
	}
	@GetMapping("/findByName")
	public ResponseEntity<Book> getBookName(@RequestParam String name){
		return bookService.findByName(name);
	}
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBookPost(@RequestBody BookDTO bookDTO){
		return bookService.addBook(bookDTO);
	}
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable Integer id){
		return bookService.delete(id);
	}
	@PutMapping("/editBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDTO){
		return bookService.updateBook(id, bookDTO);
	}
	
	@GetMapping("getAuthorBook/{id}")
	public ResponseEntity<List<Book>> getAuthor(@PathVariable Integer id){
		return bookService.getAuthor(id);
	}
	
}

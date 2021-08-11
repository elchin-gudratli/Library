package com.library.library.DTO;

public class Author_BookDTO {

	private Integer id;
	
	private AuthorDTO authors;
	
	private BookDTO books;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AuthorDTO getAuthors() {
		return authors;
	}

	public void setAuthors(AuthorDTO authors) {
		this.authors = authors;
	}

	public BookDTO getBooks() {
		return books;
	}

	public void setBooks(BookDTO books) {
		this.books = books;
	}

}

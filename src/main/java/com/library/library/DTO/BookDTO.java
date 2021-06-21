package com.library.library.DTO;

import java.util.List;

import javax.persistence.ManyToMany;


public class BookDTO {
	
	private Integer id;
	private String name;
	
	private List<CategoryDTO> categories;
	
	private List<User_BookDTO> date;
	
	private List<AuthorDTO> authors;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CategoryDTO> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}
	public List<AuthorDTO> getAuthors() {
		return authors;
	}
	public void setAuthors(List<AuthorDTO> authors) {
		this.authors = authors;
	}
	public List<User_BookDTO> getDate() {
		return date;
	}
	public void setDate(List<User_BookDTO> date) {
		this.date = date;
	}

}

package com.library.library.DTO;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User_BookDTO {
	
	private Integer id;
	private List<UserDTO> users;
	private List<BookDTO> books;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	public List<BookDTO> getBooks() {
		return books;
	}
	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}

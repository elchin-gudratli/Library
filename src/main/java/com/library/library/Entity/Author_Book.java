package com.library.library.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="author_books")
public class Author_Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="author_id", referencedColumnName = "id")
	private Author author_id;
	
	@ManyToOne
	@JoinColumn(name="book_id",  referencedColumnName = "id")
	private Book book_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Author getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(Author author_id) {
		this.author_id = author_id;
	}
	public Book getBook_id() {
		return book_id;
	}
	public void setBook_id(Book book_id) {
		this.book_id = book_id;
	}

}

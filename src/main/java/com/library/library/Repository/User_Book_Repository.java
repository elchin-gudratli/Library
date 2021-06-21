package com.library.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.library.Entity.User_Book;

public interface User_Book_Repository extends JpaRepository<User_Book, Integer>{

	@Query(value="select a from User_Book a where a.userId.id=:Id")
	List<User_Book> findAllByBookId(@Param("Id") Integer id);
	
	@Query(value="select a from User_Book a where a.id=:Id")
	List<User_Book> findAllDate(@Param("Id") Integer id);

	User_Book getById(Integer id);
	
}

package com.library.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.library.Entity.Author_Book;
import com.library.library.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query(value="Select b from Book b where b.name like %:name%")
	List<Book> getAll(@Param("name") String name);
	
	
	Book getById(Integer id);
	
	Book findByName(String name);
	
	@Query(value = "select b from Book b where b.id =:bookId")
	Book getBookDetail(@Param("bookId") Integer id);
	
	@Query(value="select n from Book n where n.id=:bookId")
	List<Book> findAllByBookId(@Param("bookId") Integer id);
	
}

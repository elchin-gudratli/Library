package com.library.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.library.library.Entity.Author_Book;
import com.library.library.Entity.Book;


@Repository
public interface Author_Book_Repository extends JpaRepository<Author_Book, Integer>{

	@Query(value="select a from Author_Book a where a.book_id.id=:Id")
	List<Author_Book> findAllByAuthorId(@Param("Id") Integer id);
	
	@Query("Select b.book_id from Author_Book b where b.author_id.id = :authorId")
	List<Book> findByAuthorId(@Param("authorId") Integer id);
	
	@Query(value="select a from Author_Book a where a.author_id.id=:Id")
	List<Author_Book> findAllByBookId(@Param("Id") Integer id);
	
}

package com.library.library.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.library.Entity.Author;
import com.library.library.Entity.Category;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{

	Author getById(Integer id);
	
	Author findByName(String name);
	
	@Query(value = "select a from Author a  where a.id =:authorId")
	Author getAuthorDetail(@Param("authorId") Integer id);
	
}

package com.library.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.library.library.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query(value="select c from Category c where c.id=:Id")
	List<Category> findAllByBookId(@Param("Id") Integer id);
	
	Category getById(Integer id);
	
}

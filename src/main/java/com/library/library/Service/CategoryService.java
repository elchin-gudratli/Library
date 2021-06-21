package com.library.library.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.library.library.DTO.CategoryDTO;
import com.library.library.Entity.Category;

public interface CategoryService {
	
    ResponseEntity<List<Category>> getAllCategory();
	
    Category getById(Integer id);
	
	ResponseEntity<Category> addCategory(CategoryDTO categoryDTO);
	
	ResponseEntity<Category> delete(Integer id);
	
	ResponseEntity<Category> updateCategory(Integer id, CategoryDTO categoryDTO);

}

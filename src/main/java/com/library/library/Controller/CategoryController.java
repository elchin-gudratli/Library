package com.library.library.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.DTO.CategoryDTO;
import com.library.library.Entity.Category;
import com.library.library.Service.CategoryService;


@RestController
@RequestMapping("api/category")
public class CategoryController {

	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@GetMapping("getAll")
	public ResponseEntity<List<Category>> getAllCategory(){
		return categoryService.getAllCategory();
	}
	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategoryPost(@RequestBody CategoryDTO categoryDTO){
		return categoryService.addCategory(categoryDTO);
	}
	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable Integer id){
		return categoryService.delete(id);
	}
	@PutMapping("/editCategory/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO){
		return categoryService.updateCategory(id, categoryDTO);
	}
	
}

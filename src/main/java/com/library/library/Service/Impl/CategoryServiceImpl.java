package com.library.library.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.library.library.DTO.CategoryDTO;
import com.library.library.Entity.Category;
import com.library.library.Repository.BookRepository;
import com.library.library.Repository.CategoryRepository;
import com.library.library.Service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	private final CategoryRepository categoryRepository;
	
	private final BookRepository bookRepository;
	
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository,BookRepository bookRepository) {
		this.categoryRepository = categoryRepository;
		this.bookRepository=bookRepository;
	}

	@Override
	public ResponseEntity getAllCategory() {
		List<Category> list = categoryRepository.findAll();
		List<CategoryDTO> ctgList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (Category c : list) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setId(c.getId());
				categoryDTO.setName(c.getName());
				ctgList.add(categoryDTO);
			}
			return ResponseEntity.ok(ctgList);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public Category getById(Integer id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<Category> addCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		categoryRepository.save(category);
		return ResponseEntity.ok(category);
	}

	@Override
	public ResponseEntity<Category> delete(Integer id) {
		Optional<Category> categoryOptional = categoryRepository.findById(id);
		if (categoryOptional.isPresent()) {
			categoryRepository.deleteById(id);
			return ResponseEntity.ok(categoryOptional.get());
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Category> updateCategory(Integer id, CategoryDTO categoryDTO) {
		Category category = categoryRepository.getById(id);
		if (category != null) {
			category.setName(categoryDTO.getName());
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		categoryRepository.save(category);
		return ResponseEntity.ok(category);
	}
}

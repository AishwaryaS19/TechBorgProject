package com.emb.techborg.service;

import java.util.List;

import com.emb.techborg.model.Category;

public interface CategoryService {

	List<Category> findAll();
	void saveCategory(Category category);
    Category findById(Long id);
    void deleteById(Long id);
}

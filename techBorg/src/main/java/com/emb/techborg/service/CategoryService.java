package com.emb.techborg.service;

import java.util.List;

import com.emb.techborg.model.Category;

public interface CategoryService {

	List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
}

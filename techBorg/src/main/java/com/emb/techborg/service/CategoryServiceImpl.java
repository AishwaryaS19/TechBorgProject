package com.emb.techborg.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emb.techborg.model.Category;
import com.emb.techborg.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    private CategoryRepository categoryRepository;
	
	private static final Logger log = LogManager.getLogger(CategoryServiceImpl.class);
	
	{
		BasicConfigurator.configure();
	}
	
	@Override
	public List<Category> findAll() {
		log.info("Fetching list of categories");        
        return categoryRepository.findAll();
	}

	@Override
	public Category save(Category category) {
		log.info("Saving new category to the database", category.getName());
        Category categorySave = new Category(category.getName());
        return categoryRepository.save(categorySave);
	}
	
	@Override
    public Category update(Category category) {
        Category categoryUpdate = null;
        try {
            categoryUpdate= categoryRepository.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("Updating existing category");
        return categoryRepository.save(categoryUpdate);
    }
	
//////
	@Override
    public Category findById(Long id) {
    	Optional<Category> optional =  categoryRepository.findById(id);
    	Category category = null;
		if (optional.isPresent()) {
			log.info("Fetching category from id {}", id);
			category = optional.get();
		}else {
			throw new RuntimeException("Category not found with Id: " + id);
		}
		return category;
	}

////////
	@Override
    public void deleteById(Long id) {
		log.info("Deleting category from id {}", id);
        this.categoryRepository.deleteById(id);
    }
	
}
package com.emb.techborg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emb.techborg.model.Category;
import com.emb.techborg.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(Model model){
    	model.addAttribute("listCategories", categoryService.findAll());
        return "category/categories";
    }
    
    @GetMapping("/addCategoryForm")
    public String addCategoryForm(Model model) {
    	Category category = new Category();
        model.addAttribute("category", category);
        return "category/addCategory";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@Valid @ModelAttribute("category") Category category,
    		BindingResult bindingResult, Model model) {
    	try {
	        if(bindingResult.hasErrors()){
	        	model.addAttribute("bindingResult", bindingResult);
	            return "category/addCategory";
	        }
	    	categoryService.saveCategory(category);
	    	model.addAttribute("successMessage", "Category saved successfully");
	        return "category/addCategory";
    	}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			model.addAttribute("successMessage", "Category already exists!");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("successMessage", "Error server");
        }
		return "category/addCategory";
    }

    @GetMapping("/updateForm/{id}")
    public String updateCategoryForm(@PathVariable(value = "id") long id, Model model) {
    	Category category = categoryService.findById(id);
    	model.addAttribute("category", category);
        return "category/updateCategory";
    }

    @GetMapping("/deleteCategory/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        try{
        	this.categoryService.deleteById(id);
        	model.addAttribute("successMessage", "Deleted successfully");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("successMessage", "Failed to delete");
        }
        return "category/categories";
    }
}

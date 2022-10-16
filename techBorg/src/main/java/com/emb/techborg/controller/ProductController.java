package com.emb.techborg.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emb.techborg.exception.ResourceNotFoundException;
import com.emb.techborg.model.Category;
import com.emb.techborg.model.Product;
import com.emb.techborg.service.CategoryService;
import com.emb.techborg.service.CategoryServiceImpl;
import com.emb.techborg.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    
    private static final Logger log = LogManager.getLogger(ProductController.class);
	
	{
		BasicConfigurator.configure();
	}
	
    @GetMapping("/productsList")
    public String products(Model model){
    	List<Product> productlist = productService.findAll();
        model.addAttribute("listProducts", productlist);
        model.addAttribute("size", productlist.size());
        return "product/products";
    }
    
    @GetMapping("/addProductForm")
    public String addProductForm(Model model){
    	List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return "product/addProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product")Product product,
    		BindingResult bindingResult,
    		@RequestParam("imageProduct") MultipartFile imageProduct,
    		Model model){
        try {
        	if(bindingResult.hasErrors()){
	        	model.addAttribute("bindingResult", bindingResult);
	        	model.addAttribute("successMessage", "Fill in all details!");
	            return "product/addProduct";
	        }
        	productService.saveProduct(imageProduct, product);
        	model.addAttribute("successMessage", "Product saved successfully");
        	return "product/addProduct";
        }catch(DataIntegrityViolationException e) {
        	log.error(e);
			model.addAttribute("successMessage", "Product already exists!");
        }catch (Exception e){
        	log.error(e);
            model.addAttribute("successMessage", "Something went wrong!");
        }
        return "product/addProduct";
    }

    @GetMapping("/updateProductForm/{id}")
    public String updateProductForm(@PathVariable("id") long id, Model model){
    	List<Category> categories = categoryService.findAll();
    	Product product = productService.findById(id);
        model.addAttribute("categories", categories);
    	model.addAttribute("product", product);
        return "product/updateProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deletedProduct(@PathVariable("id") long id, Model model){
    	try{
    		this.productService.deleteById(id);
    		model.addAttribute("successMessage", "Deleted successfully!");
	    }catch (Exception e){
	    	log.error(e);
	        model.addAttribute("successMessage", "Failed to delete!");
	    }
        return "redirect:/product/productsList";
    }
}

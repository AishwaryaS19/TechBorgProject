package com.emb.techborg.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emb.techborg.model.Category;
import com.emb.techborg.model.Product;
import com.emb.techborg.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
    @Autowired
    private ProductRepository productRepository;

    //@Autowired
    //private ImageUpload imageUpload;

    private static final Logger log = LogManager.getLogger(CategoryServiceImpl.class);
	
	{
		BasicConfigurator.configure();
	}

    @Override
    public List<Product> findAll() {
		log.info("Fetching list of products");  
		return productRepository.findAll();
    }
    
    @Override
	public void saveProduct(Product product) {
		log.info("Saving new product to the database", product.getName());
        this.productRepository.save(product);
	}
    
    @Override
    public Product findById(Long id) {
    	Optional<Product> optional =  productRepository.findById(id);
    	Product product = null;
		if (optional.isPresent()) {
			log.info("Fetching product from id {}", id);
			product = optional.get();
		}else {
			throw new RuntimeException("Product not found with Id: " + id);
		}
		return product;
	}

	@Override
    public void deleteById(Long id) {
		log.info("Deleting category from id {}", id);
        this.productRepository.deleteById(id);
    }	
}

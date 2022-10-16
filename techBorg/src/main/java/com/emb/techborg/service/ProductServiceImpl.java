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

import com.emb.techborg.exception.ResourceNotFoundException;
import com.emb.techborg.model.Category;
import com.emb.techborg.model.Product;
import com.emb.techborg.repository.ProductRepository;
import com.emb.techborg.utils.ImageUpload;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageUpload imageUpload;

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
	public void saveProduct(MultipartFile imageProduct, Product product) {
    	try {
            if(imageProduct == null){
            	product.setImage(null);
            }else{
                if(imageUpload.uploadImage(imageProduct)){
                	log.info("Uploading image to the database");                }
                	product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
			log.info("Saving new product to the database", product.getName());
			product.setName(product.getName());
			product.setDescription(product.getDescription());
			product.setCategory(product.getCategory());
			product.setCostPrice(product.getCostPrice());
			product.setSalePrice(product.getSalePrice());
			product.setCurrentQuantity(product.getCurrentQuantity());
	        this.productRepository.save(product);
    	}catch (Exception e){
            log.error(e);
        }
    } 
    
    @Override
    public Product findById(Long id){
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
		log.info("Deleting product from id {}", id);
        this.productRepository.deleteById(id);
    }	
}

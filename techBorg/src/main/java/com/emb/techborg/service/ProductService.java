package com.emb.techborg.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.emb.techborg.model.Category;
import com.emb.techborg.model.Product;

public interface ProductService {

	List<Product> findAll();
	void saveProduct(MultipartFile imageProduct, Product product);
	Product findById(Long id);
    void deleteById(Long id);
   
}

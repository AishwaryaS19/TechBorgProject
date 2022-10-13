package com.emb.techborg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emb.techborg.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

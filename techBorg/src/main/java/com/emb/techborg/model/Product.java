package com.emb.techborg.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
	
	@Length(min = 2, message = "Product Name should be atleast 2 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Invalid Product Name! Alphabets and Digits Only!")
    private String name;
    
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Invalid Product Description!")
    private String description;
    
    private double costPrice;
    
    private double salePrice;
    
    @Digits(integer=10, fraction=0, message = "Invalid Current Quantity! Digits Only!")
    private int currentQuantity;
    
    /*
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    */
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    
	public Product() {

	}

	public Product(Long id, String name, String description, double costPrice, 
			double salePrice, int currentQuantity, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.costPrice = costPrice;
		this.salePrice = salePrice;
		this.currentQuantity = currentQuantity;
		this.category = category;
	}
	/*
	public Product(Long id, String name, String description, double costPrice, double salePrice, int currentQuantity,
			String image, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.costPrice = costPrice;
		this.salePrice = salePrice;
		this.currentQuantity = currentQuantity;
		this.image = image;
		this.category = category;
	}
	*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
/*
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
*/
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}	
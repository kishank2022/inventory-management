package com.inventory.management.learning.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends PanacheEntity{

	// id automatic aata hai
	private String name;
	
	private Long stock;
	
	private Double price;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String name, Long stock, Double price) {
		super();
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", stock=" + stock + ", price=" + price + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}

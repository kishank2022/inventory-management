package com.inventory.management.learning.dto;

public class ProductDto {

private String name;
	
	private Long stock;
	
	private Double price;

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDto(String name, Long stock, Double price) {
		super();
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDto [name=" + name + ", stock=" + stock + ", price=" + price + "]";
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

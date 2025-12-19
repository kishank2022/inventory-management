package com.inventory.management.learning.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import com.inventory.management.learning.dto.ProductDto;
import com.inventory.management.learning.entity.Product;

@ApplicationScoped
public class ProductService {

	public List<Product> listProduct(){
		return Product.listAll();
	}
	
	@Transactional
	public Product saveProduct(ProductDto productDto) {
		
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		
		product.persist();
		return product;		
	}
	
	@Transactional
	public void updateProduct(Long id, ProductDto productDto) {
		Product product = new Product();
		Optional<Product> optionalProduct = Product.findByIdOptional(id);
		if(optionalProduct.isEmpty()) {
			throw new NullPointerException("Product Not Found!!");
		}
		product = optionalProduct.get();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.persist();		
	}
	
	@Transactional
	public void removeProduct(Long id) {
		Optional<Product> optionalProduct = Product.findByIdOptional(id);
		if(optionalProduct.isEmpty()) {
			throw new NullPointerException("Product Not Found!!");
		}
		Product product = optionalProduct.get();
		product.delete();
	}
}

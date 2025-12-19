package com.inventory.management.learning.controller;

import java.util.List;

import com.inventory.management.learning.dto.ProductDto;
import com.inventory.management.learning.entity.Product;
import com.inventory.management.learning.service.ProductService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

	@Inject
	ProductService productService;
	
	@GET
	public Response listProducts() {
		List<Product> list1 = productService.listProduct();
		return Response.ok(list1).status(200).build();
	}
	
	@POST
	public Response saveProduct(ProductDto productDto) {
		Product product = productService.saveProduct(productDto);
		return Response.ok(product).status(201).build();
	}
	
	
	@PUT
	@Path("{id}")
	public Response updateProduct(ProductDto productDto, @PathParam("id") Long id) {
		productService.updateProduct(id, productDto);
		return Response.status(204).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteProduct(@PathParam("id") Long id) {
		productService.removeProduct(id);
		return Response.status(204).build();
	}
	
}

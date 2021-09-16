package com.productapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.productapp.exception.ProductNotFoundException;
import com.productapp.model.Product;
import com.productapp.service.IProductService;

@SpringBootApplication
public class SpringProductappJdbcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringProductappJdbcApplication.class, args);
	}

	@Autowired
	IProductService productServiceImpl;

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product("Notepad", "gadgets", 23500.0);
		System.out.println("Added: ");
		productServiceImpl.addProduct(product);
//		productServiceImpl.deleteProduct(1);
//		productServiceImpl.updateProduct(1, 49000);
		System.out.println();
		System.out.println("All Products");
		productServiceImpl.getAll().forEach(System.out::println);
		System.out.println("Product by Id");
		try {
			System.out.println(productServiceImpl.getById(1));
		}
		catch(ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Products by Category");
		try {
			productServiceImpl.getByCategory("gadgets").forEach(System.out::println);
		}
		catch(ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Products by less than the given price");
		try {
			productServiceImpl.getByLesserPrice(10000).forEach(System.out::println);
		}
		catch(ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		

	}

}

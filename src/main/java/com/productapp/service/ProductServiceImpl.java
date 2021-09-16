package com.productapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productapp.exception.ProductNotFoundException;
import com.productapp.model.Product;
import com.productapp.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductRepository productRepository;

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

	@Override
	public void updateProduct(int productId, double price) {
		productRepository.updateProduct(productId, price);
	}

	@Override
	public void deleteProduct(int productId) {
		productRepository.deleteProduct(productId);
	}

	@Override
	public Product getById(int productId) throws ProductNotFoundException {

		Product product = productRepository.findById(productId);
		if (product == null)
			throw new ProductNotFoundException("Id not found");
		return product;
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getByCategory(String category) throws ProductNotFoundException {
		List<Product> productsByCategory = productRepository.findByCategory(category);
		if (productsByCategory.isEmpty()) {
			throw new ProductNotFoundException("Category Not Found");
		}
		return productsByCategory;
	}

	@Override
	public List<Product> getByLesserPrice(double price) throws ProductNotFoundException {
		List<Product> productsByLesserPrice = productRepository.findByLesserPrice(price);
		if (productsByLesserPrice.isEmpty()) {
			throw new ProductNotFoundException("Price Not Found Not Found");
		}
		return productsByLesserPrice;
	}

}

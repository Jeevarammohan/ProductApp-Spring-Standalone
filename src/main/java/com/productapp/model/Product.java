package com.productapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	private Integer productId;
	private String productName;
	private String category;
	private double price;
	public Product(String productName, String category, double price) {
		super();
		this.productName = productName;
		this.category = category;
		this.price = price;
	}
	
	
}

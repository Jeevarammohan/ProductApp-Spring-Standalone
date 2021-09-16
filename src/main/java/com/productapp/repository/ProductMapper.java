package com.productapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.productapp.model.Product;

public class ProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductName(rs.getString("productName"));
		product.setProductId(rs.getInt("productId"));
		product.setCategory(rs.getString("category"));
		product.setPrice(rs.getDouble("price"));
		return product;
	}

}

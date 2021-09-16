package com.productapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.productapp.exception.ProductNotFoundException;
import com.productapp.model.Product;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void addProduct(Product product) {
		String insertQuery = "insert into product(productName,category,price) values(?,?,?)";
		Object[] productArray = { product.getProductName(), product.getCategory(), product.getPrice() };
		jdbcTemplate.update(insertQuery, productArray);
	}

	@Override
	public void updateProduct(int productId, double price) {
		String updateQuery = "update product set price=? where productId=?";
		Object[] productArray = { price, productId };
		jdbcTemplate.update(updateQuery, productArray);
	}

	@Override
	public void deleteProduct(int productId) {
		String deleteQuery = "delete from product where productId=?";
		jdbcTemplate.update(deleteQuery, productId);
	}

	@Override
	public Product findById(int productId) throws ProductNotFoundException {
		String sqlQuery = "select * from product where productId=?";
		return jdbcTemplate.queryForObject(sqlQuery, new ProductMapper(), productId);
	}

	@Override
	public List<Product> findAll() {
		String sqlQuery = "select * from product";
		List<Product> productList = jdbcTemplate.query(sqlQuery, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setProductName(rs.getString("productName"));
				product.setProductId(rs.getInt("productId"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				return product;
			}

		});
		return productList;

	}

	@Override
	public List<Product> findByCategory(String category) throws ProductNotFoundException {
		String sqlQuery = "select * from product where category=?";
		List<Product> productList = jdbcTemplate.query(sqlQuery, (rs, rowNum) -> {
			Product product = new Product();
			product.setProductName(rs.getString("productName"));
			product.setProductId(rs.getInt("productId"));
			product.setCategory(rs.getString("category"));
			product.setPrice(rs.getDouble("price"));
			return product;
		}

				, category);
		return productList;
	}

	@Override
	public List<Product> findByLesserPrice(double price) throws ProductNotFoundException {
		String sqlQuery = "select * from product where price<?";
		List<Product> productList = jdbcTemplate.query(sqlQuery, new ProductMapper(), price);
		return productList;
	}

}

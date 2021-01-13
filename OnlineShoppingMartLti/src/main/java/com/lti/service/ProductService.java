package com.lti.service;

import java.util.List;

import com.lti.entity.Product;

public interface ProductService {
	Product addorUpdateProductbyRetailer(Product product);
	Product removeProductbyRetailer(long productId);
	Product fetchProductbyProductId(long productId);
	List<Product> fetchProductbyProductbyName(String productName);
	List<Product> fetchProductbyCategoryId(long categoryId);
	List<Product> fetchProductbyPrice(double minPrice,double maxPrice);
	List<Product> fetchProductbyBrand(String Brand);
	List<Product> fetchProductbyCategoryName(String categoryName);
	List<Product> fetchProductbyPriceHightoLow();
	List<Product> fetchProductbyPriceLowtoHigh();
}

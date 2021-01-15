package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Product;
import com.lti.repository.ProductRepository;
@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product fetchProductbyProductId(long productId) {
		// TODO Auto-generated method stub
		Product product=productRepository.fetchProductbyProductId(productId);
		return product;
	}

	@Override
	public List<Product> fetchProductbyCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		List<Product> product=productRepository.fetchProductbyCategoryId(categoryId);
		return product;
	}

	@Override
	public List<Product> fetchProductbyPrice(double minPrice, double maxPrice,long cid) {
		// TODO Auto-generated method stub
		List<Product> product=productRepository.fetchProductbyPrice(minPrice, maxPrice,cid);
		return product;
	}

	@Override
	public List<Product> fetchProductbyBrand(String Brand) {
		// TODO Auto-generated method stub
		List<Product> product=productRepository.fetchProductbyBrand(Brand);
		return product;
	}

	@Override
	public Product addorUpdateProductbyRetailer(Product product) {
		// TODO Auto-generated method stub
		Product p=productRepository.addorUpdateProductbyRetailer(product);
		return p;
	}

	@Override
	public Product removeProductbyRetailer(long productId) {
		// TODO Auto-generated method stub
		Product p=productRepository.removeProductbyRetailer(productId);
		return p;
	}

	@Override
	public List<Product> fetchProductbyProductbyName(String productName) {
		// TODO Auto-generated method stub
		return productRepository.fetchProductbyProductbyName(productName);
	}

	@Override
	public List<Product> fetchProductbyCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return productRepository.fetchProductbyCategoryName(categoryName);
	}

	@Override
	public List<Product> fetchProductbyPriceHightoLow(long cid) {
		// TODO Auto-generated method stub
		return productRepository.fetchProductbyPriceHightoLow(cid);
	}

	@Override
	public List<Product> fetchProductbyPriceLowtoHigh(long cid) {
		// TODO Auto-generated method stub
		return productRepository.fetchProductbyPriceLowtoHigh(cid);
	}

}

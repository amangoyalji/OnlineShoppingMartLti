package com.lti.repository;

import java.util.List;

import com.lti.entity.Product;
import com.lti.entity.Retailer;

public interface RetailerRepository {

	Product addorUpdateProductbyRetailer(Product product);
	Product removeProductbyRetailer(long productId);
	Retailer addorUpdateRetailer(Retailer retailer);
	Retailer fetchRetailerbyId(long retailerId);
	List<Product> fetchProductByRetailerId(long retailerId);
	long retailerLogin(String retailerEmail,String retailerPassword);
	
}

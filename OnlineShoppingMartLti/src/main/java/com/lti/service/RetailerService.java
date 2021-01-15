package com.lti.service;

import java.util.List;

import com.lti.entity.Product;
import com.lti.entity.Retailer;

public interface RetailerService {
	Retailer addorUpdateRetailer(Retailer retailer);
	Retailer fetchRetailerbyId(long retailerId);
	long retailerLogin(String retailerEmail,String retailerPassword);
	List<Product> fetchProductByRetailerId(long retailerId);
}

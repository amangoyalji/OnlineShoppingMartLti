package com.lti.service;

import com.lti.entity.Product;
import com.lti.entity.Retailer;

public interface RetailerService {
	Retailer addorUpdateRetailer(Retailer retailer);
	Retailer fetchRetailerbyId(long retailerId);

}

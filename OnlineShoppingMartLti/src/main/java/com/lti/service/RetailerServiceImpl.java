package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.repository.RetailerRepository;

@Component
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	RetailerRepository retailerRepository;
	@Override
	public Retailer addorUpdateRetailer(Retailer retailer) {
		// TODO Auto-generated method stub
		
		return retailerRepository.addorUpdateRetailer(retailer);
	}
	@Override
	public Retailer fetchRetailerbyId(long retailerId) {
		// TODO Auto-generated method stub
		return retailerRepository.fetchRetailerbyId(retailerId);
	}
	
	@Override
	public long retailerLogin(String retailerEmail, String retailerPassword) {
		// TODO Auto-generated method stub
		return retailerRepository.retailerLogin(retailerEmail, retailerPassword);
	}


}

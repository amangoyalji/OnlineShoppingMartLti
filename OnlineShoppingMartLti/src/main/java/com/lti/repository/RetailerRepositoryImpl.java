package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lti.entity.Product;
import com.lti.entity.Retailer;

@Component
public class RetailerRepositoryImpl implements RetailerRepository {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Retailer addorUpdateRetailer(Retailer retailer) {
		// TODO Auto-generated method stub
		Retailer r=em.merge(retailer);
		return r;
	}

	@Transactional
	public Retailer fetchRetailerbyId(long retailerId) {
		// TODO Auto-generated method stub
		
	Retailer r=em.find(Retailer.class, retailerId);
	return r;
	}

	@Transactional
	public Product addorUpdateProductbyRetailer(Product product) {
		Product p=em.merge(product);
		return p;
	}

	@Transactional
	public Product removeProductbyRetailer(long productId) {
		Product product=em.find(Product.class, productId);
		em.remove(product);
		return product;
	}

	@Transactional
	public List<Product> fetchProductByRetailerId(long retailerId) {
		Retailer r = em.find(Retailer.class, retailerId);
		List<Product> listofproduct = r.getProduct();
		return listofproduct;
	}

}

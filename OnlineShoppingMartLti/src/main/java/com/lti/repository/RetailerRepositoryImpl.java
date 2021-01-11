package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
		retailer.setRetailerApproved(false);
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

	@Transactional
	public long retailerLogin(String retailerEmail, String retailerPassword) {
		// TODO Auto-generated method stub
		
		String jpql="select r from Retailer r where retailerEmail=:email and retailerPassword=:pass";
		try {
			Query query=em.createQuery(jpql);
			query.setParameter("email", retailerEmail);
			query.setParameter("pass", retailerPassword);
			Retailer r=(Retailer) query.getSingleResult();
			return r.getRetailerId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

}

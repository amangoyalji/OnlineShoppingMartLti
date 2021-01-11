package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.entity.User;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
	
	@PersistenceContext
	EntityManager em;


	
	@Transactional
	public Retailer approveRetailer(Retailer retailer) {
		retailer.setRetailerApproved(false);
		Retailer r= em.merge(retailer);
		return r;
	}

	@Transactional
	public Product approveProduct(Product product) {
		product.setApproved(true);
		Product p = em.merge(product);
		return p;
	}

	@Transactional
	public User approveUser(User user) {
		user.setApproved(true);
		User u = em.merge(user);
		return u;
	}

	@Transactional
	public Retailer deleteRetailer(long retailerId) {
		Retailer r = em.find(Retailer.class, retailerId);
		em.remove(r);
		return r;
	}

	@Transactional
	public Product deleteProduct(long productId) {
		Product product = em.find(Product.class, productId);
		em.remove(product);
		return product;
	}

	@Transactional
	public User deleteUser(long userId) {
		User u = em.find(User.class, userId);
		em.remove(u);
		return u;
	}
	
	@Transactional
	public Admin addAdmin(Admin admin) {
		Admin a = em.merge(admin);
		return a;
	}
	
	@Transactional
	public Admin loginAdmin(String email,String password) {
		String jpql = "select a from Admin a where a.adminEmail=:pemail and a.adminPassword=:ppassword";
		try {
			TypedQuery<Admin> query = em.createQuery(jpql, Admin.class);
			query.setParameter("pemail", email);
			query.setParameter("ppassword", password);
			Admin admin= (Admin) query.getSingleResult();
			return admin;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public Admin fetchAdminById(long adminId) {
		Admin a = em.find(Admin.class, adminId);
		return a;
	}



}

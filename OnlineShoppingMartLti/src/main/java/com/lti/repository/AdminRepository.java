package com.lti.repository;

import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.entity.User;

import java.util.List;

import com.lti.entity.Admin;

public interface AdminRepository {
	Retailer approveRetailer(Retailer retailer);
	Product approveProduct(Product product);
	User approveUser(User user);
	
	Retailer deleteRetailer(long retailerId);
	Product deleteProduct(long productId);
	User deleteUser(long userId);
	
	
	Admin addAdmin(Admin admin);
	Admin loginAdmin(String email,String Password);
	Admin fetchAdminById(long adminId);
	
	List<Product> fetcProductNotApproved();
	List<User> fetchUserNotApproved();
	List<Retailer> fetcRetailerNotApproved();

	
	
}

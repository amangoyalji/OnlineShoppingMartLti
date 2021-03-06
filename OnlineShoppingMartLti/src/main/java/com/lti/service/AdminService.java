package com.lti.service;

import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.entity.User;

import java.util.List;

import com.lti.entity.Admin;

public interface AdminService {
	
	Admin addAdmin(Admin admin);
	Admin loginAdmin(String email,String Password);
	Admin fetchAdminById(long adminId);

	Retailer approveRetailer(Retailer retailer);
	Product approveProduct(Product product);
	User approveUser(User user);
	
	Retailer deleteRetailer(long retailerId);
	Product deleteProduct(long productId);
	User deleteUser(long userId);
	
	List<Product> fetchProductNotApproved();
	List<Retailer> fetcRetailerNotApproved();
	List<User> fetchUserNotApproved();
	
}

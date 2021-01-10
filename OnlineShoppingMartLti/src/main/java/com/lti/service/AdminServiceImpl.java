package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.entity.User;
import com.lti.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;
	
	public Retailer approveRetailer(Retailer retailer) {
		return adminRepository.approveRetailer(retailer);
	}
	public Product approveProduct(Product product) {
		return adminRepository.approveProduct(product);
	}
	public User approveUser(User user) {
		return adminRepository.approveUser(user);
	}
	
	public Retailer deleteRetailer(long retailerId) {
		return adminRepository.deleteRetailer(retailerId);
	}
	public Product deleteProduct(long productId) {
		return adminRepository.deleteProduct(productId);
	}
	
	public User deleteUser(long userId) {
		return adminRepository.deleteUser(userId);
	}
	
	public Admin addAdmin(Admin admin) {
		Admin a = adminRepository.addAdmin(admin);
		return a;
	}
	
	public Admin loginAdmin(String email, String password) {
		Admin  a =adminRepository.loginAdmin(email, password);
		return a;
	}
	
	public Admin fetchAdminById(long adminId) {
		return adminRepository.fetchAdminById(adminId);
	}

}

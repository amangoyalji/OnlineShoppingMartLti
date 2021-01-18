package com.lti.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Admin;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.entity.User;
import com.lti.service.AdminService;
import com.lti.service.EmailService;
import com.lti.service.ProductService;
import com.lti.service.RetailerService;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class AdminResource {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RetailerService retailerService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping(path = "/findAdminById/{adminId}")
	public Admin findAdminById(@PathVariable("adminId") long adminId) {
		return adminService.fetchAdminById(adminId);
	}
	
	@RequestMapping(value="/addAdmin", method=RequestMethod.POST)
	public Admin addAdmin(@RequestBody Admin admin) {
		Admin a = adminService.addAdmin(admin);
		return a;
	}
	
	@GetMapping(path = "/loginAdmin/{emailId}/{password}")
	public Admin findAdminById(@PathVariable("emailId") String emailId, @PathVariable("password") String password) {
		return adminService.loginAdmin(emailId, password);
	}
	
	@GetMapping(value = "/approveProduct/{productId}")
	public Product approveProduct(@PathVariable long productId) {
		Product product = productService.fetchProductbyProductId(productId);
		Product p = adminService.approveProduct(product);
		String email = p.getRetailer().getRetailerEmail();
		String subj ="Product Approved ";
		String message = "the request for product "+p.getProductName()+" request you submitted has been approved";
		emailService.sendEmailForNewRegistration(email, message, subj);
		return p;
	}
	
	@GetMapping(value = "/approveRetailer/{retailerId}")
	public Retailer approveRetailer(@PathVariable long retailerId) {
		Retailer retailer = retailerService.fetchRetailerbyId(retailerId);
		Retailer r = adminService.approveRetailer(retailer);
		String email = r.getRetailerEmail();
		String subj ="Retailer Approved ";
		String message = "the retailer "+r.getRetailerName()+" request you submitted has been approved now you can sucessfully login";
		emailService.sendEmailForNewRegistration(email, message, subj);
		return r;
	}
	
	@GetMapping(value = "/approveUser/{userId}")
	public User approveUser(@PathVariable long userId) {
		User user = userService.fetchUserById(userId);
		User u = adminService.approveUser(user);
		String email = u.getEmail();
		String subj ="User Approved ";
		String message = "the user "+u.getUserName()+" request you submitted has been approved now you can sucessfully login";
		emailService.sendEmailForNewRegistration(email, message, subj);
		return u;
	}
	
	@GetMapping(value="/notapprovedproducts")
	public List<Product> notApprovedProducts(){
		return adminService.fetchProductNotApproved();
	}
	
	@GetMapping(value="/notapprovedretailers")
	public List<Retailer> notApprovedRetailers(){
		return adminService.fetcRetailerNotApproved();
	}
	
	@GetMapping(value="/notapprovedusers")
	public List<User> notApprovedUsers(){
		return adminService.fetchUserNotApproved();
	}

}

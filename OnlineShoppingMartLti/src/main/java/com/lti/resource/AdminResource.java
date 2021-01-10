package com.lti.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Admin;
import com.lti.service.AdminService;

@RestController
@CrossOrigin
public class AdminResource {
	
	@Autowired
	AdminService adminService;
	
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

}

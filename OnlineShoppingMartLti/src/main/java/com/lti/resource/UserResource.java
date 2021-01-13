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

import com.lti.entity.User;
import com.lti.entity.WishList;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class UserResource {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public User addOrUpdateUserNotApproved(@RequestBody User user){
		long userId = userService.addOrUpdateUserNotApproved(user);
		User user2 = userService.fetchUserById(userId);
		return user2;
	}
	
	@GetMapping(value="/login/{email}/{password}")
	 public User fetchUserUsingEmailAndPassword(@PathVariable("email") String email,@PathVariable("password") String password) {
		// TODO Auto-generated method stub
		return userService.fetchUserUsingEmailAndPassword(email, password);
	}
	@GetMapping(value="/wish/{userId}")
	public List<WishList> viewWishListByUserId(@PathVariable("userId") long userId){
        List<WishList> w =  userService.viewWishListByUserId(userId);
        for(WishList we:w) {
        	System.out.println(we.getWishId()+" "+we.getProductId());
        }
        return w;
    }
}

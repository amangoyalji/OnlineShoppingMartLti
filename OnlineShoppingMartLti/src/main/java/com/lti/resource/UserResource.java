package com.lti.resource;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CartDto;
import com.lti.dto.WishDto;
import com.lti.entity.Cart;
import com.lti.entity.Items;
import com.lti.entity.Order;
import com.lti.entity.Product;
import com.lti.entity.User;
import com.lti.entity.WishList;
import com.lti.service.EmailService;
import com.lti.service.ProductService;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class UserResource {

	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	EmailService emailService;
	
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
	
	@GetMapping(value = "/viewCart/{userId}")
	public List<Cart> viewCartByUserId(@PathVariable("userId") long userId){
		return userService.viewCartByUserId(userId);
	}
	
	@RequestMapping(value = "/addToWishList", method = RequestMethod.POST)
	public long addToWishlist(@RequestBody WishDto w) {
		WishList wish = new WishList();
		User u = userService.fetchUserById(w.getUserId());
		Product p =productService.fetchProductbyProductId(w.getProductId());
		wish.setProductId(p.getProductId());
		wish.setUser(u);
		wish.setProductName(p.getProductName());
		wish.setPath(p.getPath());
		return userService.addToWishlist(wish);

	}
	
//	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
//	public long addToCart(@RequestBody CartDto c) {
//		// TODO Auto-generated method stub
//		Cart cart = new Cart();
//		Product p = productService.fetchProductbyProductId(c.getProductId());
//		cart.setProductId(p.getProductId());
//		User u = userService.fetchUserById(c.getUserId());
//		cart.setUser(u);
//		cart.setQuantity(c.getQuantity());
//	   cart.setProductName(p.getProductName());
//	   cart.setPath(p.getPath());
//		cart.setTotalPrice(p.getProductPrice()*cart.getQuantity());
//		return userService.addToCart(cart);
//	}
	
	@GetMapping(value = "/removeFromCart/{productId}")
	public long removeFromCart(@PathVariable("productId") long productId){
		Cart c = userService.viewCartByProductId(productId);
		return userService.removeFromCart(c);
		//System.out.println("Item Removed from cart");
    }
	
	@GetMapping(value = "/viewUser/{userId}")
	public User viewUserByUserId(@PathVariable("userId") long userId){
		return userService.fetchUserById(userId);
	}
	
	@PostMapping(value="/placeOrder")
	public long addOrderandOrderdetails(@RequestBody CartDto cartdto)
	{
		Order o=new Order();
		User user=userService.fetchUserById(cartdto.getUserId());
		o.setUser(user);
		long oid=userService.placeOrder(o);
//		=o.getOrderId();
		Items i=new Items();
		int j=0;
		
		List<Integer> quantity=cartdto.getQuantity();
		
		for(Product p:cartdto.getProducts())
		{
			i.setOrderId(oid);
			i.setPrice(p.getProductPrice());
			i.setProductId(p.getProductId());
			i.setQuantity(quantity.get(j++));
			//i.setQuantity(p.getQuantity());
			i.setUserId(user.getUserId());
			
			userService.addProductInItems(i);
		}
		String email = user.getEmail();
		String message ="Hi "+ user.getUserName()+" the your order placed successfully. the order Id is  "+oid;
		String subj = "Purchase Request";
		emailService.sendEmailForNewRegistration(email, message, subj);
		
		return oid;
	}
	
	
	@GetMapping(value="/forgetpassword/{userId}")
	 public long fetchUserUsingUserId(@PathVariable("userId") long userId) {
		// TODO Auto-generated method stub
		User u =  userService.fetchUserById(userId);
		Random rand = new Random();
		long otp = rand.nextInt(899999) + 600000;
		String email = u.getEmail();
		String message ="Hi "+ u.getUserName()+" the otp for reseting the password is "+otp;
		String subj = "Password Reset Request";
		emailService.sendEmailForNewRegistration(email, message, subj);
		return otp;
	}
	
	
	
}

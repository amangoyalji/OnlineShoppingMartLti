package com.lti.repository;

import java.util.List;

import com.lti.entity.Address;
import com.lti.entity.Cart;
import com.lti.entity.Items;
import com.lti.entity.Order;
import com.lti.entity.Product;
import com.lti.entity.User;
import com.lti.entity.WishList;



public interface UserRepository {

	long addProductInItems(Items items);
    long placeOrder(Order order);
	long addOrUpdateUserNotApproved(User user);
		long addAddress(Address address);
		User fetchUserUsingEmailAndPassword(String email,String password);
		User fetchUserById(long userId);
		long addToCart(Cart cart);
		long addToWishlist(WishList wish);
	    
	    long updateProductQuantity(Product product);
	    Order fetchOrderById(long orderId);
	    List<Items> fetchItemsByUserId(long userId);
	    long increaseItemQuantityInCart(Cart cart);
	    long decreaseItemQuantityInCart(Cart cart);
	    Cart findCartById(long cartId);
	    List<Cart> viewCartByUserId(long userId);
	    List<WishList> viewWishListByUserId(long userId);
	    long removeFromCart(Cart cart);
	    long removeFromWishList(WishList wish);
	    Cart viewCartByProductId(long productId);
	    WishList viewWishListByProductId(long productId);
	    List<Items> viewOrderDetailsByUserId(long userId);
	   
}

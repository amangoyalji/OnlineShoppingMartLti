package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Address;
import com.lti.entity.Cart;
import com.lti.entity.Items;
import com.lti.entity.Order;
import com.lti.entity.Product;
import com.lti.entity.User;
import com.lti.entity.WishList;
import com.lti.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public long addOrUpdateUserNotApproved(User user) {
		// TODO Auto-generated method stub
		return userRepository.addOrUpdateUserNotApproved(user);
	}

	public long addAddress(Address address) {
		// TODO Auto-generated method stub
		return userRepository.addAddress(address);
	}

	public User fetchUserUsingEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.fetchUserUsingEmailAndPassword(email, password);
	}

	public User fetchUserById(long userId) {
		// TODO Auto-generated method stub
		return userRepository.fetchUserById(userId);
	}

	public long addProductInItems(Items items) {
		// TODO Auto-generated method stub
		return userRepository.addProductInItems(items);
	}

	public long addToCart(Cart cart) {
		// TODO Auto-generated method stub
		return userRepository.addToCart(cart);
	}

	public long addToWishlist(WishList wish) {
		// TODO Auto-generated method stub
		return userRepository.addToWishlist(wish);
	}

	public long placeOrder(Order order) {
		// TODO Auto-generated method stub
		return userRepository.placeOrder(order);
	}

	public long updateProductQuantity(Product product) {
		// TODO Auto-generated method stub
		return userRepository.updateProductQuantity(product);
	}

	@Override
	public Order fetchOrderById(long orderId) {
		// TODO Auto-generated method stub
		return userRepository.fetchOrderById(orderId);
	}

	@Override
	public List<Items> fetchItemsByUserId(long userId) {
		// TODO Auto-generated method stub
		return userRepository.fetchItemsByUserId(userId);
	}

	@Override
	public long increaseItemQuantityInCart(Cart cart) {
		// TODO Auto-generated method stub
		return userRepository.increaseItemQuantityInCart(cart);
	}

	@Override
	public long decreaseItemQuantityInCart(Cart cart) {
		// TODO Auto-generated method stub
		return userRepository.decreaseItemQuantityInCart(cart);
	}

	@Override
	public Cart findCartById(long cartId) {
		// TODO Auto-generated method stub
		return userRepository.findCartById(cartId);
	}

	@Override
	public List<Cart> viewCartByUserId(long userId) {
		// TODO Auto-generated method stub
		return userRepository.viewCartByUserId(userId);
	}

	@Override
	public List<WishList> viewWishListByUserId(long userId) {
		// TODO Auto-generated method stub
		return userRepository.viewWishListByUserId(userId);
	}
}

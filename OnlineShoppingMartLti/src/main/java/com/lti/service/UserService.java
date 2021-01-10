package com.lti.service;

import java.util.List;

import com.lti.entity.Address;
import com.lti.entity.Cart;
import com.lti.entity.Items;
import com.lti.entity.Order;
import com.lti.entity.Product;
import com.lti.entity.User;
import com.lti.entity.WishList;

public interface UserService {

	long addOrUpdateUserNotApproved(User user);

	long addAddress(Address address, long userId);

	User fetchUserUsingEmailAndPassword(String email, String password);

	User fetchUserById(long userId);

	long addProductInItems(Items items);

	long addToCart(Cart cart);

	long addToWishlist(WishList wish);

	long placeOrder(Order order);

	long updateProductQuantity(Product product);

	Order fetchOrderById(long orderId);

	List<Items> fetchItemsByUserId(long userId);

	long increaseItemQuantityInCart(Cart cart);

	long decreaseItemQuantityInCart(Cart cart);

	Cart findCartById(long cartId);

	List<Cart> viewCartByUserId(long userId);

	List<WishList> viewWishListByUserId(long userId);
}

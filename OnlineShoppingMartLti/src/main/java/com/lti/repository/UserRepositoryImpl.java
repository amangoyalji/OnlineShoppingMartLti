package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Address;
import com.lti.entity.Cart;
import com.lti.entity.Items;
import com.lti.entity.Order;
import com.lti.entity.Product;
import com.lti.entity.User;
import com.lti.entity.WishList;

@Component
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	EntityManager em;

	
	@Transactional
	public long addOrUpdateUserNotApproved(User user) {
		// TODO Auto-generated method stub
		User u= em.merge(user);
		return u.getUserId();
	}

	@Transactional
	public long addAddress(Address address) {
		// TODO Auto-generated method stub
		Address a = em.merge(address);
		return a.getAddressId();
	}
 
	@Transactional
	public User fetchUserUsingEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		String jpql = "select u from User u where u.email=:pemail and u.password=:ppassword";
		try {
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("pemail", email);
			query.setParameter("ppassword", password);
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		
		
	}

	@Transactional
	public User fetchUserById(long userId) {
		// TODO Auto-generated method stub
	    User usr= em.find(User.class, userId);
	    System.out.println(usr.getUserId());
	    System.out.println(usr.getUserName());
		return usr;
	}

	@Transactional
	public long addToCart(Cart cart) {
		Cart c = em.merge(cart);
		return c.getCartId();
	}

	@Transactional
	public long addProductInItems(Items items) {
		Items it = em.merge(items);
		return it.getItemId();
		
		
	}

	@Transactional
	public long addToWishlist(WishList wish) {
		// TODO Auto-generated method stub
		WishList w = em.merge(wish);
		return w.getWishId();
	}

	@Transactional
	public long placeOrder(Order order) {
		// TODO Auto-generated method stub
		Order o = em.merge(order);
		return o.getOrderId();
	}
    
	@Transactional
	public long updateProductQuantity(Product product) {
		// TODO Auto-generated method stu
		Product p = em.merge(product);
		return p.getProductId();
	}

	@Transactional
	public Order fetchOrderById(long orderId) {
		// TODO Auto-generated method stub
		Order order = em.find(Order.class, orderId);
		return order;
	}

	@Transactional
	public List<Items> fetchItemsByUserId(long userId) {
		// TODO Auto-generated method stub
		/*Items it = em.find(Items.class, userId);
		return null;*/
		String jpql = "select i from Items i where i.userId=:uid";
		TypedQuery<Items> query= em.createQuery(jpql,Items.class);
		query.setParameter("uid", userId);
		return query.getResultList();
		
	}

	@Transactional
	public long increaseItemQuantityInCart(Cart cart) {
		// TODO Auto-generated method stub
		Cart c = em.merge(cart);
		return c.getCartId();
	}

	@Transactional
	public long decreaseItemQuantityInCart(Cart cart) {
		// TODO Auto-generated method stub
		Cart c = em.merge(cart);
		return c.getCartId();
	}

	@Transactional
	public Cart findCartById(long cartId) {
		// TODO Auto-generated method stub
		Cart c = em.find(Cart.class, cartId);
		return c;
	}

	@Transactional
	public List<Cart> viewCartByUserId(long userId) {
		// TODO Auto-generated method stub
		String jpql = "select c from Cart c where c.user.userId=:uid";
		TypedQuery<Cart> query = em.createQuery(jpql,Cart.class);
		query.setParameter("uid", userId);
		return query.getResultList();
	}

	@Transactional
	public List<WishList> viewWishListByUserId(long userId) {
		// TODO Auto-generated method stub
		String jpql = "select w from WishList w where w.user.userId=:uid";
		TypedQuery<WishList> query = em.createQuery(jpql,WishList.class).setParameter("uid", userId);
		return query.getResultList();
	}
	
	@Transactional
	public long removeFromCart(Cart cart) {
		// TODO Auto-generated method stub
		em.remove(em.contains(cart) ? cart : em.merge(cart));
		return cart.getCartId();
	}

	@Transactional
	public long removeFromWishList(WishList wish) {
		// TODO Auto-generated method stub
		em.remove(wish);
		return wish.getWishId();
	}

	@Transactional
	public Cart viewCartByProductId(long productId) {
		// TODO Auto-generated method stub
		try {
			String jpql = "select c from Cart c where c.productId=:pid";
			TypedQuery<Cart> query = em.createQuery(jpql,Cart.class);
			query.setParameter("pid", productId );
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	
	}

	@Transactional
	public WishList viewWishListByProductId(long productId) {
		// TODO Auto-generated method stub
		
		try {
			String jpql = "select w from WishList w where w.productId=:pid";
			TypedQuery<WishList> query = em.createQuery(jpql,WishList.class);
			query.setParameter("pid", productId );
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		
	}
	
	
	@Transactional
	public List<Items> viewOrderDetailsByUserId(long userId) {
		// TODO Auto-generated method stub
		String jpql = "select i from Items i where i.userId =: userId";
		TypedQuery<Items> query = em.createQuery(jpql,Items.class);
		query.setParameter("userId", userId);
		
		return query.getResultList();
	}
	

}

package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="shop_wish")
public class WishList {
	@Id
	@GeneratedValue
	long wishId;
	long productId;
	
	@OneToOne
	User user;
	
	public long getWishId() {
		return wishId;
	}

	public void setWishId(long wishId) {
		this.wishId = wishId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	
	
	

}

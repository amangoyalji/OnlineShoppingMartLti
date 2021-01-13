package com.lti.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="shop_orders")
public class Order {

	@Id
	@GeneratedValue
	long orderId;
	//long addressId;
	//long productId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public long getAddressId() {
//		return addressId;
//	}
//
//	public void setAddressId(long addressId) {
//		this.addressId = addressId;
//	}

//	public long getProductId() {
//		return productId;
//	}
//
//	public void setProductId(long productId) {
//		this.productId = productId;
//	}

	
}

package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="shop_new_user")
public class User {
	
	@Id
	@SequenceGenerator(name="seq_new_user",initialValue=511,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_new_user")
	long userId;
	String userName;
	String phoneNo;
	String email;
	String password;
	boolean approved;
	
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	Address address;
	
	@OneToMany(mappedBy="user")
	List<Cart> cart;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	List<Order> order;
	
	@OneToMany(mappedBy = "user")
	List<WishList> wish;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}



	@JsonIgnore
	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@JsonIgnore
	public List<WishList> getWish() {
		return wish;
	}

	public void setWish(List<WishList> wish) {
		this.wish = wish;
	}


	
	
	
	

}

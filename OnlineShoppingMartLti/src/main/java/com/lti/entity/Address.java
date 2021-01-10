package com.lti.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="shop_Address")
public class Address {
	
	@Id
	@SequenceGenerator(name="seq_newaddress",initialValue=100,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_newaddress")
	long addressId;
	long streetNo;
	String colonyName;
	String landMark;
	String city;
	String state;
	
	@OneToOne
	@JoinColumn(name="userId")
	User user;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(long streetNo) {
		this.streetNo = streetNo;
	}

	public String getColonyName() {
		return colonyName;
	}

	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}

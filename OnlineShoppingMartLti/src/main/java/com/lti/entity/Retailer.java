package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="shop_retailer")
public class Retailer {
	@Id
	@SequenceGenerator(name = "seq_retailer",initialValue = 9000,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_retailer")
	long retailerId;
	String retailerName;
	String retailerPhoneNo;
	String retailerEmail;
	String retailerPassword;
	String retailerApproved;
	
	@OneToMany(mappedBy = "retailer",cascade = CascadeType.ALL)
	List<Product> product;

	public long getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(long retailerId) {
		this.retailerId = retailerId;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getRetailerPhoneNo() {
		return retailerPhoneNo;
	}

	public void setRetailerPhoneNo(String retailerPhoneNo) {
		this.retailerPhoneNo = retailerPhoneNo;
	}

	public String getRetailerEmail() {
		return retailerEmail;
	}

	public void setRetailerEmail(String retailerEmail) {
		this.retailerEmail = retailerEmail;
	}

	public String getRetailerPassword() {
		return retailerPassword;
	}

	public void setRetailerPassword(String retailerPassword) {
		this.retailerPassword = retailerPassword;
	}

	public String getRetailerApproved() {
		return retailerApproved;
	}

	public void setRetailerApproved(String retailerApproved) {
		this.retailerApproved = retailerApproved;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
}

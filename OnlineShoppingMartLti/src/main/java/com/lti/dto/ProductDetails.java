package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDetails {
	int productId;
	MultipartFile path;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public MultipartFile getPath() {
		return path;
	}
	public void setPath(MultipartFile productPic) {
		this.path = productPic;
	}
	
	
	

}

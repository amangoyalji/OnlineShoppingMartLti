package com.lti.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ProductDetails;
import com.lti.entity.Product;
import com.lti.service.ProductService;


@RestController
@CrossOrigin
public class ProductResource {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/addProducts", method =RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) {
		Product p = productService.addorUpdateProductbyRetailer(product);
		return p;
	}
	
	@PostMapping(value = "/uploadProductPic")
	public Product uploadProduct(ProductDetails productDetails) {
		long productId = productDetails.getProductId();
		String imgUploadLocation = "G:/upload/";
		String uploadedFileName = productDetails.getPath().getOriginalFilename();
		String newFileName = productId + "-" + uploadedFileName;
		String targetFileName = imgUploadLocation + newFileName;
		try {
			FileCopyUtils.copy(productDetails.getPath().getInputStream(), new FileOutputStream(targetFileName));
		} catch(IOException e) {
			e.printStackTrace(); //hoping no error would occur
		}
		Product product =  productService.fetchProductbyProductId(productId);
		product.setPath(newFileName);
		productService.addorUpdateProductbyRetailer(product);
		return product;
				
		
	}
	
	
	@GetMapping("/product")
	public Product viewProductById(@RequestParam("productId") int productId,HttpServletRequest request) {
		Product product = productService.fetchProductbyProductId(productId);
		
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath+"/downloads/";
		
		File f = new File(tempDownloadPath);
		if(!f.exists())
			f.mkdir();
		
		String targetFile = tempDownloadPath+product.getPath();
		System.out.println(tempDownloadPath);
		
		String uploadedImagesPath = "G:/upload/";
		String sourceFile = uploadedImagesPath+product.getPath();
		
		try {
			FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
			System.out.println("done");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("not done");
		}
		
		return product;
	}

}

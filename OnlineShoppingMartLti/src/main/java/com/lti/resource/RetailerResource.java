package com.lti.resource;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Category;
import com.lti.entity.Product;
import com.lti.entity.Retailer;
import com.lti.service.CategoryService;
import com.lti.service.ProductService;
import com.lti.service.RetailerService;
import com.lti.dto.ProductDto;

@RestController
@CrossOrigin
public class RetailerResource {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RetailerService retailerService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/addProduct",method = RequestMethod.POST)
	public long addorUpdateProductbyRetailer(@RequestBody ProductDto productdto)
	{
		Product p=new Product();
		System.out.println(productdto.getCategoryId());
		Retailer r=retailerService.fetchRetailerbyId(productdto.getRetailerId());
		Category c=categoryService.fetchCategorybyId(productdto.getCategoryId());
		System.out.println(c.getCategoryName());
		p.setApproved(productdto.isApproved());
		p.setBrand(productdto.getBrand());
		p.setCategory(c);
		p.setPath(productdto.getPath());
		p.setProductDescription(productdto.getProductDescription());
		p.setProductName(productdto.getProductName());
		p.setProductPrice(productdto.getProductPrice());
		p.setQuantity(productdto.getQuantity());
		p.setRetailer(r);
		//Product p=productService.addorUpdateProductbyRetailer(product);
		return productService.addorUpdateProductbyRetailer(p).getProductId();
		
	}
	
	@GetMapping(value="/productbyname/{productname}")
	public List<Product> fetchProductbyProductbyName(@PathVariable String productname)
	{
		return productService.fetchProductbyProductbyName(productname);
	}
	
	@DeleteMapping(value="/deleteproduct/{productId}")
	public void removeProductbyRetailer(@PathVariable long productId)
	{
		productService.removeProductbyRetailer(productId);
	}
	
	@GetMapping(value="/productbyId/{productId}")
	public Product fetchProductbyProductId(@PathVariable long productId)
	{
		return productService.fetchProductbyProductId(productId);
	}
	
	@GetMapping(value="/productbyCategoryId/{categoryId}")
	public List<Product> fetchProductbyCategoryId(@PathVariable long categoryId)	
	{
		return productService.fetchProductbyCategoryId(categoryId);
	}
	
	
	@GetMapping(value="/productbyBrandname/{brandName}")
	public List<Product> fetchProductbyBrandName(@PathVariable String brandName)	
	{
		return productService.fetchProductbyBrand(brandName);
	}
	
	@GetMapping(value="/productbyPrice/{minPrice}/{maxPrice}/{categoryName}")
	public List<Product> fetchProductbyPrice(@PathVariable double minPrice,@PathVariable double maxPrice,@PathVariable String categoryName,HttpServletRequest request)	
	{
		long cId=categoryService.fetchCategoryIdbyName(categoryName).getCategoryId();
		List<Product> products = productService.fetchProductbyPrice(minPrice, maxPrice,cId);
		for(Product product:products) {
			
			String projPath = request.getServletContext().getRealPath("/");
			String tempDownloadPath = projPath+"/downloads/";
			
			File f = new File(tempDownloadPath);
			if(!f.exists())
				f.mkdir();
			
			String targetFile = tempDownloadPath+product.getPath();
			System.out.println(tempDownloadPath);
			
			String uploadedImagesPath = "D:/uploads/";
			String sourceFile = uploadedImagesPath+product.getPath();
			
			try {
				FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
				System.out.println("done");
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println("not done");
			}
			
		}
		
		return products;
		//return productService.fetchProductbyPrice(minPrice, maxPrice);
	}
	
//	@GetMapping(value="/productbyCategoryId/{categoryId}")
//	public List<Product> fetchProductbyCategoryId(@PathVariable long categoryId)	
//	{
//		return productService.fetchProductbyCategoryId(categoryId);
//	}
//	
	@GetMapping(value = "/retailerlogin/{email}/{password}")
	public long retailerLogin(@PathVariable String email,@PathVariable String password)
	{
		long retailerId = retailerService.retailerLogin(email, password);
		if(retailerId==0) {
			return retailerId;
		}
		Retailer r = retailerService.fetchRetailerbyId(retailerId);
		if(r.getRetailerApproved()==true) {
			return retailerId;
		}
		return 0;
	}
	
	@PostMapping(value="/addRetailer")
	public long registerRetailer(@RequestBody Retailer retailer)
	{
			//retailer.setRetailerApproved(false);
			return retailerService.addorUpdateRetailer(retailer).getRetailerId();
	}
	
	@GetMapping(value="/fetchproductbycategoryname/{categoryName}")
	public List<Product> fetchProductByCategoryName(@PathVariable String categoryName,HttpServletRequest request)
	{
		
		List<Product> products = productService.fetchProductbyCategoryName(categoryName);
		for(Product product:products) {
			
			String projPath = request.getServletContext().getRealPath("/");
			String tempDownloadPath = projPath+"/downloads/";
			
			File f = new File(tempDownloadPath);
			if(!f.exists())
				f.mkdir();
			
			String targetFile = tempDownloadPath+product.getPath();
			System.out.println(tempDownloadPath);
			
			String uploadedImagesPath = "D:/uploads/";
			String sourceFile = uploadedImagesPath+product.getPath();
			
			try {
				FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
				System.out.println("done");
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println("not done");
			}
			
		}
		
		return products;
		//return productService.fetchProductbyCategoryName(categoryName.toLowerCase());
	}
	
	@GetMapping(value="/pricehightolow/{categoryName}")
	public List<Product> fetchProductbyPriceHightoLow(@PathVariable String categoryName, HttpServletRequest request)
	{
		long cId=categoryService.fetchCategoryIdbyName(categoryName).getCategoryId();
		List<Product> products = productService.fetchProductbyPriceHightoLow(cId);
		for(Product product:products) {
			
			String projPath = request.getServletContext().getRealPath("/");
			String tempDownloadPath = projPath+"/downloads/";
			
			File f = new File(tempDownloadPath);
			if(!f.exists())
				f.mkdir();
			
			String targetFile = tempDownloadPath+product.getPath();
			System.out.println(tempDownloadPath);
			
			String uploadedImagesPath = "D:/uploads/";
			String sourceFile = uploadedImagesPath+product.getPath();
			
			try {
				FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
				System.out.println("done");
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println("not done");
			}
			
		}
		
		return products;
	}
	
	@GetMapping(value="/pricelowtohigh/{categoryName}")
	public List<Product> fetchProductbyPriceLowtoHigh(@PathVariable String categoryName,HttpServletRequest request)
	{
		long cId=categoryService.fetchCategoryIdbyName(categoryName).getCategoryId();
		List<Product> products = productService.fetchProductbyPriceLowtoHigh(cId);
		for(Product product:products) {
			
			String projPath = request.getServletContext().getRealPath("/");
			String tempDownloadPath = projPath+"/downloads/";
			
			File f = new File(tempDownloadPath);
			if(!f.exists())
				f.mkdir();
			
			String targetFile = tempDownloadPath+product.getPath();
			System.out.println(tempDownloadPath);
			
			String uploadedImagesPath = "D:/uploads/";
			String sourceFile = uploadedImagesPath+product.getPath();
			
			try {
				FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
				System.out.println("done");
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println("not done");
			}
			
		}
		
		return products;
	}
	
	
	
	@GetMapping(value="/categoryId/{cn}")
	public long fetchCategoryidbyName(@PathVariable String cn)
	{
		return categoryService.fetchCategoryIdbyName(cn).getCategoryId();
	}
	
	@GetMapping(value="/fetchproductbyretailerId/{retailerId}")
	public List<Product> fetchProductByRetailierId(@PathVariable long retailerId,HttpServletRequest request)
	{
		
		List<Product> products = retailerService.fetchProductByRetailerId(retailerId);
		for(Product product:products) {
			
			String projPath = request.getServletContext().getRealPath("/");
			String tempDownloadPath = projPath+"/downloads/";
			
			File f = new File(tempDownloadPath);
			if(!f.exists())
				f.mkdir();
			
			String targetFile = tempDownloadPath+product.getPath();
			System.out.println(tempDownloadPath);
			
			String uploadedImagesPath = "D:/uploads/";
			String sourceFile = uploadedImagesPath+product.getPath();
			
			try {
				FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
				System.out.println("done");
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println("not done");
			}
			
		}
		
		return products;
		//return productService.fetchProductbyCategoryName(categoryName.toLowerCase());
	}
	
}

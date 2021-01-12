package com.lti.resource;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
		Retailer r=retailerService.fetchRetailerbyId(productdto.getRetailerId());
		Category c=categoryService.fetchCategorybyId(productdto.getCategoryId());
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
	
	@GetMapping(value="/productbyPrice/{minPrice}/{maxPrice}")
	public List<Product> fetchProductbyPrice(@PathVariable double minPrice,@PathVariable double maxPrice)	
	{
		return productService.fetchProductbyPrice(minPrice, maxPrice);
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
		return retailerService.retailerLogin(email, password);
	}
	
	@PostMapping(value="/addRetailer")
	public long registerRetailer(@RequestBody Retailer retailer)
	{
			//retailer.setRetailerApproved(false);
			return retailerService.addorUpdateRetailer(retailer).getRetailerId();
	}
	
	
	
}

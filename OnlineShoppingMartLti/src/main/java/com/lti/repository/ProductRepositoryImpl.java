package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lti.entity.Category;
import com.lti.entity.Product;
import com.lti.entity.Retailer;

@Component
public class ProductRepositoryImpl implements ProductRepository {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Product fetchProductbyProductId(long productId) {
		String jpql="select p from Product p where productId=:pId";
		Query query=em.createQuery(jpql);
		query.setParameter("pId", productId);
		Product p=(Product) query.getSingleResult();
		return p;
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public List<Product> fetchProductbyCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		Category category=em.find(Category.class, categoryId);
		return category.getProduct();
	}

	@Transactional
	public List<Product> fetchProductbyPrice(double minPrice, double maxPrice,long cid) {
		// TODO Auto-generated method stub
		
		
		String jpql="select p from Product p where productPrice>=:min and productPrice<=:max and category_Id=:c and approved=true";
		TypedQuery<Product> query= em.createQuery(jpql,Product.class);
		query.setParameter("min",minPrice);
		query.setParameter("max",maxPrice);
		query.setParameter("c", cid);
		List<Product> p= query.getResultList();
		return p;
	}

	@Transactional
	public List<Product> fetchProductbyBrand(String brand) {
		// TODO Auto-generated method stub
		String jpql="select p from Product p where brand=:pBrand";
		Query query=em.createQuery(jpql);
		query.setParameter("pBrand",brand);
		List<Product> p= query.getResultList();
		return p;
		
	}

	@Transactional
	public Product addorUpdateProductbyRetailer(Product product) {
		// TODO Auto-generated method stub
		String pname=product.getProductName();
		product.setProductName(pname.toLowerCase());
		Product p=em.merge(product);
		return p;
	}

	@Transactional
	public Product removeProductbyRetailer(long productId) {
		// TODO Auto-generated method stub
		Product product=em.find(Product.class, productId);
		em.remove(product);
		return product;
	}

	@Transactional
	public List<Product> fetchProductbyProductbyName(String productName) {
		// TODO Auto-generated method stub
		String jpql="select p from Product p where productName=:pName";
		Query query=em.createQuery(jpql);
		query.setParameter("pName",productName);
		List<Product> p= query.getResultList();
		return p;
	}

	@Transactional
	public List<Product> fetchProductbyCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		//String jpql="select c from Category c where categoryName=:cn";
		String jpql = "select p from Product p where p.category.categoryName=:cn and approved=1";
		Query query=em.createQuery(jpql);
		query.setParameter("cn",categoryName);
		List<Product> p = query.getResultList();
		return p;
		//Category c=(Category) query.getSingleResult();
		//return c.getProduct();
	}

	@Transactional
	public List<Product> fetchProductbyPriceHightoLow(long cid) {
		// TODO Auto-generated method stub
		String jpql="select p from Product p where category_Id=:c and approved=1 order by productPrice desc";
		TypedQuery<Product> query= em.createQuery(jpql,Product.class);
		query.setParameter("c", cid);
		List<Product> p= query.getResultList();
		return p;
	}

	@Transactional
	public List<Product> fetchProductbyPriceLowtoHigh(long cid) {
		// TODO Auto-generated method stub
		String jpql="select p from Product p where category_Id=:c and approved=true order by productPrice";
		TypedQuery<Product> query= em.createQuery(jpql,Product.class);
		//Query query=em.createQuery(jpql);
		query.setParameter("c", cid);
		List<Product> p=(List<Product>) query.getResultList();
		return p;
	}

}

package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.lti.entity.Category;

@Component
public class CategoryRepositoryImpl implements CategoryRepository{
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Category addorupdateCategory(Category category) {
		// TODO Auto-generated method stub
		category.setCategoryName(category.getCategoryName().toLowerCase());
		Category c=em.merge(category);
		return c;
	}

	@Transactional
	public Category fetchCategorybyId(long categoryId) {
		// TODO Auto-generated method stub
		Category c=em.find(Category.class, categoryId);
		return c;
	}
	
	@Transactional
	public Category fetchCategoryIdbyName(String name) {
	// TODO Auto-generated method stub\
	String jpql="select c from Category c where categoryName=:cn";
	Query query=em.createQuery(jpql);
	query.setParameter("cn", name);
	Category c=(Category) query.getSingleResult();
	return c;
	}
	
	
}

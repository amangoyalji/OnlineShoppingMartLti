package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
		Category c=em.merge(category);
		return c;
	}

	@Transactional
	public Category fetchCategorybyId(long categoryId) {
		// TODO Auto-generated method stub
		Category c=em.find(Category.class, categoryId);
		return c;
	}
	
	
}

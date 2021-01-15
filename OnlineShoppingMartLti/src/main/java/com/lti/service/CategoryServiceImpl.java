package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Category;
import com.lti.repository.CategoryRepository;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public Category addorupdateCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.addorupdateCategory(category);
	}
	@Override
	public Category fetchCategorybyId(long categoryId) {
		// TODO Auto-generated method stub
		return categoryRepository.fetchCategorybyId(categoryId);
	}
	
	@Override
	public Category fetchCategoryIdbyName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.fetchCategoryIdbyName(name);
	}

}

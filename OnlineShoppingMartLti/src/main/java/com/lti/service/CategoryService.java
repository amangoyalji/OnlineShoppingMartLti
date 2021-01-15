package com.lti.service;

import com.lti.entity.Category;

public interface CategoryService {

	Category addorupdateCategory(Category category);
	Category fetchCategorybyId(long categoryId);
	Category fetchCategoryIdbyName(String name);
}

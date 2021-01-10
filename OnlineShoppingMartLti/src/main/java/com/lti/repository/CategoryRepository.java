package com.lti.repository;

import com.lti.entity.Category;

public interface CategoryRepository {

	Category addorupdateCategory(Category category);
	Category fetchCategorybyId(long categoryId);
}

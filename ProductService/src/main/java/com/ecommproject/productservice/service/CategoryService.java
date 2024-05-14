package com.ecommproject.productservice.service;

import com.ecommproject.productservice.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category createCategory(Category category);
}

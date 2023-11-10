package com.example.drone_shop_project_backend.service;

import com.example.drone_shop_project_backend.model.dto.category.CategoryDto;


public interface CategoryService {

  CategoryDto postCategory(CategoryDto categoryDto);

  CategoryDto getCategory(Long itemId);

  CategoryDto putCategory(CategoryDto categoryDto);

  void deleteCategory(Long itemId);
}

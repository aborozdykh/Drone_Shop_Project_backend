package com.example.drone_shop_project_backend.service.impl;

import com.example.drone_shop_project_backend.model.Category;
import com.example.drone_shop_project_backend.model.dto.category.CategoryDto;
import com.example.drone_shop_project_backend.model.mapper.CategoryMapper;
import com.example.drone_shop_project_backend.repository.helper.CategoryRepositoryHelper;
import com.example.drone_shop_project_backend.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private static final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

  private final CategoryRepositoryHelper categoryRepositoryHelper;

  @Override
  public CategoryDto postCategory(CategoryDto categoryDto) {
    Category category = categoryMapper.toCategory(categoryDto);
    Category savedCategory = categoryRepositoryHelper.save(category);
    return categoryMapper.toDto(savedCategory);
  }

  @Override
  public CategoryDto getCategory(Long itemId) {
    Category category = categoryRepositoryHelper.findById(itemId);
    return categoryMapper.toDto(category);
  }

  @Override
  public CategoryDto putCategory(CategoryDto categoryDto) {
    Category category = categoryRepositoryHelper.findById(categoryDto.getCategoryId());
    categoryMapper.toCategory(categoryDto, category);
    Category savedCategory = categoryRepositoryHelper.save(category);
    return categoryMapper.toDto(savedCategory);
  }

  @Override
  public void deleteCategory(Long itemId) {
    categoryRepositoryHelper.deleteById(itemId);
  }
}

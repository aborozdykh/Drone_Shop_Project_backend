package com.example.drone_shop_project_backend.repository.helper;

import com.example.drone_shop_project_backend.exception.ApiException;
import com.example.drone_shop_project_backend.exception.response.ErrorType;
import com.example.drone_shop_project_backend.model.Category;
import com.example.drone_shop_project_backend.repository.CategoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CategoryRepositoryHelper {

  private final CategoryRepository categoryRepository;

  public Category findById(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ApiException(ErrorType.PRODUCT_NOT_FOUND));
  }

  public List<Category> findAllByIds(List<Long> categoryIdList) {
    try {
      return categoryRepository.findAllById(categoryIdList);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public Category save(Category category) {
    try {
      return categoryRepository.save(category);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public void deleteById(Long categoryId) {
    try {
      categoryRepository.deleteById(categoryId);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

}

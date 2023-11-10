package com.example.drone_shop_project_backend.controller;

import com.example.drone_shop_project_backend.core.URLMapping;
import com.example.drone_shop_project_backend.exception.response.ResponseHolder;
import com.example.drone_shop_project_backend.model.dto.category.CategoryDto;
import com.example.drone_shop_project_backend.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@RequestMapping(URLMapping.CATEGORIES)
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping()
  public ResponseHolder<CategoryDto> postCategory(@RequestBody CategoryDto categoryDto) {
    return new ResponseHolder<>(categoryService.postCategory(categoryDto));
  }

  @GetMapping("/{categoryId}")
  public ResponseHolder<CategoryDto> getCategory(@PathVariable("categoryId") String categoryId) {
    return new ResponseHolder<>(categoryService.getCategory(Long.parseLong(categoryId)));
  }

  @PutMapping
  public ResponseHolder<CategoryDto> putCategory(@RequestBody CategoryDto categoryDto) {
    return new ResponseHolder<>(categoryService.putCategory(categoryDto));
  }

  @DeleteMapping("/{categoryId}")
  public ResponseHolder<String> deleteCategory(@PathVariable("categoryId") String categoryId) {
    categoryService.deleteCategory(Long.parseLong(categoryId));
    return new ResponseHolder<>("Category has been successfully deleted!");
  }

}

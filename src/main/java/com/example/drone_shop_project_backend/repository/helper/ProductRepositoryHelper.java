package com.example.drone_shop_project_backend.repository.helper;

import com.example.drone_shop_project_backend.exception.ApiException;
import com.example.drone_shop_project_backend.exception.response.ErrorType;
import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.repository.ProductRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ProductRepositoryHelper {

  private final ProductRepository productRepository;

  public Product findById(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new ApiException(ErrorType.PRODUCT_NOT_FOUND));
  }

  public List<Product> findAllByIds(List<Long> productIdList) {
    try {
      return productRepository.findAllById(productIdList);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public Product save(Product product) {
    try {
      return productRepository.save(product);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public void deleteById(Long productId) {
    try {
      productRepository.deleteById(productId);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

}

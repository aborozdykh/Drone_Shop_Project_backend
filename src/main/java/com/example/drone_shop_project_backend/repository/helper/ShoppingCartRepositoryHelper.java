package com.example.drone_shop_project_backend.repository.helper;

import com.example.drone_shop_project_backend.exception.ApiException;
import com.example.drone_shop_project_backend.exception.response.ErrorType;
import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ShoppingCartRepositoryHelper {

  private final ShoppingCartRepository shoppingCartRepository;

  public ShoppingCart findById(Long shoppingCartId) {
    return shoppingCartRepository.findById(shoppingCartId)
        .orElseThrow(() -> new ApiException(ErrorType.SHOPPING_CART_NOT_FOUND));
  }

  public ShoppingCart save(ShoppingCart shoppingCart) {
    try {
      return shoppingCartRepository.save(shoppingCart);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public void deleteById(Long shoppingCartId) {
    try {
      shoppingCartRepository.deleteById(shoppingCartId);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

}

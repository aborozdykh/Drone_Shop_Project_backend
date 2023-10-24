package com.example.drone_shop_project_backend.repository.helper;

import com.example.drone_shop_project_backend.exception.ApiException;
import com.example.drone_shop_project_backend.exception.response.ErrorType;
import com.example.drone_shop_project_backend.model.ShoppingCartItem;
import com.example.drone_shop_project_backend.repository.ShoppingCartItemRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ShoppingCartItemRepositoryHelper {

  private final ShoppingCartItemRepository shoppingCartItemRepository;

  public ShoppingCartItem findById(Long shoppingCartRepositoryId) {
    return shoppingCartItemRepository.findById(shoppingCartRepositoryId)
        .orElseThrow(() -> new ApiException(ErrorType.ITEM_NOT_FOUND));
  }

  public List<ShoppingCartItem> findAllByIds(List<Long> itemsIdList) {
    try {
      return shoppingCartItemRepository.findAllById(itemsIdList);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public ShoppingCartItem save(ShoppingCartItem shoppingCartItem) {
    try {
      return shoppingCartItemRepository.save(shoppingCartItem);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public void deleteById(Long shoppingCartItemId) {
    try {
      shoppingCartItemRepository.deleteById(shoppingCartItemId);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public void deleteAllByIds(List<Long> itemIdList) {
    try {
      shoppingCartItemRepository.deleteAllById(itemIdList);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

}

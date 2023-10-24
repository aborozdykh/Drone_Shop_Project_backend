package com.example.drone_shop_project_backend.service;

import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.PutShoppingCartDto;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.ShoppingCartDto;


public interface ShoppingCartService {

  ShoppingCartDto getShoppingCart(Long shoppingCartId);

  ShoppingCartDto putShoppingCart(PutShoppingCartDto putShoppingCartDto);

  void clear(Long shoppingCartId); //remove all products from the shoppingCart

  void clear(ShoppingCart shoppingCart);
}

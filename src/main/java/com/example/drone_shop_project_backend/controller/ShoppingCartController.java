package com.example.drone_shop_project_backend.controller;

import com.example.drone_shop_project_backend.core.URLMapping;
import com.example.drone_shop_project_backend.exception.response.ResponseHolder;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.PutShoppingCartDto;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.ShoppingCartDto;
import com.example.drone_shop_project_backend.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(URLMapping.SHOPPING_CART)
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  @PutMapping()
  public ResponseHolder<ShoppingCartDto> putShoppingCart(
      @RequestBody PutShoppingCartDto putShoppingCartDto) {
    ShoppingCartDto shoppingCartDto = shoppingCartService.putShoppingCart(putShoppingCartDto);
    return new ResponseHolder<>(shoppingCartDto);
  }

  @GetMapping("/{shoppingCartId}")
  public ResponseHolder<ShoppingCartDto> getShoppingCart(
      @PathVariable("shoppingCartId") String shoppingCartId) {
    return new ResponseHolder<>(
        shoppingCartService.getShoppingCart(Long.parseLong(shoppingCartId)));
  }
}

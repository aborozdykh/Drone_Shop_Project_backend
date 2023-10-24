package com.example.drone_shop_project_backend.model.mapper;

import com.example.drone_shop_project_backend.model.OrderItem;
import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.model.ShoppingCartItem;
import com.example.drone_shop_project_backend.model.dto.product.ProductDto;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.PostShoppingCartItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ShoppingCartItemMapper {

  ShoppingCartItemMapper INSTANCE = Mappers.getMapper(ShoppingCartItemMapper.class);

  @Mapping(target = "id", ignore = true)
  ShoppingCartItem toShoppingCartItem(PostShoppingCartItemDto dto);
}

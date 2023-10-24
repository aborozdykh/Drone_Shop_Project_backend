package com.example.drone_shop_project_backend.model.mapper;

import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.ShoppingCartDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ShoppingCartMapper {

  ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);

  ShoppingCartDto toDto(ShoppingCart shoppingCart);
}

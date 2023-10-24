package com.example.drone_shop_project_backend.model.mapper;

import com.example.drone_shop_project_backend.model.OrderItem;
import com.example.drone_shop_project_backend.model.ShoppingCartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface OrderItemMapper {

  OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

  @Mapping(target = "productId", source = "productId")
  @Mapping(target = "amount", source = "amount")
  @Mapping(target = "id", ignore = true)
  OrderItem toOrderItem(ShoppingCartItem dto);
}

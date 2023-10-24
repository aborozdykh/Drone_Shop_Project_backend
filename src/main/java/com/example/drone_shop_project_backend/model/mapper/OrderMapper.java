package com.example.drone_shop_project_backend.model.mapper;

import com.example.drone_shop_project_backend.model.Order;
import com.example.drone_shop_project_backend.model.dto.order.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface OrderMapper {

  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "orderId", source = "id")
  OrderDto toDto(Order order);
}

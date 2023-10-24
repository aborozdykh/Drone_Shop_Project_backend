package com.example.drone_shop_project_backend.service;

import com.example.drone_shop_project_backend.model.dto.order.OrderDto;
import com.example.drone_shop_project_backend.model.dto.order.PostOrderDto;


public interface OrderService {

  OrderDto postOrder(PostOrderDto postOrderDto);

  OrderDto getOrder(Long orderId);

  void deleteOrder(Long orderId);
}

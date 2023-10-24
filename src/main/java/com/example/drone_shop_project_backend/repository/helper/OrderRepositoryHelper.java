package com.example.drone_shop_project_backend.repository.helper;

import com.example.drone_shop_project_backend.exception.ApiException;
import com.example.drone_shop_project_backend.exception.response.ErrorType;
import com.example.drone_shop_project_backend.model.Order;
import com.example.drone_shop_project_backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class OrderRepositoryHelper {

  private final OrderRepository orderRepository;

  public Order findById(Long orderId) {
    return orderRepository.findById(orderId)
        .orElseThrow(() -> new ApiException(ErrorType.ORDER_NOT_FOUND));
  }

  public Order save(Order order) {
    try {
      return orderRepository.save(order);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public void deleteById(Long orderId) {
    try {
      orderRepository.deleteById(orderId);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

}

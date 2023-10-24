package com.example.drone_shop_project_backend.controller;

import com.example.drone_shop_project_backend.core.URLMapping;
import com.example.drone_shop_project_backend.exception.response.ResponseHolder;
import com.example.drone_shop_project_backend.model.dto.order.OrderDto;
import com.example.drone_shop_project_backend.model.dto.order.PostOrderDto;
import com.example.drone_shop_project_backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(URLMapping.ORDERS)
public class OrderController {

  private final OrderService orderService;

  @PostMapping()
  public ResponseHolder<OrderDto> postOrder(@RequestBody PostOrderDto postOrderDto) {
    return new ResponseHolder<>(orderService.postOrder(postOrderDto));
  }

  @GetMapping("/{orderId}")
  public ResponseHolder<OrderDto> getOrder(@PathVariable("orderId") String orderId) {
    return new ResponseHolder<>(orderService.getOrder(Long.parseLong(orderId)));
  }

  @DeleteMapping("/{orderId}")
  public ResponseHolder<String> deleteOrder(@PathVariable("orderId") String orderId) {
    orderService.deleteOrder(Long.parseLong(orderId));
    return new ResponseHolder<>("Order has been successfully deleted!");
  }
}

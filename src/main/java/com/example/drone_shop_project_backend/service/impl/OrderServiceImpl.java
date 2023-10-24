package com.example.drone_shop_project_backend.service.impl;

import com.example.drone_shop_project_backend.model.Order;
import com.example.drone_shop_project_backend.model.OrderItem;
import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.model.ShoppingCartItem;
import com.example.drone_shop_project_backend.model.User;
import com.example.drone_shop_project_backend.model.dto.order.OrderDto;
import com.example.drone_shop_project_backend.model.dto.order.PostOrderDto;
import com.example.drone_shop_project_backend.model.mapper.OrderItemMapper;
import com.example.drone_shop_project_backend.model.mapper.OrderMapper;
import com.example.drone_shop_project_backend.repository.helper.OrderRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ProductRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ShoppingCartRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.UserRepositoryHelper;
import com.example.drone_shop_project_backend.service.OrderService;
import com.example.drone_shop_project_backend.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  private static final OrderMapper orderMapper = OrderMapper.INSTANCE;
  private static final OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;

  private final OrderRepositoryHelper orderRepositoryHelper;
  private final UserRepositoryHelper userRepositoryHelper;
  private final ShoppingCartRepositoryHelper shoppingCartRepositoryHelper;
  private final ProductRepositoryHelper productRepositoryHelper;
  private final ShoppingCartService shoppingCartService;

  @Override
  public OrderDto postOrder(PostOrderDto postOrderDto) {
    User user = userRepositoryHelper.findById(postOrderDto.getUserId());  // get user from db
    Order order = new Order();
    order.addUser(user);                                                  // create relations between order and user
    ShoppingCart shoppingCart = shoppingCartRepositoryHelper.findById(
        postOrderDto.getShoppingCartId());                                // get shopping cart from db


    // convert all shopping cart items to order items, and add the items to the order.
    // we need OrderItem to keep relevant order info in case we change any data from the original product.
    for (ShoppingCartItem itemDto : shoppingCart.getItems()) {
      OrderItem orderItem = orderItemMapper.toOrderItem(itemDto);
      processNameAndPrice(orderItem);
      order.addItem(orderItem);
    }

    Order savedOrder = orderRepositoryHelper.save(order);
    shoppingCartService.clear(shoppingCart);
    return orderMapper.toDto(savedOrder);
  }

  @Override
  public OrderDto getOrder(Long orderId) {
    Order order = orderRepositoryHelper.findById(orderId);
    return orderMapper.toDto(order);
  }

  @Override
  public void deleteOrder(Long orderId) {
    orderRepositoryHelper.deleteById(orderId);
  }

  private void processNameAndPrice(OrderItem orderItem) {
    Long productId = orderItem.getProductId();
    Product product = productRepositoryHelper.findById(productId);
    orderItem.setName(product.getName());
    orderItem.setPrice(product.getPrice());
  }
}

package com.example.drone_shop_project_backend.service.impl;

import com.example.drone_shop_project_backend.model.Order;
import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.model.ShoppingCartItem;
import com.example.drone_shop_project_backend.model.User;
import com.example.drone_shop_project_backend.model.dto.order.OrderDto;
import com.example.drone_shop_project_backend.model.dto.order.PostOrderDto;
import com.example.drone_shop_project_backend.repository.helper.OrderRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ProductRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ShoppingCartRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.UserRepositoryHelper;
import com.example.drone_shop_project_backend.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  @Mock
  private OrderRepositoryHelper orderRepositoryHelper;
  @Mock
  private UserRepositoryHelper userRepositoryHelper;
  @Mock
  private ShoppingCartRepositoryHelper shoppingCartRepositoryHelper;
  @Mock
  private ProductRepositoryHelper productRepositoryHelper;
  @Mock
  private ShoppingCartService shoppingCartService;

  @InjectMocks
  private OrderServiceImpl orderService;

  @BeforeEach
  void setUp() {
  }

  @Test
  void testPostOrderWhenValidInputThenReturnOrderDto() {
    // Arrange
    PostOrderDto postOrderDto = new PostOrderDto();
    postOrderDto.setShoppingCartId(1L);
    postOrderDto.setUserId(1L);

    Product product = Product.builder()
        .name("Product name")
        .price(100.0)
        .build();

    User user = new User();
    ShoppingCart shoppingCart = new ShoppingCart();

    ShoppingCartItem shoppingCartItem = ShoppingCartItem.builder()
        .productId(2L)
        .amount(1)
        .build();

    shoppingCart.addItem(shoppingCartItem);

    when(productRepositoryHelper.findById(anyLong())).thenReturn(product);
    when(userRepositoryHelper.findById(anyLong())).thenReturn(user);
    when(shoppingCartRepositoryHelper.findById(anyLong())).thenReturn(shoppingCart);
    when(orderRepositoryHelper.save(any())).thenReturn(new Order());

    // Act
    OrderDto result = orderService.postOrder(postOrderDto);

    // Assert
    assertNotNull(result);
    verify(userRepositoryHelper, times(1)).findById(anyLong());
    verify(shoppingCartRepositoryHelper, times(1)).findById(anyLong());
  }

  @Test
  void testGetOrderWhenKnownOrderIdThenReturnOrderDto() {
    // Arrange
    Long orderId = 1L;
    Order order = new Order();

    when(orderRepositoryHelper.findById(anyLong())).thenReturn(order);

    // Act
    OrderDto result = orderService.getOrder(orderId);

    // Assert
    assertNotNull(result);
    verify(orderRepositoryHelper, times(1)).findById(anyLong());
  }

  @Test
  void testDeleteOrderWhenKnownOrderIdThenDeleteOrder() {
    // Arrange
    Long orderId = 1L;

    doNothing().when(orderRepositoryHelper).deleteById(anyLong());

    // Act
    orderService.deleteOrder(orderId);

    // Assert
    verify(orderRepositoryHelper, times(1)).deleteById(anyLong());
  }
}
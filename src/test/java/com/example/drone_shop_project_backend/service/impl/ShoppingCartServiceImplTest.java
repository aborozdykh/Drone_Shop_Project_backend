package com.example.drone_shop_project_backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.model.ShoppingCartItem;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.PostShoppingCartItemDto;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.PutShoppingCartDto;
import com.example.drone_shop_project_backend.model.dto.shoppingcart.ShoppingCartDto;
import com.example.drone_shop_project_backend.repository.helper.ProductRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ShoppingCartItemRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ShoppingCartRepositoryHelper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceImplTest {

  @Mock
  private ShoppingCartRepositoryHelper shoppingCartRepositoryHelper;

  @Mock
  private ShoppingCartItemRepositoryHelper shoppingCartItemRepositoryHelper;

  @Mock
  private ProductRepositoryHelper productRepositoryHelper;

  @InjectMocks
  private ShoppingCartServiceImpl shoppingCartService;

  @Test
  void testGetShoppingCartWhenValidIdThenReturnShoppingCartDto() {
    // Arrange
    ShoppingCart shoppingCart = new ShoppingCart();
    when(shoppingCartRepositoryHelper.findById(anyLong())).thenReturn(shoppingCart);

    // Act
    ShoppingCartDto result = shoppingCartService.getShoppingCart(1L);

    // Assert
    assertEquals(shoppingCart.getId(), result.getShoppingCartId());
  }

//    @Test
//    void testPutShoppingCartWhenValidDtoThenReturnShoppingCartDto() {
//        // Arrange
//        PutShoppingCartDto putShoppingCartDto = new PutShoppingCartDto();
//        ShoppingCart shoppingCart = new ShoppingCart();
//        when(shoppingCartRepositoryHelper.findById(anyLong())).thenReturn(shoppingCart);
//
//        // Act
//        ShoppingCartDto result = shoppingCartService.putShoppingCart(putShoppingCartDto);
//
//        // Assert
//        assertEquals(shoppingCart.getId(), result.getShoppingCartId());
//    }

  @Test
  void testPutShoppingCartWhenValidDtoThenReturnShoppingCartDto() {
    // Arrange
    PostShoppingCartItemDto postShoppingCartItemDto = PostShoppingCartItemDto.builder()
        .itemId(1L)
        .productId(1L)
        .amount(2)
        .build();
    PutShoppingCartDto putShoppingCartDto = PutShoppingCartDto.builder()
        .shoppingCartId(1L)
        .items(List.of(postShoppingCartItemDto))
        .build();

    putShoppingCartDto.setItems(List.of(postShoppingCartItemDto));
    ShoppingCartItem shoppingCartItem = ShoppingCartItem.builder()
        .id(1L)
        .productId(2L)
        .amount(1)
        .build();
    ShoppingCart shoppingCart = ShoppingCart.builder()
        .id(1L)
        .items(new ArrayList<>())
        .build();
    shoppingCart.addItem(shoppingCartItem);

    Product product = Product.builder()
        .name("Product name")
        .price(100.0)
        .build();

    when(shoppingCartRepositoryHelper.findById(anyLong())).thenReturn(shoppingCart);
    when(shoppingCartRepositoryHelper.save(any(ShoppingCart.class))).thenReturn(shoppingCart);
    when(productRepositoryHelper.findById(anyLong())).thenReturn(product);

    // Act
    ShoppingCartDto result = shoppingCartService.putShoppingCart(putShoppingCartDto);

    // Assert
    assertEquals(shoppingCart.getId(), result.getShoppingCartId());
    verify(productRepositoryHelper, times(1)).findById(anyLong());
  }

  @Test
  void testClearWhenValidIdThenShoppingCartIsCleared() {
    // Arrange
    ShoppingCart shoppingCart = new ShoppingCart();
    when(shoppingCartRepositoryHelper.findById(anyLong())).thenReturn(shoppingCart);

    // Act
    shoppingCartService.clear(1L);

    // Assert
    verify(shoppingCartItemRepositoryHelper, times(1)).deleteAllByIds(anyList());
  }

//    @Test
//    void testProcessNameAndPriceWhenValidDtoThenDtoHasCorrectNameAndPrice() {
//        // Arrange
//        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
//        shoppingCartDto.setItems(Collections.singletonList(new ShoppingCartItemDto()));
//        Product product = new Product();
//        product.setName("Product");
//        product.setPrice(100.0);
//        when(productRepositoryHelper.findById(anyLong())).thenReturn(product);
//
//        // Act
//        shoppingCartService.processNameAndPrice(shoppingCartDto);
//
//        // Assert
//        assertEquals("Product", shoppingCartDto.getItems().get(0).getName());
//        assertEquals(100.0, shoppingCartDto.getItems().get(0).getPrice());
//    }
//
//    @Test
//    public void testMapToShoppingCartItemsWhenValidDtoListThenReturnShoppingCartItem() {
//        // Arrange
//        PostShoppingCartItemDto postShoppingCartItemDto = new PostShoppingCartItemDto();
//        postShoppingCartItemDto.setProductId(1L);
//        postShoppingCartItemDto.setAmount(1);
//        List<PostShoppingCartItemDto> items = Collections.singletonList(postShoppingCartItemDto);
//
//        // Act
//        List<ShoppingCartItem> result = shoppingCartService.mapToShoppingCartItems(items);
//
//        // Assert
//        assertEquals(1, result.size());
//        assertEquals(1L, result.get(0).getProductId());
//        assertEquals(1, result.get(0).getAmount());
//        assertEquals("Product", result.get(0).getName());
//        assertEquals(100.0, result.get(0).getPrice());
//    }
}

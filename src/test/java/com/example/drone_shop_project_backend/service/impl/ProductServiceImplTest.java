package com.example.drone_shop_project_backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.drone_shop_project_backend.model.Category;
import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.model.dto.product.ProductDto;
import com.example.drone_shop_project_backend.model.mapper.ProductMapper;
import com.example.drone_shop_project_backend.repository.helper.CategoryRepositoryHelper;
import com.example.drone_shop_project_backend.repository.helper.ProductRepositoryHelper;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  private ProductRepositoryHelper productRepositoryHelper;

  @Mock
  private CategoryRepositoryHelper categoryRepositoryHelper;

  @InjectMocks
  private ProductServiceImpl productService;

  private static final ProductMapper productMapper = ProductMapper.INSTANCE;

  private ProductDto productDto;
  private Product product;
  private Category category;

  @BeforeEach
  public void setUp() {
    category = Category.builder()
        .id(1L)
        .name("Test Category")
        .products(new ArrayList<>())
        .build();

    productDto = ProductDto.builder()
        .productId(1L)
        .categoryId(1L)
        .name("Test Product")
        .price(100.0)
        .description("Test Description")
        .build();

    product = productMapper.toProduct(productDto);
    product.setId(1L);
    product.addCategory(category);


  }

  @Test
  void testPostProductWhenProductSavedThenReturnProductDto() {
    when(productRepositoryHelper.save(any(Product.class))).thenReturn(product);
    when(categoryRepositoryHelper.findById(anyLong())).thenReturn(category);

    ProductDto result = productService.postProduct(productDto);

    verify(productRepositoryHelper, times(1)).save(any(Product.class));
    assertEquals(productDto, result);
  }

  @Test
  void testGetProductWhenProductFoundThenReturnProductDto() {
    when(productRepositoryHelper.findById(anyLong())).thenReturn(product);

    ProductDto result = productService.getProduct(1L);

    verify(productRepositoryHelper, times(1)).findById(anyLong());
    assertEquals(productDto, result);
  }

  @Test
  void testPutProductWhenProductUpdatedThenReturnProductDto() {
    when(productRepositoryHelper.findById(anyLong())).thenReturn(product);
    when(productRepositoryHelper.save(any(Product.class))).thenReturn(product);

    ProductDto result = productService.putProduct(productDto);

    verify(productRepositoryHelper, times(1)).findById(anyLong());
    verify(productRepositoryHelper, times(1)).save(any(Product.class));
    assertEquals(productDto, result);
  }

  @Test
  void testPutProductWhenProductUpdatedAndCategoryUpdatedThenReturnProductDto() {
    Category category2 = Category.builder()
        .id(2L)
        .name("Test Category")
        .products(new ArrayList<>())
        .build();

    productDto.setCategoryId(2L);


    when(productRepositoryHelper.findById(anyLong())).thenReturn(product);
    when(productRepositoryHelper.save(any(Product.class))).thenReturn(product);
    when(categoryRepositoryHelper.findById(anyLong())).thenReturn(category2);

    ProductDto result = productService.putProduct(productDto);

    verify(productRepositoryHelper, times(1)).findById(anyLong());
    verify(productRepositoryHelper, times(1)).save(any(Product.class));
    assertEquals(productDto, result);
  }

  @Test
  void testDeleteProductWhenProductDeletedThenNoException() {
    doNothing().when(productRepositoryHelper).deleteById(anyLong());

    productService.deleteProduct(1L);

    verify(productRepositoryHelper, times(1)).deleteById(anyLong());
  }
}

package com.example.drone_shop_project_backend.service;

import com.example.drone_shop_project_backend.model.dto.product.ProductDto;


public interface ProductService {

  ProductDto postProduct(ProductDto productDto);

  ProductDto getProduct(Long itemId);

  ProductDto putProduct(ProductDto productDto);

  void deleteProduct(Long itemId);
}

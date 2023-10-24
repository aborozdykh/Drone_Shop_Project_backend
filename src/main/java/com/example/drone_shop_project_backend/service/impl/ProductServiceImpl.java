package com.example.drone_shop_project_backend.service.impl;

import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.model.dto.product.ProductDto;
import com.example.drone_shop_project_backend.model.mapper.ProductMapper;
import com.example.drone_shop_project_backend.repository.helper.ProductRepositoryHelper;
import com.example.drone_shop_project_backend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

  private static final ProductMapper productMapper = ProductMapper.INSTANCE;

  private final ProductRepositoryHelper productRepositoryHelper;

  @Override
  public ProductDto postProduct(ProductDto productDto) {
    Product product = productMapper.toProduct(productDto);
    Product savedProduct = productRepositoryHelper.save(product);
    return productMapper.toDto(savedProduct);
  }

  @Override
  public ProductDto getProduct(Long itemId) {
    Product product = productRepositoryHelper.findById(itemId);
    return productMapper.toDto(product);
  }

  @Override
  public ProductDto putProduct(ProductDto productDto) {
    Product product = productRepositoryHelper.findById(productDto.getProductId());
    productMapper.toProduct(productDto, product);
    Product savedProduct = productRepositoryHelper.save(product);
    return productMapper.toDto(savedProduct);
  }

  @Override
  public void deleteProduct(Long itemId) {
    productRepositoryHelper.deleteById(itemId);
  }
}

package com.example.drone_shop_project_backend.controller;

import com.example.drone_shop_project_backend.core.URLMapping;
import com.example.drone_shop_project_backend.exception.response.ResponseHolder;
import com.example.drone_shop_project_backend.model.dto.product.ProductDto;
import com.example.drone_shop_project_backend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(URLMapping.PRODUCTS)
public class ProductController {

  private final ProductService productService;

  @PostMapping()
  public ResponseHolder<ProductDto> postProduct(@RequestBody ProductDto productDto) {
    return new ResponseHolder<>(productService.postProduct(productDto));
  }

  @GetMapping("/{productId}")
  public ResponseHolder<ProductDto> getProduct(@PathVariable("productId") String productId) {
    return new ResponseHolder<>(productService.getProduct(Long.parseLong(productId)));
  }

  @PutMapping
  public ResponseHolder<ProductDto> putProduct(@RequestBody ProductDto productDto) {
    return new ResponseHolder<>(productService.putProduct(productDto));
  }

  @DeleteMapping("/{productId}")
  public ResponseHolder<String> deleteProduct(@PathVariable("productId") String productId) {
    productService.deleteProduct(Long.parseLong(productId));
    return new ResponseHolder<>("Product has been successfully deleted!");
  }
}

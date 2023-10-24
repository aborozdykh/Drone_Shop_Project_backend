package com.example.drone_shop_project_backend.model.mapper;

import com.example.drone_shop_project_backend.model.Product;
import com.example.drone_shop_project_backend.model.dto.product.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(target = "productId", source = "id")
  ProductDto toDto(Product product);

  Product toProduct(ProductDto dto);

  @Mapping(target = "id", ignore = true)
  void toProduct(ProductDto dto, @MappingTarget Product product);

}

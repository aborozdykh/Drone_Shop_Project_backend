package com.example.drone_shop_project_backend.model.mapper;

import com.example.drone_shop_project_backend.model.Category;
import com.example.drone_shop_project_backend.model.dto.category.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  @Mapping(target = "categoryId", source = "id")
  CategoryDto toDto(Category category);

  @Mapping(target = "products", ignore = true)
  Category toCategory(CategoryDto dto);

  @Mapping(target = "id", ignore = true)
  void toCategory(CategoryDto dto, @MappingTarget Category category);

}

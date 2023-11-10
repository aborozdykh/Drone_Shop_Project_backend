package com.example.drone_shop_project_backend.model.dto.category;

import com.example.drone_shop_project_backend.model.dto.product.ProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

  private Long categoryId;
  private String name;
  private List<ProductDto> products;
}

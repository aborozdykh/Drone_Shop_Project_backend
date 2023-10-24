package com.example.drone_shop_project_backend.model.dto.shoppingcart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCartItemDto {

  private Long itemId;
  private Long productId;
  private Integer amount;
  private String name;
  private Double price;
}

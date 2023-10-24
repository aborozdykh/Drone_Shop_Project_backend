package com.example.drone_shop_project_backend.model.dto.shoppingcart;

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
public class ShoppingCartDto {

  private Long shoppingCartId;
  private List<ShoppingCartItemDto> items;
  private Long userId;

}

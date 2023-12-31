package com.example.drone_shop_project_backend.model.dto.orderitem;

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
public class OrderItemDto {

  private Long itemId;
  private Long productId;
  private String name;
  private Double price;
  private Integer amount;
}

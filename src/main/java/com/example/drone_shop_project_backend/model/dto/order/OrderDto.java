package com.example.drone_shop_project_backend.model.dto.order;

import com.example.drone_shop_project_backend.model.dto.orderitem.OrderItemDto;
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
public class OrderDto {

  private Long orderId;
  private List<OrderItemDto> items;
  private String userId;
}

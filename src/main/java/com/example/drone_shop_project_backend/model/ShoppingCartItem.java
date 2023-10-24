package com.example.drone_shop_project_backend.model;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "shopping_cart_items")
@DynamicUpdate
public class ShoppingCartItem extends BaseEntity {

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "shopping_cart_id")
  private ShoppingCart shoppingCart;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "amount", nullable = false)
  private Integer amount;
}

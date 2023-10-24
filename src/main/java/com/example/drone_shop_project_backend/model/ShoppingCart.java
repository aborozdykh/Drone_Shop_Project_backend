package com.example.drone_shop_project_backend.model;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "shopping_carts")
@DynamicUpdate
public class ShoppingCart extends BaseEntity {

  @OneToMany(fetch = LAZY,
      mappedBy = "shoppingCart",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<ShoppingCartItem> items = new ArrayList<>();

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public void addItem(ShoppingCartItem shoppingCartItem) {
    items.add(shoppingCartItem);
    shoppingCartItem.setShoppingCart(this);
  }

  public void removeItem(ShoppingCartItem shoppingCartItem) {
    items.remove(shoppingCartItem);
    shoppingCartItem.setShoppingCart(null);
  }
}

package com.example.drone_shop_project_backend.model;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "users")
@DynamicUpdate
public class User extends BaseEntity {

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @OneToOne(mappedBy = "user",
      cascade = CascadeType.ALL,
      optional = false)
  private ShoppingCart shoppingCart;

  @OneToMany(fetch = LAZY,
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<Order> orders;

  public void addShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
    shoppingCart.setUser(this);
  }

  public void addOrder(Order order) {
    this.getOrders().add(order);
    order.setUser(this);
  }

  public void removeOrder(Order order) {
    this.getOrders().remove(order);
    order.setUser(null);
  }
}

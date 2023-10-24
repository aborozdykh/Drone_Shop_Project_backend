package com.example.drone_shop_project_backend.model;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "orders")
@DynamicUpdate
public class Order extends BaseEntity {

  @OneToMany(fetch = LAZY,
      mappedBy = "order",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public void addUser(User user) {
    this.setUser(user);
    user.getOrders().add(this);
  }

  public void removeUser(User user) {
    this.setUser(null);
    user.getOrders().remove(this);
  }

  public void addItem(OrderItem item) {
    this.getItems().add(item);
    item.setOrder(this);
  }

  public void removeItem(OrderItem item) {
    this.getItems().remove(item);
    item.setOrder(null);
  }
}

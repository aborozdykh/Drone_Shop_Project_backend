package com.example.drone_shop_project_backend.model;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "categories")
@DynamicUpdate
public class Category extends BaseEntity{

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(fetch = LAZY,
      mappedBy = "category",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<Product> products = new ArrayList<>();

  public void addProduct(Product product) {
    products.add(product);
    product.setCategory(this);
  }

  public void removeProduct(Product product) {
    products.remove(product);
    product.setCategory(null);
  }
}

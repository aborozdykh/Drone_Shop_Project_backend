package com.example.drone_shop_project_backend.repository;

import com.example.drone_shop_project_backend.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

}

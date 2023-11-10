package com.example.drone_shop_project_backend.repository;

import com.example.drone_shop_project_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}

package com.example.drone_shop_project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.drone_shop_project_backend.model")
public class DroneShopProjectBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(DroneShopProjectBackendApplication.class, args);
  }

}

package com.example.drone_shop_project_backend.exception.response;


import static com.example.drone_shop_project_backend.exception.response.HttpStatusConstant.BAD_REQUEST;
import static com.example.drone_shop_project_backend.exception.response.HttpStatusConstant.FORBIDDEN;
import static com.example.drone_shop_project_backend.exception.response.HttpStatusConstant.INTERNAL_SERVER_ERROR;
import static com.example.drone_shop_project_backend.exception.response.HttpStatusConstant.NOT_FOUND;
import static com.example.drone_shop_project_backend.exception.response.HttpStatusConstant.UNAUTHORIZED;
import static com.example.drone_shop_project_backend.exception.response.Severity.ERROR;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

  UNKNOWN_USER(ERROR, UNAUTHORIZED, "Unknown user"),
  USER_UNAUTHORIZED(ERROR, UNAUTHORIZED, "Unauthorized"),
  DATABASE_ERROR(ERROR, INTERNAL_SERVER_ERROR, "Database error"),
  SERVER_ERROR(ERROR, INTERNAL_SERVER_ERROR, "Database error"),
  INVALID_REFRESH_TOKEN(ERROR, BAD_REQUEST, "Invalid refresh token"),
  REFRESH_TOKEN_EXPIRED(ERROR, UNAUTHORIZED,
      "Refresh token was expired. Please make a new login request"),
  REFRESH_TOKEN_NOT_FOUND(ERROR, UNAUTHORIZED, "Refresh token not found"),
  USER_NOT_FOUND(ERROR, NOT_FOUND, "User not found"),
  ACCESS_DENIED(ERROR, FORBIDDEN, "Access denied"),
  ITEM_NOT_FOUND(ERROR, NOT_FOUND, "Item not found"),
  PRODUCT_NOT_FOUND(ERROR, NOT_FOUND, "Product not found"),
  SHOPPING_CART_NOT_FOUND(ERROR, NOT_FOUND, "Shopping cart not found"),
  ORDER_NOT_FOUND(ERROR, NOT_FOUND, "Order not found");

  private HttpStatus httpStatus;
  private String message;
  private String status;
  private Severity type;

  ErrorType(Severity type, String status, String message) {
    this.httpStatus = HttpStatus.OK;
    this.type = type;
    this.status = status;
    this.message = message;
  }
}

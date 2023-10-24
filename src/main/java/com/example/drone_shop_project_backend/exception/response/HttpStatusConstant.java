package com.example.drone_shop_project_backend.exception.response;

import org.springframework.http.HttpStatus;


public interface HttpStatusConstant {

  String BAD_REQUEST = String.valueOf(HttpStatus.BAD_REQUEST.value());
  String FORBIDDEN = String.valueOf(HttpStatus.FORBIDDEN.value());
  String NOT_FOUND = String.valueOf(HttpStatus.NOT_FOUND.value());
  String CONFLICT = String.valueOf(HttpStatus.CONFLICT.value());
  String INTERNAL_SERVER_ERROR = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
  String UNAUTHORIZED = String.valueOf(HttpStatus.UNAUTHORIZED.value());
}

package com.example.drone_shop_project_backend.service;

import com.example.drone_shop_project_backend.model.dto.user.UserDto;


public interface UserService {

  UserDto postUser(UserDto userDto);

  UserDto getUser(Long userId);

  UserDto putUser(UserDto userDto);

  void deleteUser(Long userId);
}

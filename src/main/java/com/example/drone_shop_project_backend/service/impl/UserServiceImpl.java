package com.example.drone_shop_project_backend.service.impl;

import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.model.User;
import com.example.drone_shop_project_backend.model.dto.user.UserDto;
import com.example.drone_shop_project_backend.model.mapper.UserMapper;
import com.example.drone_shop_project_backend.repository.helper.UserRepositoryHelper;
import com.example.drone_shop_project_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private static final UserMapper userMapper = UserMapper.INSTANCE;

  private final UserRepositoryHelper userRepositoryHelper;

  // Method to create a user
  @Override
  public UserDto postUser(UserDto userDto) {
    User user = userMapper.toUser(userDto);           // Creating a user from a data transfer object
    user.addShoppingCart(new ShoppingCart());         // Adding a shopping cart to the user
    User savedUser = userRepositoryHelper.save(user); // Saving the user to the database
    return userMapper.toDto(savedUser);
  }

  @Override
  public UserDto getUser(Long userId) {
    User user = userRepositoryHelper.findById(userId);  // Getting a user from the database
    return userMapper.toDto(user);                      // Mapping a user to a data transfer object
  }

  @Override
  public UserDto putUser(UserDto userDto) {
    User user = userRepositoryHelper.findById(userDto.getUserId()); // Getting a user from the database
    userMapper.toUser(userDto, user);                               // Update user
    User savedUser = userRepositoryHelper.save(user);               // Save user to database
    return userMapper.toDto(savedUser);                             // Mapping a user to a data transfer object
  }

  @Override
  public void deleteUser(Long userId) {
    userRepositoryHelper.deleteById(userId);
  }
}

package com.example.drone_shop_project_backend.controller;

import com.example.drone_shop_project_backend.core.URLMapping;
import com.example.drone_shop_project_backend.exception.response.ResponseHolder;
import com.example.drone_shop_project_backend.model.dto.user.UserDto;
import com.example.drone_shop_project_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(URLMapping.USERS)
public class UserController {

  private final UserService userService;

  @PostMapping()
  public ResponseHolder<UserDto> postUser(@RequestBody UserDto userDto) {
    return new ResponseHolder<>(userService.postUser(userDto));
  }

  @GetMapping("/{userId}")
  public ResponseHolder<UserDto> getUser(@PathVariable("userId") String userId) {
    return new ResponseHolder<>(userService.getUser(Long.parseLong(userId)));
  }

  @PutMapping
  public ResponseHolder<UserDto> putUser(@RequestBody UserDto userDto) {
    return new ResponseHolder<>(userService.putUser(userDto));
  }

  @DeleteMapping("/{userId}")
  public ResponseHolder<String> deleteUser(@PathVariable("userId") String userId) {
    userService.deleteUser(Long.parseLong(userId));
    return new ResponseHolder<>("User has been successfully deleted!");
  }
}

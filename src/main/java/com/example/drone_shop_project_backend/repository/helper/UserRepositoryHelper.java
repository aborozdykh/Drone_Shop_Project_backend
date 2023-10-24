package com.example.drone_shop_project_backend.repository.helper;

import com.example.drone_shop_project_backend.exception.ApiException;
import com.example.drone_shop_project_backend.exception.response.ErrorType;
import com.example.drone_shop_project_backend.model.User;
import com.example.drone_shop_project_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserRepositoryHelper {

  private final UserRepository userRepository;

  public User findById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
  }

  public User save(User user) {
    try {
      return userRepository.save(user);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }

  public void deleteById(Long userId) {
    try {
      userRepository.deleteById(userId);
    } catch (Exception e) {
      throw new ApiException(ErrorType.DATABASE_ERROR);
    }
  }
}

package com.example.drone_shop_project_backend.model.mapper;

import com.example.drone_shop_project_backend.model.User;
import com.example.drone_shop_project_backend.model.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "userId", source = "id")
  @Mapping(target = "shoppingCartId", source = "shoppingCart.id")
  UserDto toDto(User user);

  @Mapping(target = "id", ignore = true)
  User toUser(UserDto dto);

  @Mapping(target = "id", ignore = true)
  void toUser(UserDto dto, @MappingTarget User user);
}

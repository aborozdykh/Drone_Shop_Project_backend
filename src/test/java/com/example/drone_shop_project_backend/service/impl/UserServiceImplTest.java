package com.example.drone_shop_project_backend.service.impl;

import com.example.drone_shop_project_backend.model.ShoppingCart;
import com.example.drone_shop_project_backend.model.User;
import com.example.drone_shop_project_backend.model.dto.user.UserDto;
import com.example.drone_shop_project_backend.model.mapper.UserMapper;
import com.example.drone_shop_project_backend.repository.helper.UserRepositoryHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepositoryHelper userRepositoryHelper;

    @InjectMocks
    private UserServiceImpl userService;

    private static final UserMapper userMapper = UserMapper.INSTANCE;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostUserWhenValidUserDtoThenReturnUserDto() {
        UserDto userDto = UserDto.builder().userId(1L).email("test@test.com").firstName("Test").lastName("User").build();
        User user = userMapper.toUser(userDto);
        user.addShoppingCart(new ShoppingCart());

        when(userRepositoryHelper.save(any(User.class))).thenReturn(user);

        UserDto result = userService.postUser(userDto);

        assertNotNull(result);
        assertEquals(userDto.getEmail(), result.getEmail());
        assertEquals(userDto.getFirstName(), result.getFirstName());
        assertEquals(userDto.getLastName(), result.getLastName());
    }

    @Test
    void testGetUserWhenValidUserIdThenReturnUserDto() {
        User user = User.builder().id(1L).email("test@test.com").firstName("Test").lastName("User").build();

        when(userRepositoryHelper.findById(anyLong())).thenReturn(user);

        UserDto result = userService.getUser(1L);

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
    }

    @Test
    void testPutUserWhenValidUserDtoThenReturnUpdatedUserDto() {
        UserDto userDto = UserDto.builder().userId(1L).email("updated@test.com").firstName("Updated").lastName("User").build();
        User user = userMapper.toUser(userDto);

        when(userRepositoryHelper.findById(anyLong())).thenReturn(user);
        when(userRepositoryHelper.save(any(User.class))).thenReturn(user);

        UserDto result = userService.putUser(userDto);

        assertNotNull(result);
        assertEquals(userDto.getEmail(), result.getEmail());
        assertEquals(userDto.getFirstName(), result.getFirstName());
        assertEquals(userDto.getLastName(), result.getLastName());
    }

    @Test
    void testDeleteUserWhenValidUserIdThenDeleteUser() {
        doNothing().when(userRepositoryHelper).deleteById(anyLong());

        userService.deleteUser(1L);

        verify(userRepositoryHelper, times(1)).deleteById(anyLong());
    }
}

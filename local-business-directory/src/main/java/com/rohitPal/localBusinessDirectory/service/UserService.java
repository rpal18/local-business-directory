package com.rohitPal.localBusinessDirectory.service;

import com.rohitPal.localBusinessDirectory.dto.UserDto;
import com.rohitPal.localBusinessDirectory.dto.UserEmailDto;
import com.rohitPal.localBusinessDirectory.dto.UserResponse;

public interface UserService {
    UserResponse addUser(UserDto userDto);
    UserResponse deleteUser(Long id);
    UserResponse updateUserEmail(Long id , UserEmailDto userEmailDto);
    UserResponse updateAddress(Long id , String address);
    UserResponse getUserById(Long id);
}

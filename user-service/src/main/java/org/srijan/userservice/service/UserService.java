package org.srijan.userservice.service;


import org.srijan.userservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto getUserById(Integer userId);

    int getUserIdByEmail(String email);
}

package org.srijan.userservice.service.impl;


import org.srijan.userservice.dto.UserResponseDto;
import org.srijan.userservice.entity.User;
import org.srijan.userservice.repository.UserRepository;
import org.srijan.userservice.service.UserService;
import org.srijan.userservice.shared.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto getUserById(Integer userId) {
        User user=userRepository.findById(userId).get();
        UserResponseDto userResponseDtoFromUser=modelMapper.map(user,UserResponseDto.class);
        return userResponseDtoFromUser;
    }

    @Override
    public int getUserIdByEmail(String email) {
        User user=userRepository.findByEmail(email).orElseThrow(()
                ->new UserNotFoundException("User not found"));
        return user.getId();
    }
}

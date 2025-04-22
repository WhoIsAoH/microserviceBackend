package org.srijan.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.srijan.userservice.dto.AuthenticationRequestDto;
import org.srijan.userservice.dto.AuthenticationResponseDto;
import org.srijan.userservice.dto.RegisterRequestDto;
import org.srijan.userservice.dto.UserResponseDto;
import org.srijan.userservice.service.AuthenticationService;
import org.srijan.userservice.service.UserService;
import org.srijan.userservice.shared.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody @Validated RegisterRequestDto request){
        return service.register(request);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponseDto authenticate(
            @RequestBody AuthenticationRequestDto request
    ){
        return service.authenticate(request);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Integer id) {
        UserResponseDto userResponseDto=userService.getUserById(id);
        return userResponseDto;
    }
    @GetMapping("/getUserByEmail")
    public int getUserByEmail(@RequestParam String email) {
        int userId=userService.getUserIdByEmail(email);
        return userId;
    }
}

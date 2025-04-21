package org.srijan.userservice.service;

import org.srijan.userservice.dto.AuthenticationRequestDto;
import org.srijan.userservice.dto.AuthenticationResponseDto;
import org.srijan.userservice.dto.RegisterRequestDto;
import org.srijan.userservice.entity.Role;
import org.srijan.userservice.entity.User;
import org.srijan.userservice.repository.UserRepository;
import org.srijan.userservice.shared.MailService;
import org.srijan.userservice.shared.MessageConstant;
import org.srijan.userservice.shared.UserResponse;
import org.srijan.userservice.shared.exception.UserAlreadyExistException;
import org.srijan.userservice.shared.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;

    public UserResponse register(RegisterRequestDto request) {
            Optional<User> optionalUser = repository.findByEmail(request.getEmail());
            if (optionalUser.isPresent()) {
                log.info("user registered already");
                throw new UserAlreadyExistException(MessageConstant.ALREADY_REGISTER_USER);
            }
                log.info("NoDuplicateEmail");
                var user = User.builder()
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .email(request.getEmail())
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.CLIENT)
                        .build();
                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                AuthenticationResponseDto jwt = new AuthenticationResponseDto(jwtToken);
//                return ResponseEntity.ok(jwtToken);
        mailService.sendEmail(user.getEmail(), MessageConstant.ACCOUNT_CREATION_SUCCESSFUL, MessageConstant.ACCOUNT_CREATE_BODY);

                return new UserResponse(MessageConstant.SAVED_SUCCESSFULLY);
    }
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        log.info("login or authenticating");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(()->{
                    log.error("User Not Found");
                    throw new UserNotFoundException("User Not Found!! Create user first");
                            }
                        );
        var jwtToken = jwtService.generateToken(user);
//        log.info();
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}

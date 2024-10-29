package com.example.EzyStocks.services.impl;

import com.example.EzyStocks.dtos.LoginDto;
import com.example.EzyStocks.dtos.LoginResponseDto;
import com.example.EzyStocks.dtos.SignUpDto;
import com.example.EzyStocks.dtos.UserDto;
import com.example.EzyStocks.entities.UserEntity;
import com.example.EzyStocks.repositories.UserRepository;
import com.example.EzyStocks.services.AuthService;
import com.example.EzyStocks.services.JwtService;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtService jwtService, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        // Log before authentication
        System.out.println("Attempting to authenticate user: " + loginDto.getUsername());

        Authentication authentication = null;
        try {
            // Attempt authentication
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
            // Log success
            System.out.println("Authentication successful for user: " + loginDto.getUsername());
        } catch (Exception e) {
            // Log failure
            System.err.println("Authentication failed: " + e.getMessage());
            throw e;
        }

        // Extract authenticated user details
        UserEntity user = (UserEntity) authentication.getPrincipal();
        System.out.println("Authenticated user name: " + user.getUsername());

        // Generate access and refresh tokens
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }


    @Override
    public UserDto signUp(SignUpDto signUpDto) throws BadRequestException {
        // Check if user already exists
        Optional<UserEntity> user = userRepository.findByUsername(signUpDto.getUsername());
        if (user.isPresent()) {
            throw new BadRequestException("User already exists: " + signUpDto.getUsername());
        }

        // Map SignUpDto to UserEntity and encode password
        UserEntity newUser = modelMapper.map(signUpDto, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // Save the new user in the database
        userRepository.save(newUser);

        // Convert saved UserEntity to UserDto and return
        return modelMapper.map(newUser, UserDto.class);
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        // Validate refresh token
        if (jwtService.validateToken(refreshToken)) {
            // Retrieve user information using token
            Long userId = jwtService.getUserIdFromToken(refreshToken);
            UserEntity user = userService.getUserById(userId);

            // Generate a new access token
            String accessToken = jwtService.generateAccessToken(user);
            return new LoginResponseDto(user.getId(), accessToken, refreshToken);
        }

        // Throw exception if refresh token is invalid
        throw new AuthenticationCredentialsNotFoundException("Refresh token not valid");
    }
}


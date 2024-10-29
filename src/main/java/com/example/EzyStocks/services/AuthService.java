package com.example.EzyStocks.services;

import com.example.EzyStocks.dtos.LoginDto;
import com.example.EzyStocks.dtos.LoginResponseDto;
import com.example.EzyStocks.dtos.SignUpDto;
import com.example.EzyStocks.dtos.UserDto;
import org.apache.coyote.BadRequestException;

public interface AuthService {
    LoginResponseDto login(LoginDto loginDto);

    UserDto signUp(SignUpDto signUpDto) throws BadRequestException;
}

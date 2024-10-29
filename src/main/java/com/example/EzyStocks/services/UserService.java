package com.example.EzyStocks.services;

import com.example.EzyStocks.dtos.LoginDto;
import com.example.EzyStocks.dtos.LoginResponseDto;
import com.example.EzyStocks.dtos.SignUpDto;
import com.example.EzyStocks.dtos.UserDto;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
}

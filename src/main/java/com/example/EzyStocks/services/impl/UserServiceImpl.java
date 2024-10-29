package com.example.EzyStocks.services.impl;

import com.example.EzyStocks.dtos.TransactionDto;
import com.example.EzyStocks.dtos.UserDto;
import com.example.EzyStocks.entities.UserEntity;
import com.example.EzyStocks.exceptions.ResourceNotFoundException;
import com.example.EzyStocks.repositories.UserRepository;
import com.example.EzyStocks.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new ResourceNotFoundException("user not found with username:" + username));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity user = modelMapper.map(userDto,UserEntity.class);
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }
    public UserEntity getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User with id:"+id+"not found"));
    }
    public UserDto getAuthenticatedUserDetails() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return modelMapper.map(getUserById(user.getId()),UserDto.class);
    }
}

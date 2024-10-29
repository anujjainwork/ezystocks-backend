package com.example.EzyStocks.config;

import com.example.EzyStocks.dtos.StockDto;
import com.example.EzyStocks.dtos.UserDto;
import com.example.EzyStocks.entities.StockEntity;
import com.example.EzyStocks.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        // Map StockEntity to StockDto with nested collections
        modelMapper.typeMap(StockEntity.class, StockDto.class).addMappings(mapper -> {
            mapper.map(StockEntity::getSymbol, StockDto::setSymbol);
            mapper.map(StockEntity::getName, StockDto::setName);
            mapper.map(StockEntity::getCurrentPrice, StockDto::setCurrentPrice);
        });
        return modelMapper;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

package com.example.EzyStocks.services.impl;

import com.example.EzyStocks.dtos.PortfolioDto;
import com.example.EzyStocks.entities.PortfolioEntity;
import com.example.EzyStocks.repositories.PortfolioRepository;
import com.example.EzyStocks.services.PortfolioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public List<PortfolioDto> getUserPortfolio(Long userId) {
        List<PortfolioEntity> userPortfolio = portfolioRepository.findAllByUserId(userId);
        return userPortfolio.stream()
                .map(portfolioEntity -> mapper.map(portfolioEntity, PortfolioDto.class))
                .collect(Collectors.toList());
    }


}

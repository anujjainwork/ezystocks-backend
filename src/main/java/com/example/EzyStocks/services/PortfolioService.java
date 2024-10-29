package com.example.EzyStocks.services;

import com.example.EzyStocks.dtos.PortfolioDto;
import com.example.EzyStocks.entities.PortfolioEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PortfolioService {

    public List<PortfolioDto> getUserPortfolio(Long userId);
}

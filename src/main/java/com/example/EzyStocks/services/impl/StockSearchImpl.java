package com.example.EzyStocks.services.impl;

import com.example.EzyStocks.dtos.PortfolioDto;
import com.example.EzyStocks.dtos.StockDto;
import com.example.EzyStocks.entities.StockEntity;
import com.example.EzyStocks.repositories.StockRepository;
import com.example.EzyStocks.services.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockSearchImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<StockDto> searchStocks(String query) {
        List<StockEntity> stockEntityList =  stockRepository.findByNameContainingIgnoreCase(query);
        return stockEntityList.stream()
                .map(stockEntity -> mapper.map(stockEntity, StockDto.class))
                .collect(Collectors.toList());
    }
}

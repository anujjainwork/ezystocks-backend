package com.example.EzyStocks.services;

import com.example.EzyStocks.dtos.StockDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {

    public List<StockDto> searchStocks(String query);
}

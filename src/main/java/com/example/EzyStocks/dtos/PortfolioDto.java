package com.example.EzyStocks.dtos;
import lombok.Data;

@Data
public class PortfolioDto {
    public Long id;
    public StockDto stock;
    public Integer quantity;
}

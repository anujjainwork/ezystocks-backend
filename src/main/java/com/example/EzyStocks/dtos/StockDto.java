package com.example.EzyStocks.dtos;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class StockDto {
    private String symbol;
    private String name;
    private BigDecimal currentPrice;
    private Set<TransactionDto> transactions;
    private Set<PortfolioDto> portfolios;
    private Set<WatchlistDto> watchlist;
}

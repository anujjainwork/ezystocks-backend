package com.example.EzyStocks.dtos;

import com.example.EzyStocks.entities.PortfolioEntity;
import com.example.EzyStocks.entities.TransactionEntity;
import com.example.EzyStocks.entities.WatchlistEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private Long id;

    private String username;

    private String password;

    private BigDecimal cashWallet;

    private Set<TransactionDto> transactions;

    private Set<PortfolioDto> portfolio;

    private Set<WatchlistDto> watchlist;

}


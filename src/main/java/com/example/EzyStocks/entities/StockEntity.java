package com.example.EzyStocks.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
public class StockEntity {
    @Id
    @Column(length = 10)
    private String symbol;

    @Column(nullable = false)
    private String name;

    @Column(precision = 12, scale = 2)
    private BigDecimal currentPrice;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private Set<TransactionEntity> transactions;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private Set<PortfolioEntity> portfolios;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private Set<WatchlistEntity> watchlist;
}

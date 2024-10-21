package com.example.EzyStocks.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "stock_symbol", nullable = false)
    private StockEntity stock;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String type;  // "BUY" or "SELL"

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

}

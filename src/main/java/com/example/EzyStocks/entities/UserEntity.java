package com.example.EzyStocks.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(precision = 12, scale = 2)
    private BigDecimal cashWallet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TransactionEntity> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PortfolioEntity> portfolio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<WatchlistEntity> watchlist;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}

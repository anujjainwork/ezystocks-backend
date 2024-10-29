package com.example.EzyStocks.repositories;

import com.example.EzyStocks.entities.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity,Long> {
    public List<PortfolioEntity> findAllByUserId(Long userId);
}

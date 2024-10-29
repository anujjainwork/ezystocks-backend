package com.example.EzyStocks.repositories;

import com.example.EzyStocks.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<StockEntity,String> {
    public List<StockEntity> findByNameContainingIgnoreCase(String namePart);
}

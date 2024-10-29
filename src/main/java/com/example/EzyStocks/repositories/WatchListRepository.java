package com.example.EzyStocks.repositories;

import com.example.EzyStocks.entities.WatchlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchlistEntity,Long> {
}

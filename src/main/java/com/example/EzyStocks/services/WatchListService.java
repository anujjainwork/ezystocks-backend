package com.example.EzyStocks.services;

import com.example.EzyStocks.dtos.UserDto;
import com.example.EzyStocks.dtos.WatchlistDto;
import org.springframework.stereotype.Service;

@Service
public interface WatchListService {
    public WatchlistDto addToWatchlist(WatchlistDto watchlistDto, UserDto userDto);
}

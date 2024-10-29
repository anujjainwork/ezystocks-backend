package com.example.EzyStocks.services.impl;

import com.example.EzyStocks.dtos.UserDto;
import com.example.EzyStocks.dtos.WatchlistDto;
import com.example.EzyStocks.entities.UserEntity;
import com.example.EzyStocks.entities.WatchlistEntity;
import com.example.EzyStocks.repositories.WatchListRepository;
import com.example.EzyStocks.services.WatchListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchListServiceImpl implements WatchListService {

    @Autowired
    WatchListRepository watchListRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public WatchlistDto addToWatchlist(WatchlistDto watchlistDto, UserDto userDto) {
        watchlistDto.setUser(userDto);
        WatchlistEntity newWatchList = mapper.map(watchlistDto,WatchlistEntity.class);

        WatchlistEntity savedWatchlist = watchListRepository.save(newWatchList);
        return mapper.map(savedWatchlist,WatchlistDto.class);
    }
}

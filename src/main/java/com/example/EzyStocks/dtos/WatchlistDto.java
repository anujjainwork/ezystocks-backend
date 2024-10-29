package com.example.EzyStocks.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class WatchlistDto {
    private Long id;

    @JsonIgnore
    private UserDto user;

    private StockDto stock;
}

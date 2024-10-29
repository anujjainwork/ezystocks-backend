package com.example.EzyStocks.controllers;

import com.example.EzyStocks.apiresponses.StandardApiResponse;
import com.example.EzyStocks.dtos.UserDto;
import com.example.EzyStocks.dtos.WatchlistDto;
import com.example.EzyStocks.services.impl.UserServiceImpl;
import com.example.EzyStocks.services.impl.WatchListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/watchlist")
public class WatchListController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    WatchListServiceImpl watchListService;

    @PostMapping("/add-to-watchlist")
    public ResponseEntity<StandardApiResponse<WatchlistDto>> addToWatchList(@RequestBody WatchlistDto watchlistDto){
        UserDto userDto = userService.getAuthenticatedUserDetails();
        if(userDto!=null){
            WatchlistDto watchlistDto1 = watchListService.addToWatchlist(watchlistDto,userDto);

            if (watchlistDto != null){
                StandardApiResponse successResponse = new StandardApiResponse(
                        1,
                        200,
                        "watchlist added successfully",
                        watchlistDto1
                );
                return ResponseEntity.ok(successResponse);
            }
            StandardApiResponse nullResponse = new StandardApiResponse(
                    0,
                    404,
                    "null response - watchlist did not get added",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nullResponse);
        }
        StandardApiResponse userNotFoundResponse = new StandardApiResponse(
                0,
                403,
                "user not authorised",
                null
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(userNotFoundResponse);
    }
}

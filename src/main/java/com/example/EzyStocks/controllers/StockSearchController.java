package com.example.EzyStocks.controllers;

import com.example.EzyStocks.apiresponses.StandardApiResponse;
import com.example.EzyStocks.dtos.StockDto;
import com.example.EzyStocks.dtos.UserDto;
import com.example.EzyStocks.services.impl.StockSearchImpl;
import com.example.EzyStocks.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class StockSearchController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    StockSearchImpl stockSearch;

    @GetMapping("/query")
    public ResponseEntity<StandardApiResponse<List<StockDto>>> searchStocks(@RequestParam("query") String query) {
        UserDto userDto = userService.getAuthenticatedUserDetails();

        // Check if the user is authenticated
        if (userDto == null) {
            StandardApiResponse<List<StockDto>> unauthorisedResponse = new StandardApiResponse<>(
                    0,
                    403,
                    "User not authorized",
                    null
            );
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(unauthorisedResponse);
        }

        // Get the list of stocks based on the query
        List<StockDto> stocks = stockSearch.searchStocks(query);

        // Check if the list of stocks is empty
        if (stocks.isEmpty()) {
            StandardApiResponse<List<StockDto>> emptyResponse = new StandardApiResponse<>(
                    stocks.size(),
                    200,
                    "No such stock available",
                    stocks
            );
            return ResponseEntity.ok(emptyResponse);
        }

        // Return the list of stocks if found
        StandardApiResponse<List<StockDto>> response = new StandardApiResponse<>(
                stocks.size(),
                200,
                "Searched stocks fetched successfully",
                stocks
        );
        return ResponseEntity.ok(response);
    }
}

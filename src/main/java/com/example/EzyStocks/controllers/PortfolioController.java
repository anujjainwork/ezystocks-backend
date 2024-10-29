package com.example.EzyStocks.controllers;

import com.example.EzyStocks.apiresponses.StandardApiResponse;
import com.example.EzyStocks.dtos.PortfolioDto;
import com.example.EzyStocks.dtos.UserDto;
import com.example.EzyStocks.entities.UserEntity;
import com.example.EzyStocks.services.impl.PortfolioServiceImpl;
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
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioServiceImpl portfolioService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user")
    public ResponseEntity<StandardApiResponse<List<PortfolioDto>>> getUserPortfolio(){
        UserDto userDto = userService.getAuthenticatedUserDetails();
        if(userDto!=null){
            List<PortfolioDto> userPortfolio = portfolioService.getUserPortfolio(userDto.getId());
            StandardApiResponse<List<PortfolioDto>> newResponse =
                    new StandardApiResponse<>(
                          userPortfolio.size(),
                          200,
                          "portfolio fetched successfully",
                          userPortfolio
                    );
            return ResponseEntity.ok(newResponse);
        }
        StandardApiResponse<List<PortfolioDto>> notFoundResponse = new StandardApiResponse<>(
                0,
                403,
                "user not verified",
                null
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(notFoundResponse);
    }
}

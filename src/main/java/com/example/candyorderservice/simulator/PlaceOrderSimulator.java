package com.example.candyorderservice.simulator;

import com.example.candyorderservice.service.CandyOrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class PlaceOrderSimulator {

    private CandyOrderService candyOrderService;

    public PlaceOrderSimulator(CandyOrderService candyOrderService) {
        this.candyOrderService = candyOrderService;
    }

    @Scheduled(fixedRate = 5000)
    public void placeOrder(){
        candyOrderService.placeOrder();
    }
}

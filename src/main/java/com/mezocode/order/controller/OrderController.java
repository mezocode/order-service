package com.mezocode.order.controller;

import com.mezocode.order.payload.OrderPayload;
import com.mezocode.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void processOrder(@RequestBody OrderPayload orderPayload){
        System.out.println("Order service --- orderPayload: " + orderPayload);
        orderService.processOrder(orderPayload.getProductId(), orderPayload.getQuantity());
        System.out.println("Order processed successfully");
    }
}

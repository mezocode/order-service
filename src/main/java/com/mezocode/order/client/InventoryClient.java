package com.mezocode.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8081")
@Component
public interface InventoryClient {
    @GetMapping("/api/inventory/check/{productId}")
    Boolean checkProductAvailability(@PathVariable("productId") Long productId);
}
package com.mezocode.order.service;

import com.mezocode.order.client.InventoryClient;
import com.mezocode.order.exception.ProductAvailabilityException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final InventoryClient inventoryClient;

    public OrderService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackCheckInventory")
    public void processOrder(Long productId, long quantity) {
        System.out.println("processOrder -- calling the inventoryService with productId: "+productId + ", and quantity: "+quantity);
        Boolean isProductAvailable = inventoryClient.checkProductAvailability(productId);
        if (!isProductAvailable) {
            throw new ProductAvailabilityException("Product:"+ productId+" unavailable");
        }

    }

    public void fallbackCheckInventory(Long productId, long quantity, Throwable throwable) {
        System.out.println("Fallback logic if Inventory Service fails for productId "
                + productId + " and quantity : "+quantity+ ". Error: " + throwable.getMessage());
    }
}
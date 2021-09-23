package com.shopserver.listeners;

import com.shopserver.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
@RequiredArgsConstructor
public class ProductsIncreaseListener {

    @JmsListener(
            destination= "products"
    )
    public void onForProductQuantityIncrease(Message message) {
        orderService.processIncompleteOrders();
    }

    private final OrderService orderService;
}

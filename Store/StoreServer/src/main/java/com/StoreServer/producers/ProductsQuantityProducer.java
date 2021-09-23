package com.storeserver.producers;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsQuantityProducer {
    public void publish(String message) {
        jmsTemplate.convertAndSend(DESTINATION, message);
    }
    public static final String PRODUCT_QUANTITY_INCREASED_NOTIFICATION_MSG = "product-quantity-increased";

    private final JmsTemplate jmsTemplate;
    private static final String DESTINATION = "products";
}

package org.example.api.order;

import org.example.entity.Order;

public interface OrderApi {
    Order getOrderById(Long id);
    void addOrder(Order order);
}


package com.Restaurant.Repository;

import com.Restaurant.Model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public void saveOrder(Order order) {
        orders.add(order);
    }

    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getOrderId() == id) {
                return order;
            }
        }

        return null;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
}

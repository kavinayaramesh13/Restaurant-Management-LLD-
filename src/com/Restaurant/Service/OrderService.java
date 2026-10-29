
package com.Restaurant.Service;

import com.Restaurant.Model.Order;
import com.Restaurant.Model.MenuItem;
import com.Restaurant.Model.Table;
import com.Restaurant.Model.Payment;
import com.Restaurant.Payment.PaymentMethod;
import com.Restaurant.Repository.OrderRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository repository;
    private final TableService tableService;
    private final MenuService menuService;
    private final PaymentService paymentService;

    private int nextOrderId = 1;

    public OrderService(
            TableService tableService,
            MenuService menuService,
            PaymentService paymentService) {

        if (tableService == null || menuService == null
                || paymentService == null) {
            throw new IllegalArgumentException(
                "Services cannot be null."
            );
        }

        this.repository = new OrderRepository();
        this.tableService = tableService;
        this.menuService = menuService;
        this.paymentService = paymentService;
    }

    public Order createOrder(int tableId) {
        Table table = tableService.getTableById(tableId);

        if (table == null) {
            throw new IllegalArgumentException(
                "Table does not exist."
            );
        }

        if (table.getStatus() != Table.TableStatus.AVAILABLE) {
            throw new IllegalStateException(
                "Table is already occupied."
            );
        }

        Order order = new Order(nextOrderId, tableId);

        if (!tableService.occupyTable(tableId)) {
            throw new IllegalStateException(
                "Could not occupy the table."
            );
        }

        repository.saveOrder(order);
        nextOrderId++;

        return order;
    }

    public void addItem(int orderId, int itemId, int quantity) {
        Order order = repository.getOrderById(orderId);

        if (order == null) {
            throw new IllegalArgumentException(
                "Order does not exist."
            );
        }

        if (order.isPaid()) {
            throw new IllegalStateException(
                "Cannot modify a paid order."
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero."
            );
        }

        MenuItem item = menuService.getItemById(itemId);

        if (item == null) {
            throw new IllegalArgumentException(
                "Menu item does not exist."
            );
        }

        order.addItem(item, quantity);
    }

    public Payment payOrder(int orderId, PaymentMethod method) {
        Order order = repository.getOrderById(orderId);

        if (order == null) {
            throw new IllegalArgumentException(
                "Order does not exist."
            );
        }

        if (order.isPaid()) {
            throw new IllegalStateException(
                "This order has already been paid."
            );
        }

        if (order.getTotalAmount() <= 0) {
            throw new IllegalStateException(
                "Add at least one item before payment."
            );
        }

        Payment payment = paymentService.processPayment(
            order.getOrderId(),
            order.getTotalAmount(),
            method
        );

        order.markPaid();
        tableService.freeTable(order.getTableId());

        return payment;
    }

    public Order getOrderById(int id) {
        return repository.getOrderById(id);
    }

    public List<Order> getAllOrders() {
        return repository.getAllOrders();
    }
}

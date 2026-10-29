
package com.Restaurant.Model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public static class OrderItem {
        private MenuItem menuItem;
        private int quantity;

        public OrderItem(MenuItem menuItem, int quantity) {
            this.menuItem = menuItem;
            this.quantity = quantity;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getSubtotal() {
            return menuItem.getPrice() * quantity;
        }
    }

    private int orderId;
    private int tableId;
    private List<OrderItem> items;
    private boolean paid;

    public Order(int orderId, int tableId) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.items = new ArrayList<>();
        this.paid = false;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void markPaid() {
        paid = true;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
    }

    public double getTotalAmount() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public void displayOrder() {
        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Table ID: " + tableId);

        for (OrderItem item : items) {
            System.out.println(
                item.getMenuItem().getName()
                + " x " + item.getQuantity()
                + " = Rs. " + item.getSubtotal()
            );
        }

        System.out.println("Total: Rs. " + getTotalAmount());
        System.out.println("Payment Status: " +
            (paid ? "PAID" : "PENDING"));
    }
}


package com.Restaurant.CLI;

import com.Restaurant.Model.Order;
import com.Restaurant.Model.Table;
import com.Restaurant.Model.MenuItem;
import com.Restaurant.Model.Payment;
import com.Restaurant.Payment.PaymentMethod;
import com.Restaurant.Service.OrderService;
import com.Restaurant.Service.MenuService;
import com.Restaurant.Service.TableService;

import java.util.Scanner;

public class OrderCLI {

    private final OrderService orderService;
    private final MenuService menuService;
    private final TableService tableService;
    private final Scanner scanner;

    public OrderCLI(
            OrderService orderService,
            MenuService menuService,
            TableService tableService,
            Scanner scanner) {

        this.orderService = orderService;
        this.menuService = menuService;
        this.tableService = tableService;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== ORDER MANAGEMENT ===");
            System.out.println("1. Create order and add items");
            System.out.println("2. View all orders");
            System.out.println("3. Pay for an order");
            System.out.println("0. Back to main menu");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        createOrder();
                        break;

                    case "2":
                        viewOrders();
                        break;

                    case "3":
                        payOrder();
                        break;

                    case "0":
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void createOrder() {
        System.out.println("\n=== AVAILABLE TABLES ===");

        for (Table table : tableService.getAllTables()) {
            table.displayTable();
        }

        System.out.print("Enter table ID: ");
        int tableId = Integer.parseInt(scanner.nextLine().trim());

        Order order = orderService.createOrder(tableId);

        System.out.println("Order created. Order ID: "
            + order.getOrderId());

        System.out.println("\n=== MENU ===");

        for (MenuItem item : menuService.getAllItems()) {
            item.displayItem();
        }

        while (true) {
            System.out.print(
                "Enter menu item ID (0 to finish): "
            );

            int itemId = Integer.parseInt(scanner.nextLine().trim());

            if (itemId == 0) {
                break;
            }

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            orderService.addItem(
                order.getOrderId(), itemId, quantity
            );

            System.out.println("Item added successfully.");
        }

        order.displayOrder();
        System.out.println(
            "Use option 3 to pay for this order."
        );
    }

    private void viewOrders() {
        if (orderService.getAllOrders().isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order order : orderService.getAllOrders()) {
            order.displayOrder();
            System.out.println("-----------------------");
        }
    }

    private void payOrder() {
        System.out.print("Enter order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine().trim());

        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        order.displayOrder();

        System.out.println("\n1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.print("Choose payment method: ");

        String choice = scanner.nextLine().trim();
        PaymentMethod method;

        switch (choice) {
            case "1":
                method = PaymentMethod.CASH;
                break;

            case "2":
                method = PaymentMethod.CARD;
                break;

            case "3":
                method = PaymentMethod.UPI;
                break;

            default:
                System.out.println("Invalid payment method.");
                return;
        }

        Payment payment = orderService.payOrder(orderId, method);

        System.out.println("\nPayment successful.");
        payment.displayPayment();
    }
}

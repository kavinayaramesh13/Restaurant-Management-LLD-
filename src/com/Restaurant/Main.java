
package com.Restaurant;

import com.Restaurant.CLI.TableCLI;
import com.Restaurant.CLI.MenuCLI;
import com.Restaurant.CLI.OrderCLI;

import com.Restaurant.Service.TableService;
import com.Restaurant.Service.MenuService;
import com.Restaurant.Service.OrderService;
import com.Restaurant.Service.PaymentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create services
        TableService tableService = new TableService();
        MenuService menuService = new MenuService();
        PaymentService paymentService = new PaymentService();

        // Connect order, table, menu, and payment services
        OrderService orderService = new OrderService(
            tableService,
            menuService,
            paymentService
        );

        // Create CLI objects
        TableCLI tableCLI = new TableCLI(
            tableService, scanner
        );

        MenuCLI menuCLI = new MenuCLI(
            menuService, scanner
        );

        OrderCLI orderCLI = new OrderCLI(
            orderService,
            menuService,
            tableService,
            scanner
        );

        boolean running = true;

        while (running) {
            System.out.println("\n=== RESTAURANT MANAGEMENT SYSTEM ===");
            System.out.println("1. Table Management");
            System.out.println("2. View Menu");
            System.out.println("3. Order and Payment Management");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    tableCLI.start();
                    break;

                case "2":
                    menuCLI.start();
                    break;

                case "3":
                    orderCLI.start();
                    break;

                case "0":
                    running = false;
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}

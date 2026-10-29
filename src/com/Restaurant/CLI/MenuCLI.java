
package com.Restaurant.CLI;

import com.Restaurant.Model.MenuItem;
import com.Restaurant.Service.MenuService;
import java.util.Scanner;

public class MenuCLI {

    private final MenuService service;
    private final Scanner scanner;

    public MenuCLI(MenuService service, Scanner scanner) {
    this.service = service;
    this.scanner = scanner;
}

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU MANAGEMENT ===");
            System.out.println("1. View menu");
            System.out.println("0. Back to main menu");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    for (MenuItem item : service.getAllItems()) {
                        item.displayItem();
                    }
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

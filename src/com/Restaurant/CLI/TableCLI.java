
package com.Restaurant.CLI;

import com.Restaurant.Model.Table;
import com.Restaurant.Service.TableService;
import java.util.Scanner;

public class TableCLI {

    private final TableService service;
    private final Scanner scanner;

    public TableCLI(TableService service, Scanner scanner) {
    this.service = service;
    this.scanner = scanner;
}

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== TABLE MANAGEMENT ===");
            System.out.println("1. View all tables");
            System.out.println("2. Occupy a table");
            System.out.println("3. Free a table");
            System.out.println("0. Back to main menu");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        for (Table table : service.getAllTables()) {
                            table.displayTable();
                        }
                        break;

                    case "2":
                        System.out.print("Enter table ID: ");
                        int occupyId = Integer.parseInt(
                            scanner.nextLine().trim()
                        );

                        if (service.occupyTable(occupyId)) {
                            System.out.println("Table occupied.");
                        } else {
                            System.out.println(
                                "Table not found or already occupied."
                            );
                        }
                        break;

                    case "3":
                        System.out.print("Enter table ID: ");
                        int freeId = Integer.parseInt(
                            scanner.nextLine().trim()
                        );

                        if (service.freeTable(freeId)) {
                            System.out.println("Table is now available.");
                        } else {
                            System.out.println(
                                "Table not found or already available."
                            );
                        }
                        break;

                    case "0":
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}

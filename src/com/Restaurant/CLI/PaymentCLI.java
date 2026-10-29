package com.Restaurant.CLI;

import com.Restaurant.Model.Payment;
import com.Restaurant.Payment.PaymentMethod;
import com.Restaurant.Service.PaymentService;
import java.util.List;
import java.util.Scanner;

public class PaymentCLI {

    private final PaymentService service;
    private final Scanner scanner;

    public PaymentCLI() {
        this(new PaymentService());
    }

    public PaymentCLI(PaymentService service) {
        if (service == null) {
            throw new IllegalArgumentException("Payment service is required.");
        }

        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== RESTAURANT PAYMENT ===");
            System.out.println("1. Process payment");
            System.out.println("2. View payment history");
            System.out.println("0. Exit");

            String choice = readLine("Enter choice: ");

            if (choice == null) {
                break;
            }

            switch (choice.trim()) {
                case "1":
                    processPayment();
                    break;

                case "2":
                    showPaymentHistory();
                    break;

                case "0":
                    running = false;
                    System.out.println("Thank you.");
                    break;

                default:
                    System.out.println("Invalid choice. Select 1, 2, or 0.");
            }
        }
    }

    private void processPayment() {
        try {
            String orderInput = readLine("Enter order ID: ");
            if (orderInput == null) {
                return;
            }

            String amountInput = readLine("Enter amount: ");
            if (amountInput == null) {
                return;
            }

            System.out.println("1. Cash");
            System.out.println("2. Card (simulation)");
            System.out.println("3. UPI (simulation)");

            String methodInput = readLine("Choose payment method: ");
            if (methodInput == null) {
                return;
            }

            int orderId = Integer.parseInt(orderInput.trim());
            double amount = Double.parseDouble(amountInput.trim());

            PaymentMethod method = parsePaymentMethod(methodInput.trim());

            Payment payment = service.processPayment(orderId, amount, method);

            System.out.println("Payment processed:");
            payment.displayPayment();

        } catch (NumberFormatException exception) {
            System.out.println("Order ID and amount must be valid numbers.");
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println("Payment failed: " + exception.getMessage());
        }
    }

    private PaymentMethod parsePaymentMethod(String choice) {
        switch (choice) {
            case "1":
                return PaymentMethod.CASH;
            case "2":
                return PaymentMethod.CARD;
            case "3":
                return PaymentMethod.UPI;
            default:
                throw new IllegalArgumentException(
                    "Choose cash, card, or UPI."
                );
        }
    }

    private void showPaymentHistory() {
        List<Payment> payments = service.getAllPayments();

        if (payments.isEmpty()) {
            System.out.println("No payments have been recorded.");
            return;
        }

        System.out.println("\n=== PAYMENT HISTORY ===");

        for (Payment payment : payments) {
            payment.displayPayment();
            System.out.println();
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }
}

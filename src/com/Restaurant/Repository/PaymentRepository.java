package com.Restaurant.Repository;

import com.Restaurant.Model.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private final List<Payment> payments = new ArrayList<>();

    public boolean savePayment(Payment payment) {
        if (payment == null) {
            return false;
        }

        payments.add(payment);
        return true;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    public void displayAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No payment records found.");
            return;
        }

        System.out.println("\n===== PAYMENT HISTORY =====");

        for (Payment payment : payments) {
            payment.displayPayment();
            System.out.println("-------------------------");
        }
    }
}


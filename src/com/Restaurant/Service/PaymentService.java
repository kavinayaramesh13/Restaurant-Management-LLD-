
package com.Restaurant.Service;

import com.Restaurant.Model.Payment;
import com.Restaurant.Payment.PaymentMethod;
import com.Restaurant.Payment.PaymentProcessor;
import com.Restaurant.Payment.CashPayment;
import com.Restaurant.Payment.CardPayment;
import com.Restaurant.Payment.UpiPayment;
import com.Restaurant.Repository.PaymentRepository;

import java.util.List;

public class PaymentService {

    private final PaymentRepository repository;
    private int nextPaymentId = 1;

    public PaymentService() {
        this(new PaymentRepository());
    }

    public PaymentService(PaymentRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository is required.");
        }

        this.repository = repository;
    }

    public Payment processPayment(int orderId, double amount,
                                 PaymentMethod method) {

        if (orderId <= 0) {
            throw new IllegalArgumentException("Invalid order ID.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero."
            );
        }

        if (method == null) {
            throw new IllegalArgumentException(
                "Invalid payment method."
            );
        }

        Payment payment;

        switch (method) {
            case CASH:
                payment = new CashPayment(
                    nextPaymentId, orderId, amount
                );
                break;

            case CARD:
                payment = new CardPayment(
                    nextPaymentId, orderId, amount
                );
                break;

            case UPI:
                payment = new UpiPayment(
                    nextPaymentId, orderId, amount
                );
                break;

            default:
                throw new IllegalArgumentException(
                    "Unsupported payment method."
                );
        }

        // Interface reference demonstrating polymorphism
        PaymentProcessor processor = payment;

        boolean success = processor.processPayment();

        if (!success) {
            throw new IllegalStateException(
                "Payment processing failed."
            );
        }

        boolean saved = repository.savePayment(payment);

        if (!saved) {
            throw new IllegalStateException(
                "Payment was processed, but saving failed."
            );
        }

        nextPaymentId++;

        return payment;
    }

    public List<Payment> getAllPayments() {
        return repository.getAllPayments();
    }
}

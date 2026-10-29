package com.Restaurant.Model;

import com.Restaurant.Payment.PaymentMethod;
import com.Restaurant.Payment.PaymentProcessor;

public abstract class Payment implements PaymentProcessor {

    private int paymentId;
    private int orderId;
    private double amount;
    private PaymentMethod paymentMethod;

    public Payment(int paymentId, int orderId,
                   double amount, PaymentMethod paymentMethod) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public abstract boolean processPayment();

    public void displayPayment() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Order ID: " + orderId);
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Payment Method: " + paymentMethod);
    }
}
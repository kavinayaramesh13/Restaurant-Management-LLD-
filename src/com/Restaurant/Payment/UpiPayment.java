package com.Restaurant.Payment;

import com.Restaurant.Model.Payment;

public class UpiPayment extends Payment {

    public UpiPayment(int paymentId, int orderId, double amount) {
        super(paymentId, orderId, amount, PaymentMethod.UPI);
    }

    @Override
    public boolean processPayment() {
        System.out.println("UPI payment processed successfully.");
        return true;
    }
}
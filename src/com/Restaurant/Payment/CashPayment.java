package com.Restaurant.Payment;

import com.Restaurant.Model.Payment;

public class CashPayment extends Payment {

    public CashPayment(int paymentId, int orderId, double amount) {
        super(paymentId, orderId, amount, PaymentMethod.CASH);
    }

    @Override
    public boolean processPayment() {
        System.out.println("Cash payment received successfully.");
        return true;
    }
}
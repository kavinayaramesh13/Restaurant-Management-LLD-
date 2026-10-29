package com.Restaurant.Payment;

import com.Restaurant.Model.Payment;

public class CardPayment extends Payment {

    public CardPayment(int paymentId, int orderId, double amount) {
        super(paymentId, orderId, amount, PaymentMethod.CARD);
    }

    @Override
    public boolean processPayment() {
        System.out.println("Card payment processed successfully.");
        return true;
    }
}
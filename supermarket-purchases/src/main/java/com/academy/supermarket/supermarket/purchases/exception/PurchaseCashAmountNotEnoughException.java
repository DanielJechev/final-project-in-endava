package com.academy.supermarket.supermarket.purchases.exception;

public class PurchaseCashAmountNotEnoughException extends RuntimeException {
    public PurchaseCashAmountNotEnoughException(String message) {
        super(message);
    }
}

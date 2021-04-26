package com.academy.supermarket.supermarket.purchases.exception;

public class SuperMarketAlreadyExistsException extends RuntimeException {
    public SuperMarketAlreadyExistsException(String message) {
        super(message);
    }
}

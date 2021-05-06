package com.academy.supermarket.supermarket.purchases.exception;

public class ItemTypeInvalidException extends RuntimeException {
    public ItemTypeInvalidException(String message) {
        super(message);
    }
}

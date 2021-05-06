package com.academy.supermarket.supermarket.purchases.model.enums;

public enum PaymentType {
    CASH, CARD;

    public static boolean contains(String type) {
        for (ItemType it : ItemType.values()) {
            if (it.toString().equals(type)) {
                return true;
            }
        }
        return false;
    }
}

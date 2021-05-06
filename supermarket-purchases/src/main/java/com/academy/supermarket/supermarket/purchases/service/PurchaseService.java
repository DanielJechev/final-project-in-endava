package com.academy.supermarket.supermarket.purchases.service;

import com.academy.supermarket.supermarket.purchases.model.dto.PurchaseAddDto;
import com.academy.supermarket.supermarket.purchases.model.entities.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase makePurchase(PurchaseAddDto purchaseSupermarketDto);

    List<Purchase> getAll();
}

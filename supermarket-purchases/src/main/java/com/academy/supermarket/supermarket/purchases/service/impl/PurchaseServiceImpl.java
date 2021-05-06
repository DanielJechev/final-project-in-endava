package com.academy.supermarket.supermarket.purchases.service.impl;

import com.academy.supermarket.supermarket.purchases.exception.ItemTypeInvalidException;
import com.academy.supermarket.supermarket.purchases.exception.PurchaseCashAmountNotEnoughException;
import com.academy.supermarket.supermarket.purchases.exception.SuperMarketNotFoundException;
import com.academy.supermarket.supermarket.purchases.model.dto.PurchaseAddDto;
import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import com.academy.supermarket.supermarket.purchases.model.entities.Purchase;
import com.academy.supermarket.supermarket.purchases.model.entities.SuperMarket;
import com.academy.supermarket.supermarket.purchases.model.enums.PaymentType;
import com.academy.supermarket.supermarket.purchases.repostitory.ItemRepository;
import com.academy.supermarket.supermarket.purchases.repostitory.PurchaseRepository;
import com.academy.supermarket.supermarket.purchases.repostitory.SuperMarketRepository;
import com.academy.supermarket.supermarket.purchases.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    public static final double INITIALIZATION_VARIABLE = 0.0;
    private PurchaseRepository purchaseRepository;
    private SuperMarketRepository superMarketRepository;
    private ItemRepository itemRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, SuperMarketRepository superMarketRepository, ItemRepository itemRepository) {
        this.purchaseRepository = purchaseRepository;
        this.superMarketRepository = superMarketRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Purchase makePurchase(PurchaseAddDto purchaseSupermarketDto) {
        Purchase purchase = new Purchase();
        Optional<SuperMarket> optionalSuperMarket = superMarketRepository.findById(purchaseSupermarketDto.getSuperMarketId());

        if (optionalSuperMarket.isEmpty()) {
            throw new SuperMarketNotFoundException(MessageFormat.format("Supermarket with id:{0} not found!", purchaseSupermarketDto.getSuperMarketId()));
        }

        Double totalPrice = INITIALIZATION_VARIABLE;
        List<Item> newItems = new ArrayList<>();
        for (Item itemId : purchaseSupermarketDto.getItemList()) {
            Optional<Item> optionalItem = itemRepository.findById(itemId.getId());
            if (optionalItem.isPresent()) {
                newItems.add(optionalItem.get());
                totalPrice += optionalItem.get().getPrice();
            }
        }

        if (!PaymentType.contains(purchaseSupermarketDto.getPaymentType())) {
            throw new ItemTypeInvalidException("Invalid payment type.");
        }

        purchase.setSuperMarketId(purchaseSupermarketDto.getSuperMarketId());
        purchase.setTotalPrice(totalPrice);
        purchase.setPaymentType(PaymentType.valueOf(purchaseSupermarketDto.getPaymentType()));

        if (Objects.equals(PaymentType.CASH, purchase.getPaymentType())) {
            purchase.setCashAmount(purchaseSupermarketDto.getCashAmount());
            if (purchaseSupermarketDto.getCashAmount() < purchase.getTotalPrice()) {
                throw new PurchaseCashAmountNotEnoughException("You do not have enough money for this purchase !");
            }
            purchase.setCashAfterShopping(purchaseSupermarketDto.getCashAmount() - purchase.getTotalPrice());
            purchase.setItemList(newItems);
            purchase.setTimePurchased(LocalDateTime.now());

        }

        if (Objects.equals(PaymentType.CARD, purchase.getPaymentType())) {
            purchase.setItemList(newItems);
            purchase.setTimePurchased(LocalDateTime.now());
        }

        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

}


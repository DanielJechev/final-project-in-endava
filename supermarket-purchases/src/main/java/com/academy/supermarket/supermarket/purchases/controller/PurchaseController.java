package com.academy.supermarket.supermarket.purchases.controller;

import com.academy.supermarket.supermarket.purchases.model.dto.PurchaseAddDto;
import com.academy.supermarket.supermarket.purchases.model.dto.PurchaseResponseDto;
import com.academy.supermarket.supermarket.purchases.model.entities.Purchase;
import com.academy.supermarket.supermarket.purchases.service.PurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private PurchaseService purchaseService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @PostMapping
    public ResponseEntity<PurchaseResponseDto> buyItemsFromSupermarket(@Valid @RequestBody PurchaseAddDto purchaseSupermarketDto) {
        Purchase newPurchase = this.purchaseService.makePurchase(purchaseSupermarketDto);
        return new ResponseEntity<>(modelMapper.map(newPurchase, PurchaseResponseDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getPurchases() {
        return new ResponseEntity(purchaseService.getAll(), HttpStatus.OK);
    }
}

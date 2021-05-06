package com.academy.supermarket.supermarket.purchases.controller;

import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketAddDto;
import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketDto;
import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketGetDto;
import com.academy.supermarket.supermarket.purchases.model.entities.SuperMarket;
import com.academy.supermarket.supermarket.purchases.service.SuperMarketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/supermarket")
public class SuperMarketController {
    private SuperMarketService superMarketService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public SuperMarketController(SuperMarketService superMarketService) {
        this.superMarketService = superMarketService;
    }

    @PostMapping("/create")
    public ResponseEntity<SuperMarketDto> createUser(@Valid @RequestBody SuperMarketDto superMarketDto) {
        SuperMarket savedSuperMarket = superMarketService.createSuperMarket(superMarketDto);
        return new ResponseEntity(modelMapper.map(savedSuperMarket, SuperMarketDto.class), HttpStatus.CREATED);
    }

    @PostMapping("/addItems")
    public ResponseEntity<SuperMarketAddDto> addItemsToSupermarket(@Valid @RequestBody SuperMarketAddDto superMarketAddDto) {
        SuperMarket superMarket = superMarketService.addItemsToSupermarket(superMarketAddDto);
        return new ResponseEntity<>(modelMapper.map(superMarket, SuperMarketAddDto.class), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperMarketGetDto> getSupermarketById(@PathVariable(value = "id", required = true) String supermarketId) {
        SuperMarket supermarket = this.superMarketService.getSupermarketById(supermarketId);
        return new ResponseEntity<>(modelMapper.map(supermarket, SuperMarketGetDto.class), HttpStatus.OK);
    }

}

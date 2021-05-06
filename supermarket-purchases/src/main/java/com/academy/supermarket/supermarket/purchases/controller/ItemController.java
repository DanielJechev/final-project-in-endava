package com.academy.supermarket.supermarket.purchases.controller;

import com.academy.supermarket.supermarket.purchases.model.dto.ItemDto;
import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import com.academy.supermarket.supermarket.purchases.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {
    private ItemService itemService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        Item savedItem = itemService.createItem(itemDto);
        return new ResponseEntity(modelMapper.map(savedItem, ItemDto.class), HttpStatus.CREATED);
    }



}

package com.academy.supermarket.supermarket.purchases.service.impl;

import com.academy.supermarket.supermarket.purchases.exception.ItemTypeInvalidException;
import com.academy.supermarket.supermarket.purchases.model.dto.ItemDto;
import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import com.academy.supermarket.supermarket.purchases.model.enums.ItemType;
import com.academy.supermarket.supermarket.purchases.repostitory.ItemRepository;
import com.academy.supermarket.supermarket.purchases.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(ItemDto itemDto) {
        Item itemExample = new Item();
        if (!ItemType.contains(itemDto.getType())) {
            throw new ItemTypeInvalidException("Invalid item type.");
        }

        itemExample.setName(itemDto.getName());
        itemExample.setPrice(itemDto.getPrice());
        itemExample.setType(ItemType.valueOf(itemDto.getType()));

        return itemRepository.save(itemExample);
    }
}

package com.academy.supermarket.supermarket.purchases.service.impl;

import com.academy.supermarket.supermarket.purchases.model.entities.Item;
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
    public Item createItem(Item item) {
        Item itemExample = new Item();
        itemExample.setName(item.getName());
        itemExample.setPrice(item.getPrice());
        itemExample.setType(item.getType());
        return itemRepository.save(item);
    }
}

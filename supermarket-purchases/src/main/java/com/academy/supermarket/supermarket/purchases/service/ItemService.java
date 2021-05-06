package com.academy.supermarket.supermarket.purchases.service;

import com.academy.supermarket.supermarket.purchases.model.dto.ItemDto;
import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import com.academy.supermarket.supermarket.purchases.model.enums.ItemType;

public interface ItemService {
    Item createItem(ItemDto itemDto);

}

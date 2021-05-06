package com.academy.supermarket.supermarket.purchases.service.impl;

import com.academy.supermarket.supermarket.purchases.exception.ItemNotFoundException;
import com.academy.supermarket.supermarket.purchases.exception.SuperMarketAlreadyExistsException;
import com.academy.supermarket.supermarket.purchases.exception.SuperMarketNotFoundException;
import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketAddDto;
import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketDto;
import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import com.academy.supermarket.supermarket.purchases.model.entities.SuperMarket;
import com.academy.supermarket.supermarket.purchases.repostitory.ItemRepository;
import com.academy.supermarket.supermarket.purchases.repostitory.SuperMarketRepository;
import com.academy.supermarket.supermarket.purchases.service.SuperMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class SuperMarketServiceImpl implements SuperMarketService {
    private SuperMarketRepository superMarketRepository;
    private ItemRepository itemRepository;

    @Autowired
    public SuperMarketServiceImpl(SuperMarketRepository superMarketRepository, ItemRepository itemRepository) {
        this.superMarketRepository = superMarketRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public SuperMarket createSuperMarket(SuperMarketDto superMarketDto) {
        SuperMarket superMarketExample = new SuperMarket();

        superMarketExample.setName(superMarketDto.getName());
        Example<SuperMarket> example = Example.of(superMarketExample);
        Optional<SuperMarket> optionalSuperMarket = superMarketRepository.findOne(example);
        if (optionalSuperMarket.isPresent()) {
            throw new SuperMarketAlreadyExistsException("SuperMarket with this name already exists!");
        }

        superMarketExample.setAddress(superMarketDto.getAddress());
        superMarketExample.setPhoneNumber(superMarketDto.getPhoneNumber());
        superMarketExample.setWorkHours(superMarketDto.getWorkHours());

        return superMarketRepository.save(superMarketExample);
    }

    @Override
    public SuperMarket addItemsToSupermarket(SuperMarketAddDto superMarketAddDto) {
        SuperMarket superMarket = getSuperMarketById(superMarketAddDto.getId());
        List<Item> itemLists = updateItemList(superMarketAddDto.getItemList());
        superMarket.setItemList(itemLists);
        return superMarketRepository.save(superMarket);

    }

    @Override
    public SuperMarket getSupermarketById(String supermarketId) {
        return this.superMarketRepository.findById(supermarketId).get();
    }

    private SuperMarket getSuperMarketById(String id) {
        SuperMarket superMarketExample = new SuperMarket();
        superMarketExample.setId(id);
        Example<SuperMarket> example = Example.of(superMarketExample);
        return superMarketRepository.findOne(example).orElseThrow(() -> new SuperMarketNotFoundException(MessageFormat.format("Supermarket with id:{0} not found!", id)));
    }

    private List<Item> updateItemList(List<Item> itemList) {
        Optional<Item> currentItem;
        for (int i = 0; i < itemList.size(); i++) {
             currentItem = itemRepository.findById(itemList.get(i).getId());

            if (currentItem.isEmpty()){
           throw  new ItemNotFoundException("This item does not exist!");
            }
            itemList.set(i,currentItem.get());
        }

        return itemList;
    }


}

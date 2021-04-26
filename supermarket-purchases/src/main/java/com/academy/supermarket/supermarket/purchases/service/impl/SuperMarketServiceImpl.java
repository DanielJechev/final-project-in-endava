package com.academy.supermarket.supermarket.purchases.service.impl;

import com.academy.supermarket.supermarket.purchases.exception.SuperMarketAlreadyExistsException;
import com.academy.supermarket.supermarket.purchases.exception.SuperMarketNotFoundException;
import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketAddDto;
import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import com.academy.supermarket.supermarket.purchases.model.entities.SuperMarket;
import com.academy.supermarket.supermarket.purchases.model.param.FilterParam;
import com.academy.supermarket.supermarket.purchases.model.param.PageParam;
import com.academy.supermarket.supermarket.purchases.repostitory.ItemRepository;
import com.academy.supermarket.supermarket.purchases.repostitory.SuperMarketRepository;
import com.academy.supermarket.supermarket.purchases.service.SuperMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SuperMarketServiceImpl implements SuperMarketService {
    private SuperMarketRepository superMarketRepository;
    private ItemRepository itemRepository;
    public static final String ID = "id";

    @Autowired
    public SuperMarketServiceImpl(SuperMarketRepository superMarketRepository, ItemRepository itemRepository) {
        this.superMarketRepository = superMarketRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public SuperMarket createSuperMarket(SuperMarket superMarket) {
        SuperMarket superMarketExample = new SuperMarket();

        superMarketExample.setName(superMarket.getName());
        Example<SuperMarket> example = Example.of(superMarketExample);
        Optional<SuperMarket> optionalUser = superMarketRepository.findOne(example);
        if (optionalUser.isPresent()) {
            throw new SuperMarketAlreadyExistsException("SuperMarket with this name already exists!");
        }

        superMarketExample.setAddress(superMarket.getAddress());
        superMarketExample.setPhoneNumber(superMarket.getPhoneNumber());
        superMarketExample.setWorkHours(superMarket.getWorkHours());

        return superMarketRepository.save(superMarket);
    }

    @Override
    public SuperMarket addItemsToSupermarket(int id, SuperMarketAddDto superMarketAddDto) {
        SuperMarket superMarket = getSuperMarketById(id);

        List<Item> itemList = updateItemList(superMarketAddDto.getItemList());

        superMarket.setItemList(itemList);
        return superMarketRepository.save(superMarket);
    }

    @Override
    public Page<SuperMarket> getAll(PageParam pageParam, FilterParam filterParam) {
        SuperMarket superMarket = new SuperMarket();
        if (Objects.equals(filterParam.getSearchBy(), ID)) {
            superMarket.setId(filterParam.getSearchText());
        }

        Example<SuperMarket> example = Example.of(superMarket);
        Pageable allOfSuperMarket = PageRequest.of(pageParam.getPage() - 1, pageParam.getLimit());

        return this.superMarketRepository.findAll(example, allOfSuperMarket);
    }

    private List<Item> updateItemList(List<Item> itemList) {
        Item currentItem;
        for (int i = 0; i < itemList.size(); i++) {
            currentItem = itemRepository.findByName(itemList.get(i).getName())
                    .orElse(itemList.get(i));
            if (Objects.isNull(currentItem.getId())) {
                currentItem = itemRepository.save(currentItem);
            }
            itemList.set(i, currentItem);
        }
        return itemList;
    }

    private SuperMarket getSuperMarketById(Integer id) {
        SuperMarket superMarketExample = new SuperMarket();
        superMarketExample.setId(id);
        Example<SuperMarket> example = Example.of(superMarketExample);
        return superMarketRepository.findOne(example).orElseThrow(() -> new SuperMarketNotFoundException(MessageFormat.format("Supermarket with id:{0} not found!", id)));
    }
}

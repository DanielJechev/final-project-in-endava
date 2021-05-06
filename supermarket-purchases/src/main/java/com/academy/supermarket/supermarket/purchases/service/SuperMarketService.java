package com.academy.supermarket.supermarket.purchases.service;

import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketAddDto;
import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketDto;
import com.academy.supermarket.supermarket.purchases.model.entities.SuperMarket;

public interface SuperMarketService {

    SuperMarket createSuperMarket(SuperMarketDto superMarketDto);

    SuperMarket addItemsToSupermarket(SuperMarketAddDto superMarketAddDto);

    SuperMarket getSupermarketById(String supermarketId);


}

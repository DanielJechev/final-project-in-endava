package com.academy.supermarket.supermarket.purchases.service;

import com.academy.supermarket.supermarket.purchases.model.dto.SuperMarketAddDto;
import com.academy.supermarket.supermarket.purchases.model.entities.SuperMarket;
import com.academy.supermarket.supermarket.purchases.model.param.FilterParam;
import com.academy.supermarket.supermarket.purchases.model.param.PageParam;
import org.springframework.data.domain.Page;

public interface SuperMarketService {
    SuperMarket createSuperMarket(SuperMarket superMarket);

    SuperMarket addItemsToSupermarket(int id, SuperMarketAddDto superMarketAddDto);

    Page<SuperMarket> getAll(PageParam pageParam, FilterParam filterParam);


}

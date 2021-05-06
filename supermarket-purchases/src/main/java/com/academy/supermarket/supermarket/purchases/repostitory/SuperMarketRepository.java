package com.academy.supermarket.supermarket.purchases.repostitory;

import com.academy.supermarket.supermarket.purchases.model.entities.SuperMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperMarketRepository extends JpaRepository<SuperMarket, String> {
}

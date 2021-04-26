package com.academy.supermarket.supermarket.purchases.repostitory;

import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    Optional<Item> findByName(String name);

}

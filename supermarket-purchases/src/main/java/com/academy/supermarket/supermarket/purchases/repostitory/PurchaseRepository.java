package com.academy.supermarket.supermarket.purchases.repostitory;

import com.academy.supermarket.supermarket.purchases.model.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,String> {
}

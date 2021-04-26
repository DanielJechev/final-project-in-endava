package com.academy.supermarket.supermarket.purchases.model.entities;

import com.academy.supermarket.supermarket.purchases.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item extends BaseEntity {
    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ItemType type;


}

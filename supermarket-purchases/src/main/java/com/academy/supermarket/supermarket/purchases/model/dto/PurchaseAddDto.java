package com.academy.supermarket.supermarket.purchases.model.dto;

import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class PurchaseAddDto {
    @NotBlank
    private String superMarketId;

    @NotEmpty
    private List<Item> itemList;

    @NotNull
    private String paymentType;

    private Double cashAmount;
}

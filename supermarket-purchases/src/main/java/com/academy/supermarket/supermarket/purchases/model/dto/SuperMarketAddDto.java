package com.academy.supermarket.supermarket.purchases.model.dto;

import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuperMarketAddDto {

    @NotBlank
    private String id;

    @NotNull
    private List<Item> itemList;
}

package com.academy.supermarket.supermarket.purchases.model.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilterParam {
    private String searchBy;
    private Integer searchText;
}

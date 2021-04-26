package com.academy.supermarket.supermarket.purchases.model.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageParam {
    Integer limit;
    Integer page;
}

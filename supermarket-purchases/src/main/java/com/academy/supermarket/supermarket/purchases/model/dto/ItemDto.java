package com.academy.supermarket.supermarket.purchases.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    @NotBlank(message = "Please, provide name!")
    @Length(max = 64, message = "Invalid name size. It should be max 64 characters!")
    private String name;

    @NotNull(message = "Please, provide price!")
    @Min(value = (long) 0.01, message = "Price cannot be less than 0.01 !")
    @Max(value = (long) 9999.99, message = "Price cannot be more than 9999.99 ! ")
    private Double price;

    @NotNull(message = "Please,choose valid type between FOOD, DRINKS, TECHNOLOGY, HOUSEHOLD")
    private String type;

}

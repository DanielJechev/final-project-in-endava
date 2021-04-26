package com.academy.supermarket.supermarket.purchases.model.dto;

import com.academy.supermarket.supermarket.purchases.model.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuperMarketGetDto {

    @NotBlank(message = "Please, provide name!")
    @Length(max = 64, message = "Invalid name size. It should be max 64 characters!")
    private String name;

    @NotBlank(message = "The field cannot be empty. Please, provide Address!")
    @Pattern(regexp = "^.+,.+,.+$", message = "Please enter (country,city,street) separated by comma!")
    private String address;

    @Pattern(regexp = "08[789]\\d{7}", message = "Please,enter real phone number!")
    private String phoneNumber;

    private String workHours;

    @NotNull
    private List<Item> itemList;
}

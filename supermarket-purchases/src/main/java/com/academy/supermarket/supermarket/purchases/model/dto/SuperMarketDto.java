package com.academy.supermarket.supermarket.purchases.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuperMarketDto {
    @NotBlank(message = "Please, provide name!")
    @Length(max = 64, message = "Invalid name size. It should be max 64 characters!")
    private String name;

    @NotBlank(message = "The field cannot be empty. Please, provide Address!")
    @Pattern(regexp = "^.+,.+,.+$", message = "Please enter (country,city,street) separated by comma!")
    private String address;

    @Pattern(regexp = "08[789]\\d{7}", message = "Please,enter real phone number!")
    private String phoneNumber;
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Please,enter  when (open:closed for =>example 9:22) separated by colon !")
    private String workHours;
}

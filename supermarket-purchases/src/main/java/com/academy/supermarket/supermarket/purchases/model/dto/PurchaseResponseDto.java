package com.academy.supermarket.supermarket.purchases.model.dto;

import com.academy.supermarket.supermarket.purchases.model.enums.PaymentType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseResponseDto {
    @NotBlank
    private String superMarketId;

    @NotNull
    private PaymentType paymentType;


    private Double cashAmount;

    private Double totalPrice;

    private Double cashAfterShopping;

    private LocalDateTime timePurchased;
}

package com.academy.supermarket.supermarket.purchases.model.entities;

import com.academy.supermarket.supermarket.purchases.model.enums.PaymentType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchase extends BaseEntity {
@NotBlank
    private String superMarketId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "purchase_item", joinColumns = {@JoinColumn(name = "purchase_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Item> itemList;


    private Double cashAmount;

    private Double totalPrice;

    private Double cashAfterShopping;

    private LocalDateTime timePurchased;
}

package com.academy.supermarket.supermarket.purchases.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SuperMarket extends BaseEntity {
    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String address;

    private String phoneNumber;

    private String workHours;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "superMarket_items", joinColumns = {@JoinColumn(name = "superMarket_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Item> itemList;

}

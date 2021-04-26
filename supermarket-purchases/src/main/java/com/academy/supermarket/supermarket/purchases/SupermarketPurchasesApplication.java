package com.academy.supermarket.supermarket.purchases;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class SupermarketPurchasesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupermarketPurchasesApplication.class, args);
    }

}

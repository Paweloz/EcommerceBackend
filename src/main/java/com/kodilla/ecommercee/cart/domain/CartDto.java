package com.kodilla.ecommercee.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Long userId;

}

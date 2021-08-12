package com.kodilla.ecommercee.order.domain;

import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    public Order(@NotNull LocalDate dateOfOrder, @NotNull BigDecimal price, User user){
        this.dateOfOrder = dateOfOrder;
        this.price = price;
        this.user = user;
    }

    public Order(@NotNull LocalDate dateOfOrder, @NotNull BigDecimal price, List<Product> products, User user){
        this.dateOfOrder = dateOfOrder;
        this.price = price;
        this.products = products;
        this.user = user;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull
    @Column(name = "DATE")
    private LocalDate dateOfOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "ORDERS_PRODUCTS",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> products = new ArrayList<>();
}

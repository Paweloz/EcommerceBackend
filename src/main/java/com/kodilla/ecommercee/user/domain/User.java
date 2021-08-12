package com.kodilla.ecommercee.user.domain;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    public User(String username, boolean status, String userKey, String email, String password, boolean isBlocked, List<Order> ordersId) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
        this.ordersId = ordersId;
    }

    public User(String username, boolean status, String userKey, String email, String password, boolean isBlocked) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
    }

    public User( Long id, String username, boolean status, String userKey,
                String email, String password, boolean isBlocked, List<Order> ordersId ) {
        this.Id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
        this.ordersId = ordersId;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long Id;

    @NotNull
    @Column(name = "USERNAME", unique = true)
    private String username;

    @NotNull
    @Column(name = "STATUS")
    private boolean status;

    @NotNull
    @Column(name = "USER_KEY")
    private String userKey;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "IS_BLOCKED")
    private boolean isBlocked;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> ordersId;

}

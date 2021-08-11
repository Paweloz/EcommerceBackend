package com.kodilla.ecommercee.user.domain;

import com.kodilla.ecommercee.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private boolean status;
    private String userKey;
    private String email;
    private String password;
    private boolean isBlocked;
    private List<Long> ordersId;
    private Long cartId;

}

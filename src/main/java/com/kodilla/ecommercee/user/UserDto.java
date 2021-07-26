package com.kodilla.ecommercee.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String status;
    private Long userKey;
    private boolean isBlocked;

    public void setUserKey(Long userKey) {
        Random random = new Random();
        this.userKey = random.nextLong();
    }

    public void blockUser(boolean isBlocked) {
        this.isBlocked = !isBlocked;
    }
}
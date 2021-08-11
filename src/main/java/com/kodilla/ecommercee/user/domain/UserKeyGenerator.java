package com.kodilla.ecommercee.user.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class UserKeyGenerator {

    private static String keyGenerateFrom = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public String keyGenerator() {
        StringBuffer stringKeyGenerator = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int generator = random.nextInt(keyGenerateFrom.length());
            stringKeyGenerator.append(keyGenerateFrom.charAt(generator));
        }
        String userKey = stringKeyGenerator.toString();

        return userKey;
    }

}

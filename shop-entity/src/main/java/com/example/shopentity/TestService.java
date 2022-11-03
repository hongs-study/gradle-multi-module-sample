package com.example.shopentity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Value("${shop.active-value}")
    private String activeValue;

    public void testActive() {
        System.out.println(">> " + activeValue);
    }
}

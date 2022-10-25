package com.example.shopcore.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatusCode {
    NOMAL("일반고객"),
    VIP("VIP고객")
    ;

    private final String title;
}

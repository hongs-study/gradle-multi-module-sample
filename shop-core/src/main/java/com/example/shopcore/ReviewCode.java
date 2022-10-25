package com.example.shopcore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReviewCode {
    NOMAL("정상"),
    DELETE("삭제")
    ;

    private final String title;
}

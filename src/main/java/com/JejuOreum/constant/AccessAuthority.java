package com.JejuOreum.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccessAuthority {
    USER("01"),
    GUEST("02"),
    ADMIN("99");


    private final String authorityCode;
}

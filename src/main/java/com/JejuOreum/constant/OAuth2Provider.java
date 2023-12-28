package com.JejuOreum.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuth2Provider {
    GOOGLE("G"),
    NAVER("N"),
    KAKAO("K");

    private final String siteCd;
}

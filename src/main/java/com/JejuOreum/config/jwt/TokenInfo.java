package com.JejuOreum.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {
    String grantType;
    String accessToken;
    String refreshToken;
}

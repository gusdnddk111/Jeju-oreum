package com.JejuOreum.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@ConfigurationProperties(prefix="login")
public class LoginApiProperties {
    Property google = new Property();
    Property naver = new Property();
    Property kakao = new Property();

    @Getter
    @Setter
    @ToString
    public static class Property {
        private String clientId;
        private String clientSecret;
        private String loginRequestUrl;
        private String tokenRequestUrl;
        private String userinfoRequestUrl;
        private String redirectUri;
    }
}

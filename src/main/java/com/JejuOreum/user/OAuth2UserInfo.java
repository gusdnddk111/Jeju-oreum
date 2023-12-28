package com.JejuOreum.user;

import com.JejuOreum.constant.OAuth2Provider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
@Setter
@ToString
public abstract class OAuth2UserInfo {

    private String accessToken;

    private OAuth2Provider provider;

    private String id;
    private String email;
    private String birthday;
    private String genderCd;

    public OAuth2UserInfo(String accessToken){
        super();
        this.accessToken = accessToken;
    }

    public abstract void setUserInfo(JSONObject jsonObject);
}

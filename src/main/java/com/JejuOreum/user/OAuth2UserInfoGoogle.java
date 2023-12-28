package com.JejuOreum.user;

import com.JejuOreum.constant.OAuth2Provider;
import org.json.simple.JSONObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class OAuth2UserInfoGoogle extends OAuth2UserInfo {

    public OAuth2UserInfoGoogle(String accessToken){
        super(accessToken);
        this.setProvider(OAuth2Provider.GOOGLE);
    }

    @Override
    public void setUserInfo(JSONObject jsonObject){

        String id = jsonObject.containsKey("id") ? jsonObject.get("id").toString() : null;

        this.setEmail(jsonObject.get("email").toString());
        this.setId(id);
    }
}

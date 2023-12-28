package com.JejuOreum.user;

import com.JejuOreum.constant.OAuth2Provider;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class OAuth2UserInfoNaver extends OAuth2UserInfo {

    public OAuth2UserInfoNaver(String accessToken){
        super(accessToken);
        this.setProvider(OAuth2Provider.NAVER);
    }

    @Override
    public void setUserInfo(JSONObject jsonObject){
        Map<String, Object> response = (Map<String, Object>) jsonObject.get("response");
        String id = response.containsKey("id") ? response.get("id").toString() : null;
        String birthyear = response.containsKey("birthyear") ? response.get("birthyear").toString() : null;
        String birthday = response.containsKey("birthday") ? response.get("birthday").toString().replace("-","") : null;
        String gender = response.containsKey("gender") ? response.get("gender").toString() : null;

        this.setEmail(response.get("email").toString());
        this.setId(id);
        this.setBirthday(birthyear != null && birthday != null ? birthyear+birthday : null);
        this.setGenderCd(gender.equals("M") ? "1" : gender.equals("F") ? "2" : null);
    }
}

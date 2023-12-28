package com.JejuOreum.user;

import com.JejuOreum.constant.OAuth2Provider;
import org.json.simple.JSONObject;

import java.util.Map;

public class OAuth2UserInfoKakao extends OAuth2UserInfo {

    public OAuth2UserInfoKakao(String accessToken){
        super(accessToken);
        this.setProvider(OAuth2Provider.KAKAO);
    }

    @Override
    public void setUserInfo(JSONObject jsonObject){
        JSONObject kakaoAccount = (JSONObject) jsonObject.get("kakao_account");

        String id = jsonObject.containsKey("id") ? jsonObject.get("id").toString() : null;
        String birthyear = kakaoAccount.containsKey("birthyear") ? kakaoAccount.get("birthyear").toString() : null;
        String birthday = kakaoAccount.containsKey("birthday") ? kakaoAccount.get("birthday").toString() : null;

        this.setEmail(kakaoAccount.get("email").toString());
        this.setId(id);
        this.setBirthday(birthyear != null && birthday != null ? birthyear+birthday : null);
    }
}

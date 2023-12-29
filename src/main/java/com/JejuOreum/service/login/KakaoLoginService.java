package com.JejuOreum.service.login;

import com.JejuOreum.user.OAuth2UserInfo;
import com.JejuOreum.user.OAuth2UserInfoKakao;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoLoginService extends CommonLoginService {

    @Autowired
    public KakaoLoginService(@Value("${login.kakao.client-id}") String oAuthClientId,
                             @Value("${login.kakao.client-secret}") String oAuthClientSecret,
                             @Value("${login.kakao.login-request-url}") String loginRequestUrl,
                             @Value("${login.kakao.token-request-url}") String tokenRequestUrl,
                             @Value("${login.kakao.userinfo-request-url}") String userinfoRequestUrl,
                             @Value("${login.kakao.redirect-uri}") String redirectUri
                             ){
        this.oAuthClientId = oAuthClientId;
        this.oAuthClientSecret = oAuthClientSecret;
        this.loginRequestUrl = loginRequestUrl;
        this.tokenRequestUrl = tokenRequestUrl;
        this.userinfoRequestUrl = userinfoRequestUrl;
        this.redirectUri = redirectUri;
    }

    @Override
    public String getLoginRequestUrl() throws Exception {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("response_type","code");
        urlParams.put("client_id", oAuthClientId);
        urlParams.put("redirect_uri", redirectUri);

        return httpRequestManager.getUri(loginRequestUrl, urlParams);
    }


    @Override
    public JSONObject getToken(Map<String, String> reqParams) throws Exception {

        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("grant_type","authorization_code");
        bodyParams.put("client_id", oAuthClientId);
        bodyParams.put("client_secret", oAuthClientSecret);
        bodyParams.put("redirect_uri", redirectUri);
        bodyParams.put("code", reqParams.get("code").toString());

        HttpHeaders headerParams = new HttpHeaders();
        headerParams.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headerParams.add("Accept", "application/json");

        JSONObject responseBody = httpRequestManager.httpRequestPost(tokenRequestUrl, headerParams, bodyParams);

        return responseBody;
    }

    @Override
    public OAuth2UserInfo getUserInfo(Map<String, String> reqParams) throws Exception {
        JSONObject tokenResponse = this.getToken(reqParams);
        OAuth2UserInfo userInfo = new OAuth2UserInfoKakao(tokenResponse.get("access_token").toString());

        HttpHeaders headerParams = new HttpHeaders();
        headerParams.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headerParams.add("Accept", "application/json");
        headerParams.set("Authorization", "Bearer " + userInfo.getAccessToken());

        JSONObject result = httpRequestManager.httpRequestPost(userinfoRequestUrl, headerParams, null);
        userInfo.setUserInfo(result);

        return userInfo;
    }
}

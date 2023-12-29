package com.JejuOreum.service.login;

import com.JejuOreum.user.OAuth2UserInfo;
import com.JejuOreum.user.OAuth2UserInfoNaver;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class NaverLoginService extends CommonLoginService {

    @Autowired
    public NaverLoginService(@Value("${login.naver.client-id}") String oAuthClientId,
                             @Value("${login.naver.client-secret}") String oAuthClientSecret,
                             @Value("${login.naver.login-request-url}") String loginRequestUrl,
                             @Value("${login.naver.token-request-url}") String tokenRequestUrl,
                             @Value("${login.naver.userinfo-request-url}") String userinfoRequestUrl,
                             @Value("${login.naver.redirect-uri}") String redirectUri
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
        urlParams.put("state", UUID.randomUUID().toString());

        return httpRequestManager.getUri(loginRequestUrl, urlParams);
    }


    @Override
    public JSONObject getToken(Map<String, String> reqParams) throws Exception {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("grant_type","authorization_code");
        urlParams.put("client_id", oAuthClientId);
        urlParams.put("client_secret", oAuthClientSecret);
        urlParams.put("code", reqParams.get("code").toString());
        urlParams.put("state", reqParams.get("state").toString());

        JSONObject responseBody = httpRequestManager.httpRequestGet(tokenRequestUrl, urlParams);

        return responseBody;
    }

    @Override
    public OAuth2UserInfo getUserInfo(Map<String, String> reqParams) throws Exception {
        JSONObject tokenResponse = this.getToken(reqParams);
        OAuth2UserInfo userInfo = new OAuth2UserInfoNaver(tokenResponse.get("access_token").toString());

        HttpHeaders headerParams = new HttpHeaders();
        headerParams.set("Authorization", "Bearer " + userInfo.getAccessToken());

        JSONObject result = httpRequestManager.httpRequestGet(userinfoRequestUrl, headerParams, null);
        userInfo.setUserInfo(result);

        return userInfo;
    }
}

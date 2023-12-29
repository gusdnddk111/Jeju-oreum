package com.JejuOreum.service.login;

import com.JejuOreum.user.OAuth2UserInfo;
import com.JejuOreum.user.OAuth2UserInfoGoogle;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleLoginService extends CommonLoginService {

    @Autowired
    public GoogleLoginService(@Value("${login.google.client-id}") String oAuthClientId,
                              @Value("${login.google.client-secret}") String oAuthClientSecret,
                              @Value("${login.google.login-request-url}") String loginRequestUrl,
                              @Value("${login.google.token-request-url}") String tokenRequestUrl,
                              @Value("${login.google.userinfo-request-url}") String userinfoRequestUrl,
                              @Value("${login.google.redirect-uri}") String redirectUri
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
        urlParams.put("scope", "email profile");

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

        JSONObject responseBody = httpRequestManager.httpRequestPost(tokenRequestUrl, bodyParams);

        return responseBody;
    }

    @Override
    public OAuth2UserInfo getUserInfo(Map<String, String> reqParams) throws Exception {
        JSONObject tokenResponse = this.getToken(reqParams);
        OAuth2UserInfo userInfo = new OAuth2UserInfoGoogle(tokenResponse.get("access_token").toString());

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("access_token", tokenResponse.get("access_token").toString());

        JSONObject result = httpRequestManager.httpRequestGet(userinfoRequestUrl, urlParams);
        userInfo.setUserInfo(result);

        return userInfo;
    }
}

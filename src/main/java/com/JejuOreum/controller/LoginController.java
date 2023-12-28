package com.JejuOreum.controller;

import com.JejuOreum.service.loginService.GoogleLoginService;
import com.JejuOreum.service.loginService.KakaoLoginService;
import com.JejuOreum.service.loginService.NaverLoginService;
import com.JejuOreum.user.OAuth2UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class LoginController {

    private final GoogleLoginService googleLoginService;
    private final NaverLoginService naverLoginService;
    private final KakaoLoginService kakaoLoginService;

    @Autowired
    public LoginController(NaverLoginService naverLoginService,
                           GoogleLoginService googleLoginService,
                           KakaoLoginService kakaoLoginService){
        this.naverLoginService = naverLoginService;
        this.googleLoginService =googleLoginService;
        this.kakaoLoginService = kakaoLoginService;
    }

    @GetMapping("/login/google/api/request")
    public ResponseEntity<Object> loginRequestOfGoogle() throws Exception{
        log.info("asdf");
        String url = googleLoginService.getLoginRequestUrl();

        URI redirectUri = new URI(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }


    @GetMapping("/login/google/api/result")
    public ResponseEntity<Object> googleLoginResult(@RequestParam Map<String, String> reqParams) throws Exception{
        googleLoginService.getLoginResult(reqParams);
        return null;
    }


    @GetMapping("/login/naver/api/request")
    public ResponseEntity<Object> loginRequestOfNaver() throws Exception{
        String url = naverLoginService.getLoginRequestUrl();

        URI redirectUri = new URI(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }


    @GetMapping("/login/naver/api/result")
    public ResponseEntity<Object> naverLoginResult(@RequestParam Map<String, String> reqParams) throws Exception {
        naverLoginService.getLoginResult(reqParams);

        URI redirectUri = new URI("/");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/login/kakao/api/request")
    public ResponseEntity<Object> loginRequestOfKakao() throws Exception{
        String url = kakaoLoginService.getLoginRequestUrl();

        URI redirectUri = new URI(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/login/kakao/api/result")
    public ResponseEntity<Object> kakaoLoginResult(@RequestParam Map<String, String> reqParams) throws Exception {
        kakaoLoginService.getLoginResult(reqParams);
        return null;
    }

}

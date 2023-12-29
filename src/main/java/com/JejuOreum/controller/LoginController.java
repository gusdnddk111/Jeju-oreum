package com.JejuOreum.controller;

import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.service.login.GoogleLoginService;
import com.JejuOreum.service.login.KakaoLoginService;
import com.JejuOreum.service.login.NaverLoginService;
import com.JejuOreum.service.member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    private final ObjectMapper mapper;
    private final JSONParser parser;
    private final GoogleLoginService googleLoginService;
    private final NaverLoginService naverLoginService;
    private final KakaoLoginService kakaoLoginService;
    private final MemberService memberService;

    @Autowired
    public LoginController(NaverLoginService naverLoginService,
                           GoogleLoginService googleLoginService,
                           KakaoLoginService kakaoLoginService,
                           MemberService memberService){

        this.mapper = new ObjectMapper();
        this.parser = new JSONParser();

        this.naverLoginService = naverLoginService;
        this.googleLoginService =googleLoginService;
        this.kakaoLoginService = kakaoLoginService;
        this.memberService = memberService;
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
    public JSONObject googleLoginResult(@RequestParam Map<String, String> reqParams) throws Exception{
        MemberEntity memberEntity = googleLoginService.getLoginResult(reqParams);
        JSONObject result = (JSONObject) parser.parse(memberEntity.toString());

        return result;
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
    public JSONObject naverLoginResult(@RequestParam Map<String, String> reqParams) throws Exception {
        MemberEntity memberEntity = naverLoginService.getLoginResult(reqParams);
        JSONObject result = (JSONObject) parser.parse(memberEntity.toString());

        return result;
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
    public JSONObject kakaoLoginResult(@RequestParam Map<String, String> reqParams) throws Exception {
        MemberEntity memberEntity = kakaoLoginService.getLoginResult(reqParams);
        JSONObject result = (JSONObject) parser.parse(memberEntity.toString());

        return result;
    }

    @PostMapping("/login/joinMember")
    public ResponseEntity<Object> joinMember(@RequestBody Map<String,Object> body) throws Exception {
        MemberEntity memberEntity = mapper.convertValue(body, MemberEntity.class);
        memberService.joinMember(memberEntity);

        return null;
    }
}

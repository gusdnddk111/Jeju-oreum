package com.JejuOreum.service.login;

import com.JejuOreum.config.restTemplate.HttpRequestManager;
import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.model.service.MemberService;
import com.JejuOreum.user.OAuth2UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
public abstract class CommonLoginService {

    protected HttpRequestManager httpRequestManager;
    private MemberService memberService;

    protected String oAuthClientId;
    protected String oAuthClientSecret;
    protected String loginRequestUrl;
    protected String tokenRequestUrl;
    protected String userinfoRequestUrl;
    protected String redirectUri;


    @Autowired
    public final void setHttpRequestManager(HttpRequestManager httpRequestManager) {
        this.httpRequestManager = httpRequestManager;
    }

    @Autowired
    public final void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired
    public final void setMemberDbService(MemberService memberService) {
        this.memberService = memberService;
    }

    // Login 성공 후 처리
    public MemberEntity getLoginResult(Map<String, String> reqParams) throws Exception {
        OAuth2UserInfo userInfo = this.getUserInfo(reqParams);

        MemberEntity memberEntity = MemberEntity.builder()
                .joinSiteCd(userInfo.getProvider().getSiteCd())
                .email(userInfo.getEmail())
                .birthday(userInfo.getBirthday())
                .genderCd(userInfo.getGenderCd())
                //.nickname("test_user")
                .build();

        return memberEntity;
        /*
        // Email 기준 신규 회원
        if(memberDbService.findByEmail(userInfo.getEmail()).isEmpty()){
            // TODO : redirect 추가정보입력화면
        }else{
            // TODO : redirect 메인화면
        }
        */
    }

    public abstract String getLoginRequestUrl() throws Exception;
    protected abstract JSONObject getToken(Map<String, String> reqParams) throws Exception;
    protected abstract OAuth2UserInfo getUserInfo(Map<String, String> reqParams) throws Exception;

}

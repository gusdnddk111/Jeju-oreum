package com.JejuOreum.service.loginService;

import com.JejuOreum.config.restTemplate.HttpRequestManager;
import com.JejuOreum.constant.AccessAuthority;
import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.model.entity.MemberSsnMgmtEntity;
import com.JejuOreum.model.repository.MemberRepository;
import com.JejuOreum.model.service.MemberService;
import com.JejuOreum.model.service.MemberSsnMgmtService;
import com.JejuOreum.user.OAuth2UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Slf4j
public abstract class CommonLoginService {

    protected HttpRequestManager httpRequestManager;
    private MemberService memberService;
    private MemberSsnMgmtService memberSsnMgmtService;

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
    public final void setMemberSsnMgmtService(MemberSsnMgmtService memberSsnMgmtService) {
        this.memberSsnMgmtService = memberSsnMgmtService;
    }

    // Login 성공 후 처리
    public void getLoginResult(Map<String, String> reqParams) throws Exception {
        OAuth2UserInfo userInfo = this.getUserInfo(reqParams);

        MemberEntity memberEntity = MemberEntity.builder()
                .joinSiteCd(userInfo.getProvider().getSiteCd())
                .email(userInfo.getEmail())
                .birthday(userInfo.getBirthday())
                .genderCd(userInfo.getGenderCd())
                .nickname("test_user")
                .build();

        // Email 기준 신규 회원
        if(memberService.findByEmail(userInfo.getEmail()).isEmpty()){
            memberEntity.setCustNo(memberService.maxCustNo()+1);
            MemberEntity resultEntity = memberService.save(memberEntity);

            MemberSsnMgmtEntity memberSsnMgmtEntity = new MemberSsnMgmtEntity();
            memberSsnMgmtEntity.setCustNo(resultEntity.getCustNo());
            memberSsnMgmtEntity.setSessionKey(AccessAuthority.USER.getAuthorityCode());
            memberSsnMgmtEntity.setSessionKey(UUID.randomUUID().toString());
            memberSsnMgmtService.save(memberSsnMgmtEntity);
        }
        // TODO : email 기준 신규회원인지 기존회원인지 판단
        // TODO : 신규회원이면 SEQ 채번하여 DB INSERT, 기존회원이면 DB UPDATE
        // TODO : 결과에 따라 Redirect url return
    }

    public abstract String getLoginRequestUrl() throws Exception;
    protected abstract JSONObject getToken(Map<String, String> reqParams) throws Exception;
    protected abstract OAuth2UserInfo getUserInfo(Map<String, String> reqParams) throws Exception;

}

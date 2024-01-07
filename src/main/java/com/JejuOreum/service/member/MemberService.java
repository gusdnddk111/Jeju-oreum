package com.JejuOreum.service.member;

import com.JejuOreum.config.jwt.JwtTokenProvider;
import com.JejuOreum.config.jwt.TokenInfo;
import com.JejuOreum.constant.AccessAuthority;
import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.model.entity.MemberSsnMgmtEntity;
import com.JejuOreum.model.service.MemberDbService;
import com.JejuOreum.model.service.MemberSsnMgmtDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberDbService memberDbService;
    private final MemberSsnMgmtDbService memberSsnMgmtDbService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MemberService(MemberDbService memberDbService,
                         MemberSsnMgmtDbService memberSsnMgmtDbService,
                         JwtTokenProvider jwtTokenProvider
    ){
        this.memberDbService = memberDbService;
        this.memberSsnMgmtDbService = memberSsnMgmtDbService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public TokenInfo joinMember(MemberEntity memberEntity){

        // 고객 DB에 신규회원으로 저장
        memberEntity.setCustNo(memberDbService.maxCustNo()+1);
        memberEntity.setLevel(0L);
        MemberEntity resultEntity = memberDbService.save(memberEntity);

        Long newCustNo = resultEntity.getCustNo();
        String accessAuthority = AccessAuthority.USER.getAuthorityCode();

        // jwt token 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(resultEntity.getCustNo(), accessAuthority);

        // RefreshToken 값 DB 저장
        MemberSsnMgmtEntity memberSsnMgmtEntity = new MemberSsnMgmtEntity();
        memberSsnMgmtEntity.setCustNo(newCustNo);
        memberSsnMgmtEntity.setAccessAuthority(accessAuthority);
        memberSsnMgmtEntity.setRefreshToken(tokenInfo.getRefreshToken());

        memberSsnMgmtDbService.save(memberSsnMgmtEntity);

        return tokenInfo;
    }

    public TokenInfo reIssueAccessToken(String refreshToken) throws Exception{

        Long custNo = jwtTokenProvider.getCustNo(refreshToken);
        Optional<MemberSsnMgmtEntity> memberEntity = memberSsnMgmtDbService.findByCustNo(custNo);

        if(memberEntity.isEmpty()){
            throw new Exception("찾을 수 없는 회원입니다.");
        }

        if(refreshToken.equals(memberEntity.get().getRefreshToken())){
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(custNo, memberEntity.get().getAccessAuthority());
            return tokenInfo;
        }else{
            // TODO : redirect?
            throw new Exception("RefreshToken이 올바르지 않습니다.");
        }
    }

}

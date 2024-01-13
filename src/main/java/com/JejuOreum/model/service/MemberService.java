package com.JejuOreum.model.service;

import com.JejuOreum.config.jwt.JwtTokenProvider;
import com.JejuOreum.config.jwt.TokenInfo;
import com.JejuOreum.constant.AccessAuthority;
import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.model.entity.MemberSsnMgmtEntity;
import com.JejuOreum.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private MemberSsnMgmtService memberSsnMgmtService;
    private MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MemberService(MemberRepository memberRepository,
                         JwtTokenProvider jwtTokenProvider){
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Optional<MemberEntity> findByEmail(String email){
        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(email);
        return memberEntity;
    }

    public MemberEntity save(MemberEntity memberEntity){
        MemberEntity resultEntity = memberRepository.save(memberEntity);
        return resultEntity;
    }

    public Long findMaxCustNo(){
        return memberRepository.findMaxCustNo();
    }

    public Optional<MemberEntity> getMemberInfo(Long custNo){
        return memberRepository.findByCustNo(custNo);
    }

    public TokenInfo joinMember(MemberEntity memberEntity){

        // 고객 DB에 신규회원으로 저장
        memberEntity.setCustNo(this.findMaxCustNo()+1);
        memberEntity.setLevel(0L);
        MemberEntity resultEntity = this.save(memberEntity);

        Long newCustNo = resultEntity.getCustNo();
        String accessAuthority = AccessAuthority.USER.getAuthorityCode();

        // jwt token 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(resultEntity.getCustNo(), accessAuthority);

        // RefreshToken 값 DB 저장
        MemberSsnMgmtEntity memberSsnMgmtEntity = new MemberSsnMgmtEntity();
        memberSsnMgmtEntity.setCustNo(newCustNo);
        memberSsnMgmtEntity.setAccessAuthority(accessAuthority);
        memberSsnMgmtEntity.setRefreshToken(tokenInfo.getRefreshToken());

        memberSsnMgmtService.save(memberSsnMgmtEntity);

        return tokenInfo;
    }

    public TokenInfo reIssueAccessToken(String refreshToken) throws Exception{

        Long custNo = jwtTokenProvider.getCustNo(refreshToken);
        Optional<MemberSsnMgmtEntity> memberEntity = memberSsnMgmtService.findByCustNo(custNo);

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

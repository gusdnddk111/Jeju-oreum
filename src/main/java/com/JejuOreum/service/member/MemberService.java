package com.JejuOreum.service.member;

import com.JejuOreum.constant.AccessAuthority;
import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.model.entity.MemberSsnMgmtEntity;
import com.JejuOreum.model.service.MemberDbService;
import com.JejuOreum.model.service.MemberSsnMgmtDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberService {

    private final MemberDbService memberDbService;
    private final MemberSsnMgmtDbService memberSsnMgmtDbService;

    @Autowired
    public MemberService(MemberDbService memberDbService,
                         MemberSsnMgmtDbService memberSsnMgmtDbService
    ){
        this.memberDbService = memberDbService;
        this.memberSsnMgmtDbService = memberSsnMgmtDbService;
    }

    public void joinMember(MemberEntity memberEntity){
        memberEntity.setCustNo(memberDbService.maxCustNo()+1);
        MemberEntity resultEntity = memberDbService.save(memberEntity);

        MemberSsnMgmtEntity memberSsnMgmtEntity = new MemberSsnMgmtEntity();
        memberSsnMgmtEntity.setCustNo(resultEntity.getCustNo());
        memberSsnMgmtEntity.setAccessAuthority(AccessAuthority.USER.getAuthorityCode());
        memberSsnMgmtEntity.setSessionKey(UUID.randomUUID().toString());

        memberSsnMgmtDbService.save(memberSsnMgmtEntity);
    }
}

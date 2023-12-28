package com.JejuOreum.model.service;

import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.model.entity.MemberSsnMgmtEntity;
import com.JejuOreum.model.repository.MemberRepository;
import com.JejuOreum.model.repository.MemberSsnMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberSsnMgmtService {

    private MemberSsnMgmtRepository memberSsnMgmtRepository;

    @Autowired
    public MemberSsnMgmtService(MemberSsnMgmtRepository memberSsnMgmtRepository){
        this.memberSsnMgmtRepository = memberSsnMgmtRepository;
    }

    public Optional<MemberSsnMgmtEntity> findByCustNo(Long custNo){
        Optional<MemberSsnMgmtEntity> memberSsnMgmtEntity = memberSsnMgmtRepository.findByCustNo(custNo);
        return memberSsnMgmtEntity;
    }

    public Optional<MemberSsnMgmtEntity> findBySessionKey(String sessionKey){
        Optional<MemberSsnMgmtEntity> memberSsnMgmtEntity = memberSsnMgmtRepository.findBySessionKey(sessionKey);
        return memberSsnMgmtEntity;
    }

    public MemberSsnMgmtEntity save(MemberSsnMgmtEntity memberSsnMgmtEntity){
        MemberSsnMgmtEntity resultEntity = memberSsnMgmtRepository.save(memberSsnMgmtEntity);
        return resultEntity;
    }
}

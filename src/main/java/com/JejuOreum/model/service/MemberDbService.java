package com.JejuOreum.model.service;

import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberDbService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberDbService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Optional<MemberEntity> findByEmail(String email){
        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(email);
        return memberEntity;
    }

    public MemberEntity save(MemberEntity memberEntity){
        MemberEntity resultEntity = memberRepository.save(memberEntity);
        return resultEntity;
    }

    public Long maxCustNo(){
        return memberRepository.findMaxCustNo();
    }
}

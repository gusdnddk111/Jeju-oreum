package com.JejuOreum.model.repository;

import com.JejuOreum.model.entity.MemberEntity;
import com.JejuOreum.model.entityId.MemberEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<MemberEntity, MemberEntityId> {

    Optional<MemberEntity> findByEmail(String email);

    @Query(value = "SELECT COALESCE(MAX(u.custNo),0) FROM MemberEntity u")
    Long findMaxCustNo();

    Optional<MemberEntity> findByCustNo(Long custNo);
}


package com.JejuOreum.model.repository;

import com.JejuOreum.model.entity.MemberSsnMgmtEntity;
import com.JejuOreum.model.entityId.MemberSsnMgmtEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberSsnMgmtRepository extends JpaRepository<MemberSsnMgmtEntity, MemberSsnMgmtEntityId> {
     Optional<MemberSsnMgmtEntity> findByCustNo(Long custNo);
}

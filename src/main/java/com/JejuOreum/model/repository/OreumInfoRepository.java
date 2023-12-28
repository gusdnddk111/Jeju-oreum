package com.JejuOreum.model.repository;

import com.JejuOreum.model.entity.OreumInfoEntity;
import com.JejuOreum.model.entityId.OreumInfoEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OreumInfoRepository extends JpaRepository<OreumInfoEntity, OreumInfoEntityId> {

    //Optional<OreumInfoEntity> xxx();
    
}


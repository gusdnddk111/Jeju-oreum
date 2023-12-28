package com.JejuOreum.model.repository;

import com.JejuOreum.model.entity.OreumVisitEntity;
import com.JejuOreum.model.entityId.OreumVisitEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OreumVisitRepository extends JpaRepository<OreumVisitEntity, OreumVisitEntityId> {

    //Optional<OreumVisitEntity> xxx();
    
}


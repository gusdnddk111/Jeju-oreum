package com.JejuOreum.model.repository;

import com.JejuOreum.model.entity.OreumVisitEntity;
import com.JejuOreum.model.entity.OreumVisitPicEntity;
import com.JejuOreum.model.entityId.OreumVisitEntityId;
import com.JejuOreum.model.entityId.OreumVisitPicEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OreumVisitPicRepository extends JpaRepository<OreumVisitPicEntity, OreumVisitPicEntityId> {

    //Optional<OreumVisitPicEntity> xxx();
    
}


package com.JejuOreum.model.repository;

import com.JejuOreum.model.entity.OreumInfoEntity;
import com.JejuOreum.model.entityId.OreumInfoEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OreumInfoRepository extends JpaRepository<OreumInfoEntity, OreumInfoEntityId> {

    List<OreumInfoEntity> findAllByLongitudeBetweenAndLatitudeBetween(float minLongitude, float maxLongitude, float minLatitude, float maxLatitude);
}


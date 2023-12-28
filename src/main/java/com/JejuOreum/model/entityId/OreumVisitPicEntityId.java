package com.JejuOreum.model.entityId;

import lombok.Data;

import java.io.Serializable;

@Data
public class OreumVisitPicEntityId implements Serializable {
    private Long custNo;
    private Long oreumId;
    private Long picSeq;
}

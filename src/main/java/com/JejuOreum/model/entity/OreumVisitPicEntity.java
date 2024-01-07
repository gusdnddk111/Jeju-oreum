package com.JejuOreum.model.entity;

import com.JejuOreum.model.entityId.OreumVisitPicEntityId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@DynamicInsert
@IdClass(OreumVisitPicEntityId.class)
@Table(name = "OREUM_VISIT_PIC", schema = "OREUM")
public class OreumVisitPicEntity {

    @Id
    @Column(name = "CUST_NO")
    private Long custNo;

    @Id
    @Column(name = "OREUM_ID")
    private Long oreumId;

    @Id
    @Column(name = "PIC_SEQ")
    private Long picSeq;

    @Column(name = "PIC_CONTENT")
    private String picContent;

    @Column(name = "EXT_TYPE")
    private String extType;

    @Column(name = "SIZE")
    private Long size;

    /* 등록일시 TIMESTAMP */
    @Column(name = "REG_DATE", updatable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    /* 등록자ID VARCHAR(20) */
    @Column(name = "REG_OPRT")
    private String regOprt;

    /* 수정일시 TIMESTAMP */
    @Column(name = "UPD_DATE")
    @UpdateTimestamp
    private LocalDateTime updDate;

    /* 수정자ID VARCHAR(20) */
    @Column(name = "UPD_OPRT")
    private String updOprt;

}



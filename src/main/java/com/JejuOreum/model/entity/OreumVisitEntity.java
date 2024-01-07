package com.JejuOreum.model.entity;

import com.JejuOreum.model.entityId.OreumVisitEntityId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
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
@IdClass(OreumVisitEntityId.class)
@Table(name = "OREUM_VISIT", schema = "OREUM")
public class OreumVisitEntity {

    @Id
    @Column(name = "CUST_NO")
    private Long custNo;

    @Id
    @Column(name = "OREUM_ID")
    private Long oreumId;

    @Column(name = "VISIT_DATE")
    private LocalDateTime visitDate;

    @Column(name = "VISIT_COMMENT")
    private String visitComment;

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



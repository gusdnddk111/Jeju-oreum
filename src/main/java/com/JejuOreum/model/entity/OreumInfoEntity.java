package com.JejuOreum.model.entity;

import com.JejuOreum.model.entityId.OreumInfoEntityId;
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
@IdClass(OreumInfoEntityId.class)
@Table(name = "OREUM_INFO", schema = "OREUM")
public class OreumInfoEntity {

    @Id
    @Column(name = "OREUM_ID", updatable = false)
    private Long oreumId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OLD_ADDRESS")
    private String oldAddress;

    @Column(name = "NEW_ADDRESS")
    private String newAddress;

    @Column(name = "LONGTITUDE")
    private Float longtitude;

    @Column(name = "LATITUDE")
    private Float latitude;

    @Column(name = "TEL_NO")
    private String telNo;

    @Column(name = "KAKAO_MAP_URL")
    private String kakaoMapUrl;

    @Column(name = "SIZE_CD")
    private String size_cd;

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



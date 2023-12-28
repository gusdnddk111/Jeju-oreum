package com.JejuOreum.model.entity;

import com.JejuOreum.constant.AccessAuthority;
import com.JejuOreum.model.entityId.MemberSsnMgmtEntityId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@IdClass(MemberSsnMgmtEntityId.class)
@DynamicInsert
@Table(name = "MEMBER_SSN_MGMT", schema = "OREUM")
public class MemberSsnMgmtEntity {

    @Id
    @Column(name = "CUST_NO", updatable = false)
    private Long custNo;

    @Column(name = "SESSION_KEY")
    private String sessionKey;

    @Column(name = "ACCESS_AUTHORITY")
    private String accessAuthority;

    /* 등록일시 TIMESTAMP */
    @Column(name = "REG_DATE", updatable = false)
    private LocalDateTime regDate;

    /* 등록자ID VARCHAR(20) */
    @Column(name = "REG_OPRT")
    private String regOprt;

    /* 수정일시 TIMESTAMP */
    @Column(name = "UPD_DATE")
    private LocalDateTime updDate;

    /* 수정자ID VARCHAR(20) */
    @Column(name = "UPD_OPRT")
    private String updOprt;

}



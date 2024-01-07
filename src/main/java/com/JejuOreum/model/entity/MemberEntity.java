package com.JejuOreum.model.entity;

import com.JejuOreum.constant.OAuth2Provider;
import com.JejuOreum.model.entityId.MemberEntityId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@IdClass(MemberEntityId.class)
@Table(name = "MEMBER", schema = "OREUM")
public class MemberEntity {

    @Builder
    public MemberEntity(String joinSiteCd, String birthday, String genderCd, String email, String nickname){
        this.joinSiteCd = joinSiteCd;
        this.birthday = birthday;
        this.genderCd = genderCd;
        this.email = email;
        this.nickname = nickname;
    }

    @Id
    @Column(name = "CUST_NO", updatable = false)
    private Long custNo;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "LEVEL")
    private Long level;

    @Column(name = "JOIN_SITE_CD")
    private String joinSiteCd;

    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "GENDER_CD")
    private String genderCd;

    @Column(name = "EMAIL")
    private String email;

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



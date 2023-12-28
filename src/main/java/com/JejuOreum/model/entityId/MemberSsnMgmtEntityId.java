package com.JejuOreum.model.entityId;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Data
public class MemberSsnMgmtEntityId implements Serializable {
    private Long custNo;
}

package com.resumly.resumeapi.core.domain;

import com.resumly.resumeapi.modules.core.enums.EntityFlag;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseEntity extends AbstractAuditingEntity {

    @Column
    private EntityFlag entityFlag;
}

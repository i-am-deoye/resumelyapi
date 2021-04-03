package com.resumly.resumeapi.modules.resume.domain;

import com.resumly.resumeapi.core.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.util.function.Consumer;


@Data
@MappedSuperclass
public abstract class ResumeOwnerID extends BaseEntity {
}

package com.resumly.resumeapi.modules.resume.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resumly.resumeapi.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.function.Consumer;

@Data
@Entity(name = "experiences")
@EqualsAndHashCode(callSuper = true)
public class Experience extends ResumeOwnerID {

    @ManyToOne(optional = false)
    @JoinColumn(name="resume_id", referencedColumnName = "id")
    @JsonIgnore
    private Resume resume;

    @Column private String jobTitle ;
    @Column private String employer ;
    @Column private String startDate ;
    @Column private String endDate ;
    @Column private String city ;
    @Column private String description ;
    @Column private Boolean isWorkingHere;


    @Override
    public String toString() {
        return super.toString();
    }
}

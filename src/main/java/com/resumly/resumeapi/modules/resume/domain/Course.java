package com.resumly.resumeapi.modules.resume.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "courses")
@EqualsAndHashCode(callSuper = true)
public class Course extends ResumeOwnerID {
    @ManyToOne(optional = false)
    @JoinColumn(name="resume_id", referencedColumnName = "id")
    @JsonIgnore
    private Resume resume;

    @Column private String name;
    @Column private String institution;
    @Column private String startDate;
    @Column private String endDate;
    @Column private Boolean currentlyWorkHere = false;

    @Override
    public String toString() {
        return super.toString();
    }
}

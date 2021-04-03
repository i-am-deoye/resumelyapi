package com.resumly.resumeapi.modules.resume.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "customs")
@EqualsAndHashCode(callSuper = true)
public class Custom extends ResumeOwnerID{
    @ManyToOne(optional = false)
    @JoinColumn(name="resume_id", referencedColumnName = "id")
    @JsonIgnore
    private Resume resume;

    @Column private String customName;
    @Column private String title;
    @Column private String city;
    @Column private String startDate;
    @Column private String endDate;
    @Column private Boolean currentlyWorkHere = false;
    @Column private Boolean showOnlyEndDate = false;
    @Column private Boolean hideDates = false;
    @Column private String description;

    @Override
    public String toString() {
        return super.toString();
    }
}

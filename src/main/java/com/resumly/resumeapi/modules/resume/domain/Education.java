package com.resumly.resumeapi.modules.resume.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "education")
@EqualsAndHashCode(callSuper = true)
public class Education extends ResumeOwnerID {
    @ManyToOne(optional = false)
    @JoinColumn(name="resume_id", referencedColumnName = "id")
    @JsonIgnore
    private Resume resume;

    @Column private String school;
    @Column private String degree ;
    @Column private String graduationDate ;
    @Column private String city ;
    @Column private String description ;

    @Override
    public String toString() {
        return super.toString();
    }
}

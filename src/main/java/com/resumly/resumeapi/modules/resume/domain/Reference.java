package com.resumly.resumeapi.modules.resume.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "referencesx")
@EqualsAndHashCode(callSuper = true)
public class Reference extends ResumeOwnerID {

    @ManyToOne(optional = false)
    @JoinColumn(name="resume_id", referencedColumnName = "id")
    @JsonIgnore
    private Resume resume;

    @Column private String fullName;
    @Column private String company;
    @Column private String phoneNumber;
    @Column private String email;

    @Override
    public String toString() {
        return super.toString();
    }
}

package com.resumly.resumeapi.modules.resume.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "publications")
@EqualsAndHashCode(callSuper = true)
public class Publication extends ResumeOwnerID {

    @ManyToOne(optional = false)
    @JoinColumn(name="resume_id", referencedColumnName = "id")
    @JsonIgnore
    private Resume resume;

    @Column private String titleOfArticle;
    @Column private String titleOfPublication;
    @Column private String publicationDate;

    @Override
    public String toString() {
        return super.toString();
    }
}

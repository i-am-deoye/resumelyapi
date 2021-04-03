package com.resumly.resumeapi.modules.resume.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity(name = "summaries")
@EqualsAndHashCode(callSuper = false)
public class Summary extends ResumeOwnerID {

    @OneToOne(mappedBy = "summary")
    @JsonIgnore
    private Resume resume;

    @Column String text;

    @Override
    public String toString() {
        return super.toString();
    }
}

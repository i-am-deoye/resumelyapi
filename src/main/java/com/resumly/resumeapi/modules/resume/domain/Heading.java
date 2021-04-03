package com.resumly.resumeapi.modules.resume.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity(name = "headings")
@EqualsAndHashCode(callSuper = true)
public class Heading extends ResumeOwnerID {

    @OneToOne(mappedBy = "heading")
    @JsonIgnore
    private Resume resume;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String postalCode;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private String seekingRole;

    @Column
    private String linkedInUrl;

    @Column
    private String githubUrl;

    @Override
    public String toString() {
        return super.toString();
    }
}

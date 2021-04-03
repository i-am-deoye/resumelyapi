package com.resumly.resumeapi.modules.resume.domain;

import com.resumly.resumeapi.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Data
@Entity(name = "resumes")
@EqualsAndHashCode(callSuper = true)
public class Resume extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "heading_id", referencedColumnName = "id")
    private Heading heading;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "summary_id", referencedColumnName = "id")
    private Summary summary;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;


    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Language> languages;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reference> references;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Intership> internships;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)//
    private List<Hobby> hobbies;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)//
    private List<Custom> customs;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)//
    private List<DriverLicense> driverLicenses;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)//
    private List<ExtraCurricular> extraCurriculars;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)//
    private List<Publication> publications;

    @OneToMany(mappedBy="resume", cascade = CascadeType.ALL, orphanRemoval = true)//
    private List<PersonalDetails> personalDetails;


    public void addExperience(List<Experience> experiences) {
        if (Objects.isNull(this.experiences)) this.experiences = new ArrayList<>();;
        experiences.forEach(experience -> experience.setResume(this));
        this.experiences.addAll(experiences);
    }


    public void addEducations(List<Education> educations) {
        if (Objects.isNull(this.educations)) this.educations = new ArrayList<>();;
        educations.forEach(education -> education.setResume(this));
        this.educations.addAll(educations);
    }

    public void addSkills(List<Skill> skills) {
        if (Objects.isNull(this.skills)) this.skills = new ArrayList<>();;
        skills.forEach(skill -> skill.setResume(this));
        this.skills.addAll(skills);
    }


    public void addLanguages(List<Language> languages) {
        if (Objects.isNull(this.languages)) this.languages = new ArrayList<>();;
        languages.forEach(language -> language.setResume(this));
        this.languages.addAll(languages);
    }

    public void addCourses(List<Course> courses) {
        if (Objects.isNull(this.courses)) this.courses = new ArrayList<>();;
        courses.forEach(course -> course.setResume(this));
        this.courses.addAll(courses);
    }

    public void addReferences(List<Reference> references) {
        if (Objects.isNull(this.references)) this.references = new ArrayList<>();;
        references.forEach(reference -> reference.setResume(this));
        this.references.addAll(references);
    }

    public void addInterships(List<Intership> interships) {
        if (Objects.isNull(this.internships)) this.internships = new ArrayList<>();;
        interships.forEach(intership -> intership.setResume(this));
        this.internships.addAll(interships);
    }


    public void addHobbies(List<Hobby> hobbies) {
        if (Objects.isNull(this.hobbies)) this.hobbies = new ArrayList<>();;
        hobbies.forEach(hobby -> hobby.setResume(this));
        this.hobbies.addAll(hobbies);
    }

    public void addCustoms(List<Custom> customs) {
        if (Objects.isNull(this.customs)) this.customs = new ArrayList<>();;
        customs.forEach(custom -> custom.setResume(this));
        this.customs.addAll(customs);
    }

    public void addDriverLicenses(List<DriverLicense> driverLicenses) {
        if (Objects.isNull(this.driverLicenses)) this.driverLicenses = new ArrayList<>();;
        driverLicenses.forEach(driverLicense -> driverLicense.setResume(this));
        this.driverLicenses.addAll(driverLicenses);
    }

    public void addExtraCurriculars(List<ExtraCurricular> extraCurriculars) {
        if (Objects.isNull(this.extraCurriculars)) this.extraCurriculars = new ArrayList<>();;
        extraCurriculars.forEach(extraCurricular -> extraCurricular.setResume(this));
        this.extraCurriculars.addAll(extraCurriculars);
    }

    public void addPublications(List<Publication> publications) {
        if (Objects.isNull(this.publications)) this.publications = new ArrayList<>();;
        publications.forEach(publication -> publication.setResume(this));
        this.publications.addAll(publications);
    }

    public void addPersonalDetails(List<PersonalDetails> personalDetails) {
        if (Objects.isNull(this.personalDetails)) this.personalDetails = new ArrayList<>();;
        personalDetails.forEach(personalDetail -> personalDetail.setResume(this));
        this.personalDetails.addAll(personalDetails);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

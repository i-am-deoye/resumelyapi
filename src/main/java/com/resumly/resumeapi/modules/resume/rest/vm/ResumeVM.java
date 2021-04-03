package com.resumly.resumeapi.modules.resume.rest.vm;

import com.resumly.resumeapi.modules.resume.domain.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class ResumeVM implements Serializable {
    private Long createdBy;
    private Resume resume;
    private Heading heading;
    private Summary summary;
    private List<Experience> experiences = new ArrayList<>();
    private List<Education> educations = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();
    private List<Language> languages = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Reference> references = new ArrayList<>();
    private List<Intership> internships = new ArrayList<>();
    private List<Hobby> hobbies = new ArrayList<>();
    private List<Custom> customs = new ArrayList<>();
    private List<DriverLicense> driverLicenses = new ArrayList<>();
    private List<ExtraCurricular> extraCurriculars = new ArrayList<>();
    private List<Publication> publications = new ArrayList<>();
    private List<PersonalDetails> personalDetails = new ArrayList<>();

    public static ResumeVM to(Resume resume) {
        ResumeVM resumeVM = new ResumeVM();

        resumeVM.setResume(resume);
        resumeVM.setHeading(resume.getHeading());
        resumeVM.setSummary(resume.getSummary());
        resumeVM.setExperiences(resume.getExperiences());
        resumeVM.setEducations(resume.getEducations());
        resumeVM.setSkills(resume.getSkills());
        resumeVM.setLanguages(resume.getLanguages());
        resumeVM.setCourses(resume.getCourses());
        resumeVM.setReferences(resume.getReferences());
        resumeVM.setInternships(resume.getInternships());
        resumeVM.setHobbies(resume.getHobbies());
        resumeVM.setCustoms(resume.getCustoms());
        resumeVM.setDriverLicenses(resume.getDriverLicenses());
        resumeVM.setExtraCurriculars(resume.getExtraCurriculars());
        resumeVM.setPublications(resume.getPublications());
        resumeVM.setPersonalDetails(resume.getPersonalDetails());

        return resumeVM;
    }
}

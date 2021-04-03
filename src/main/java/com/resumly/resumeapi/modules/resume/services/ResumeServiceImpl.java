package com.resumly.resumeapi.modules.resume.services;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.resumly.resumeapi.modules.core.enums.EntityFlag;
import com.resumly.resumeapi.modules.core.exceptions.CustomException;
import com.resumly.resumeapi.modules.core.services.BaseService;
import com.resumly.resumeapi.modules.resume.domain.Resume;
import com.resumly.resumeapi.modules.resume.repository.*;
import com.resumly.resumeapi.modules.resume.rest.vm.ResumeVM;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl extends BaseService implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final TemplateEngine templateEngine;
    private final HeadingRepository headingRepository;
    private final SummaryRepository summaryRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final LanguageRepository languageRepository;
    private final CourseRepository courseRepository;
    private final ReferenceRepository referenceRepository;
    private final IntershipRepository intershipRepository;
    private final HobbyRepository hobbyRepository;
    private final CustomRepository customRepository;
    private final DriverLicenseRepository driverLicenseRepository;
    private final ExtraCurricularRepository extraCurricularRepository;
    private final PublicationRepository publicationRepository;
    private final PersonalDetailsRepository personalDetailsRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository, TemplateEngine templateEngine, HeadingRepository headingRepository, SummaryRepository summaryRepository, ExperienceRepository experienceRepository, EducationRepository educationRepository, SkillRepository skillRepository, LanguageRepository languageRepository, CourseRepository courseRepository, ReferenceRepository referenceRepository, IntershipRepository intershipRepository, HobbyRepository hobbyRepository, CustomRepository customRepository, DriverLicenseRepository driverLicenseRepository, ExtraCurricularRepository extraCurricularRepository, PublicationRepository publicationRepository, PersonalDetailsRepository personalDetailsRepository) {
        this.resumeRepository = resumeRepository;
        this.templateEngine = templateEngine;
        this.headingRepository = headingRepository;
        this.summaryRepository = summaryRepository;
        this.experienceRepository = experienceRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.languageRepository = languageRepository;
        this.courseRepository = courseRepository;
        this.referenceRepository = referenceRepository;
        this.intershipRepository = intershipRepository;
        this.hobbyRepository = hobbyRepository;
        this.customRepository = customRepository;
        this.driverLicenseRepository = driverLicenseRepository;
        this.extraCurricularRepository = extraCurricularRepository;
        this.publicationRepository = publicationRepository;
        this.personalDetailsRepository = personalDetailsRepository;
    }

    @Override
    public void deleteResumeBy(Long resumeId) throws CustomException {
        Optional<Resume> resumeOptional = resumeRepository.findById(resumeId);
        validateOptionalEntity(resumeOptional);
        Resume resume = resumeOptional.get();
        resume.setEntityFlag(EntityFlag.DELETE);
        resumeRepository.save(resume);
    }

    @Override
    public Resume saveResume(ResumeVM resumeVM) throws CustomException {
        validateSaveEntity(resumeVM.getResume());
        validateSaveEntity(resumeVM.getHeading());
        validateSaveEntity(resumeVM.getSummary());

        Resume resume = resumeVM.getResume();
        resume.setCreatedBy(resumeVM.getCreatedBy());
        resume.setEntityFlag(EntityFlag.ACTIVE);

        resume.setHeading(resumeVM.getHeading());
        resume.setSummary(resumeVM.getSummary());
        resume.addExperience(resumeVM.getExperiences());
        resume.addEducations(resumeVM.getEducations());
        resume.addSkills(resumeVM.getSkills());
        resume.addLanguages(resumeVM.getLanguages());
        resume.addCourses(resumeVM.getCourses());
        resume.addReferences(resumeVM.getReferences());
        resume.addInterships(resumeVM.getInternships());
        resume.addHobbies(resumeVM.getHobbies());
        resume.addCustoms(resumeVM.getCustoms());
        resume.addDriverLicenses(resumeVM.getDriverLicenses());
        resume.addExtraCurriculars(resumeVM.getExtraCurriculars());
        resume.addPublications(resumeVM.getPublications());
        resume.addPersonalDetails(resumeVM.getPersonalDetails());

        return resumeRepository.save(resume);
    }

    @Override
    public String bindToTemplate(String dataKey, Object data, String templatePath) throws Exception {
        final Context context = new Context();
        context.setVariable(dataKey, data);
        return templateEngine.process(templatePath, context);
    }


    @Override
    public InputStream getPDF(Long resumeId, String template) throws Exception {

        String defaultBaseDir = System.getProperty("java.io.tmpdir");
        String outputFile = defaultBaseDir + UUID.randomUUID().toString() + resumeId;

        Optional<Resume> resumeOptional = resumeRepository.findById(resumeId);
        validateOptionalEntity(resumeOptional);

        try (OutputStream os = new FileOutputStream(outputFile)) {
            String bindedTemplate = bindToTemplate("resume", resumeOptional.get(), template);

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(bindedTemplate, "file:");
            builder.toStream(os);
            builder.run();

            File file = new File(outputFile);
            InputStream inputStream = Files.newInputStream(Path.of(file.getPath()), StandardOpenOption.DELETE_ON_CLOSE);
            return inputStream;
        }
    }

    @Override
    public Resume getResumeBy(Long resumeId) throws CustomException {
        Optional<Resume> resumeOptional = resumeRepository.findById(resumeId);
        validateOptionalEntity(resumeOptional);
        return resumeOptional.get();
    }

    @Override
    public List<ResumeVM> getResumes(Long createdBy) throws CustomException {
        List<Resume> resumes = resumeRepository.findResumesByCreatedByAndEntityFlag(createdBy, EntityFlag.ACTIVE);
        validateCollectionEntities(resumes);
        return resumes.stream().map(ResumeVM::to).collect(Collectors.toList());
    }
}

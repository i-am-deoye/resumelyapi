package com.resumly.resumeapi.modules.resume.services;

import com.resumly.resumeapi.modules.core.exceptions.CustomException;
import com.resumly.resumeapi.modules.resume.domain.Resume;
import com.resumly.resumeapi.modules.resume.rest.vm.ResumeVM;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

public interface ResumeService {

    void deleteResumeBy(Long resumeId) throws CustomException;
    Resume saveResume(ResumeVM resume) throws CustomException;
    String bindToTemplate(String dataKey, Object data, String templatePath) throws Exception;
    InputStream getPDF(Long resumeId, String template) throws Exception;
    Resume getResumeBy(Long resumeId) throws CustomException;
    List<ResumeVM> getResumes(Long createdBy) throws CustomException;
}

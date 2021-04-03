package com.resumly.resumeapi.modules.resume.rest;

import com.resumly.resumeapi.core.domain.DataResponse;
import com.resumly.resumeapi.core.domain.IDataResponse;
import com.resumly.resumeapi.core.domain.Message;
import com.resumly.resumeapi.core.security.TokenProvider;
import com.resumly.resumeapi.modules.core.exceptions.CustomException;
import com.resumly.resumeapi.modules.resume.domain.Resume;
import com.resumly.resumeapi.modules.resume.rest.vm.ResumeVM;
import com.resumly.resumeapi.modules.resume.services.ResumeService;
import com.resumly.resumeapi.modules.resume.services.ResumeServiceImpl;
import com.resumly.resumeapi.modules.user.domain.User;
import org.apache.pdfbox.io.IOUtils;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/resume")
public class ResumeResource {
    private final ResumeService resumeService;
    private final TokenProvider tokenProvider;

    public ResumeResource(ResumeServiceImpl resumeService, TokenProvider tokenProvider) {
        this.resumeService = resumeService;
        this.tokenProvider = tokenProvider;
    }


    @DeleteMapping("/{resumeId}")
    @ResponseBody
    DataResponse<?> delete(@PathVariable(value = "resumeId") Long resumeId) {
        DataResponse<?> response = new IDataResponse<>();
        try {
            resumeService.deleteResumeBy(resumeId);
            response.setValid(true);
            response.addMessage("", Message.Severity.SUCCESS);
        } catch (CustomException e) {
            e.printStackTrace();
            response.addMessage("Data not found", Message.Severity.ERROR);
        }

        return response;
    }



    @GetMapping
    @ResponseBody
    DataResponse<ResumeVM> getAll(@RequestHeader("Authorization") String authHeader) {
        DataResponse<ResumeVM> response = new IDataResponse<>();
        try {
            User user = tokenProvider.parseToken(authHeader);
            List<ResumeVM> resumes = resumeService.getResumes(user.getId());
            response.setValid(true);
            response.setData(resumes);
            response.addMessage("", Message.Severity.SUCCESS);
        } catch (CustomException e) {
            e.printStackTrace();
            response.addMessage("Data not found", Message.Severity.ERROR);
        }

        return response;
    }

    @PostMapping
    @ResponseBody
    DataResponse<ResumeVM> create(@RequestBody ResumeVM resumePayload) {
        DataResponse<ResumeVM> response = new IDataResponse<>();
        try {
            Resume resume = resumeService.saveResume(resumePayload);
            response.setValid(true);
            response.addData(ResumeVM.to(resume));
            response.addMessage("Resume created successfully", Message.Severity.SUCCESS);
        } catch (CustomException e) {
            e.printStackTrace();
            response.addMessage("Unable to create Resume", Message.Severity.ERROR);
        }

        return response;
    }


    @GetMapping(value = "/template/all")
    public DataResponse<String> getAllTemplates() {
        DataResponse<String> response = new IDataResponse<>();
        List<String> resumes = Arrays.asList("AKOKA", "IKEJA");
        response.setValid(true);
        response.setData(resumes);
        response.addMessage("", Message.Severity.SUCCESS);
        return response;
    }
}

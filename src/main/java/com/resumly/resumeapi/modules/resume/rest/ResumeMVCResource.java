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

@Controller
@RequestMapping("/api/resume/mvc")
public class ResumeMVCResource {
    private final String errorTemplate = "ErrorTemplate";

    private final ResumeService resumeService;

    public ResumeMVCResource(ResumeServiceImpl resumeService) {
        this.resumeService = resumeService;
    }






    @GetMapping("/view/allowed/{resumeId}/{templateName}")
    public String viewResumeBy(@PathVariable(value = "templateName") String templateName,
                                  @PathVariable("resumeId") Long resumeId,
                                  Model model, HttpServletResponse response) {

        response.addHeader("Content-type", "application/javascript");
        String htmlTemplate = "";
        try {
            Resume resume = resumeService.getResumeBy(resumeId);
            model.addAttribute("resume", resume);
            htmlTemplate = templateName;
        } catch (CustomException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            htmlTemplate = errorTemplate;
        }

        return htmlTemplate;
    }

    @GetMapping(value = "/download/allowed/{resumeId}/{templateName}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> downloadResumeBy (@PathVariable(value = "templateName") String templateName,
                                                                 @PathVariable("resumeId") Long resumeId,
                                               HttpServletResponse response) throws Exception{

        InputStream inputStream = resumeService.getPDF(resumeId, templateName);
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

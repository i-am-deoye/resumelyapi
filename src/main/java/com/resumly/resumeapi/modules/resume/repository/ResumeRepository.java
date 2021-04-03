package com.resumly.resumeapi.modules.resume.repository;

import com.resumly.resumeapi.modules.core.enums.EntityFlag;
import com.resumly.resumeapi.modules.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findResumesByCreatedByAndEntityFlag(Long createdBy, EntityFlag entityFlag);
}

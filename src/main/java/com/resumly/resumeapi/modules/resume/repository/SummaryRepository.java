package com.resumly.resumeapi.modules.resume.repository;

import com.resumly.resumeapi.modules.resume.domain.Resume;
import com.resumly.resumeapi.modules.resume.domain.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {
}

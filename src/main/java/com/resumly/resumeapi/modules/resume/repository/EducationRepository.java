package com.resumly.resumeapi.modules.resume.repository;

import com.resumly.resumeapi.modules.resume.domain.Education;
import com.resumly.resumeapi.modules.resume.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}

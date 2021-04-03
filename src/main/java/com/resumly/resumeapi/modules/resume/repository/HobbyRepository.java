package com.resumly.resumeapi.modules.resume.repository;

import com.resumly.resumeapi.modules.resume.domain.Course;
import com.resumly.resumeapi.modules.resume.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}

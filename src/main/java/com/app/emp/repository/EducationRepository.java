package com.app.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.emp.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

}

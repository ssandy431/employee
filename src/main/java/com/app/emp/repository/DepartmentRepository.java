package com.app.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.emp.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}

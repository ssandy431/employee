package com.app.emp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.emp.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	@Query(value = "SELECT * FROM Departments d where d.department_name like %:department_name%",nativeQuery = true)
	List<Department> findByDepartmentName(@Param("department_name") String department_name);
}

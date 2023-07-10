package com.app.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.emp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(value = "SELECT * FROM employees e where e.emp_id=:employeeId",nativeQuery = true)
	Employee findByEmployeeId(@Param("employeeId") String employeeId);

}

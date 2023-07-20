package com.app.emp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.emp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(value = "SELECT * FROM employees e where e.emp_id=:employeeId",nativeQuery = true)
	Optional<Employee> findByEmployeeId(@Param("employeeId") String employeeId);
	
	@Query(value="SELECT * FROM employees e where fisrt_name like %:name% or last_name like %:name%",nativeQuery = true)
	List<Employee> findByEmployeeName(@Param("name") String name);

}

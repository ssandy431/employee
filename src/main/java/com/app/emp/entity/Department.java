package com.app.emp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;
	@Column(name = "department_name", nullable = false,unique = false)
	private String departmentName;
//	@OneToMany(fetch = FetchType.EAGER,mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Employee> employees;

	public Department() {
	}

//	public Department(Long departmentId, String name, Set<Employee> employees) {
//		this.departmentId = departmentId;
//		this.employees = employees;
//	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

//	public Set<Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(Set<Employee> employees) {
//		this.employees = employees;
//	}
}

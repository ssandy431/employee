package com.app.emp.dto;

import java.util.List;

public class Department {
	private Long departmentId;
	private String departmentName;
	private List<Employee> employees;

	public Department() {
	}

	public Department(Long departmentId, String name, List<Employee> employees) {
		this.departmentId = departmentId;
		this.employees = employees;
	}

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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}

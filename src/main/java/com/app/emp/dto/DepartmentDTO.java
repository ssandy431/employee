package com.app.emp.dto;

public class DepartmentDTO {
	private Long departmentId;
	private String departmentName;

	public DepartmentDTO() {
	}

	public DepartmentDTO(Long departmentId, String name) {
		this.departmentId = departmentId;
		this.departmentName = name;
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
}

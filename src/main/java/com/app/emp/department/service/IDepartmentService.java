package com.app.emp.department.service;

import com.app.emp.dto.DepartmentDTO;
import com.app.emp.response.DataResponse;

public interface IDepartmentService {
	
	public DepartmentDTO createDepartment(DepartmentDTO department);
	
	public DataResponse getDepartments();
	
	public DataResponse getDepartmentByIdOrName(String departmentIdOrName);

}

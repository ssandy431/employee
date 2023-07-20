package com.app.emp.service;

import com.app.emp.dto.EmployeeDTO;
import com.app.emp.response.DataResponse;

public interface IEmployeeService {
	
	public EmployeeDTO createEmployee(EmployeeDTO employee);
	public DataResponse getAllEmployees(int pageNo, int pageSize,String sortBy,String sortDir);
	public DataResponse getEmployeeById(String employeeId);
	public EmployeeDTO updateEmployeeById(EmployeeDTO employee,String employeeId);

}

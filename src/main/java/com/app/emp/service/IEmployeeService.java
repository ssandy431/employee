package com.app.emp.service;

import com.app.emp.dto.Employee;
import com.app.emp.response.DataResponse;

public interface IEmployeeService {
	
	public Employee createEmployee(Employee employee);
	public DataResponse getAllEmployees(int pageNo, int pageSize,String sortBy,String sortDir);
	public Employee getEmployeeById(String employeeId);
	public Employee updateEmployeeById(Employee employee,String employeeId);

}

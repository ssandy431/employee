package com.app.emp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.emp.dto.Employee;
import com.app.emp.repository.EmployeeRepository;
import com.app.emp.response.DataResponse;
import com.app.emp.service.IEmployeeService;
import com.app.emp.util.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Employee createEmployee(Employee employee) {
		com.app.emp.entity.Employee employeeEnt= mapper.map(employee, com.app.emp.entity.Employee.class);
		com.app.emp.entity.Employee emp = repository.save(employeeEnt);
		return convertEntityToDTO(emp);
	}
	
	private Employee convertEntityToDTO(com.app.emp.entity.Employee emp)
	{
		return mapper.map(emp, Employee.class);
	}

	@Override
	public DataResponse getAllEmployees(int pageNo, int pageSize,String sortBy,String sortDir) {
		Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(sortDir) ? Sort.by(sortBy).ascending() :Sort.by(sortBy).descending();
		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		Page<com.app.emp.entity.Employee> employees =  repository.findAll(page);
		List<Employee> employeeList = employees.stream().map(employee->convertEntityToDTO(employee)).collect(Collectors.toList());
		DataResponse response = new DataResponse();
		response.setData(employeeList);
		response.setIsSuccess(true);
		response.setPageNo(pageNo);
		response.setPageSize(pageSize);
		response.setTotalElements(employees.getTotalElements());
		response.setTotalPageSize(employees.getTotalPages());
		response.setLast(employees.isLast());
		return response;
	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		com.app.emp.entity.Employee employee = repository.findByEmployeeId(employeeId);

		if(employee ==null){throw new ResourceNotFoundException("Employee", "Id", employeeId);}
		return convertEntityToDTO(employee);
	}


	@Override
	public Employee updateEmployeeById(Employee dto, String employeeId) {
		com.app.emp.entity.Employee emp = repository.findByEmployeeId(employeeId);
		if(emp ==null){throw new ResourceNotFoundException("Employee", "Id", employeeId);}
		
		emp.setMartialStatus(dto.getMartialStatus());
		emp.setBloodGroup(dto.getBloodGroup());
		emp.setAlternatePhoneNumber(dto.getAlternatePhoneNumber());
		emp.setEmail(dto.getEmail());
		com.app.emp.entity.Employee updatedEmp = repository.save(emp);
		
		return convertEntityToDTO(updatedEmp);
	}

}

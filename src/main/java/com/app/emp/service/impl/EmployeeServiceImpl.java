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

import com.app.emp.dto.EmployeeDTO;
import com.app.emp.entity.Employee;
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
	public EmployeeDTO createEmployee(EmployeeDTO employee) {
		Employee employeeEnt= mapper.map(employee, Employee.class);
		Employee emp = repository.save(employeeEnt);
		return convertEntityToDTO(emp);
	}
	
	private EmployeeDTO convertEntityToDTO(Employee emp)
	{
		return mapper.map(emp, EmployeeDTO.class);
	}

	@Override
	public DataResponse getAllEmployees(int pageNo, int pageSize,String sortBy,String sortDir) {
		Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(sortDir) ? Sort.by(sortBy).ascending() :Sort.by(sortBy).descending();
		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		Page<Employee> employees =  repository.findAll(page);
		List<EmployeeDTO> employeeList = employees.stream().map(employee->convertEntityToDTO(employee)).collect(Collectors.toList());
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
	public DataResponse getEmployeeById(String employeeIdOrName) {
		String regex = "^[a-zA-Z]+";
		DataResponse response = new DataResponse();
		if(!employeeIdOrName.matches(regex))
		{
			Employee employee = repository.findByEmployeeId(employeeIdOrName)
					.orElseThrow(()->new ResourceNotFoundException("Employee", "Id", employeeIdOrName));
			response.setData(convertEntityToDTO(employee));
		}
		else
		{
			List<Employee> employees = repository.findByEmployeeName(employeeIdOrName);
			response.setData(employees);
		}
		response.setIsSuccess(true);
		return response ;
	}


	@Override
	public EmployeeDTO updateEmployeeById(EmployeeDTO dto, String employeeId) {
		Employee emp = repository.findByEmployeeId(employeeId)
				.orElseThrow(()->new ResourceNotFoundException("Employee", "Id", employeeId));
		if(dto.getMartialStatus() != null && !dto.getMartialStatus().isBlank())
		{
			emp.setMartialStatus(dto.getMartialStatus());
		}
		if(dto.getBloodGroup() != null && !dto.getBloodGroup().isBlank())emp.setBloodGroup(dto.getBloodGroup());
		if(dto.getAlternatePhoneNumber() != null && !dto.getAlternatePhoneNumber().isBlank())
		{
			emp.setAlternatePhoneNumber(dto.getAlternatePhoneNumber());
		}
		if(dto.getEmail() != null && !dto.getEmail().isBlank())
		emp.setEmail(dto.getEmail());
		
		if(dto.getDesignation() != null && !dto.getDesignation().isBlank())
		emp.setDesignation(dto.getDesignation());
		
		Employee updatedEmp = repository.save(emp);
		
		return convertEntityToDTO(updatedEmp);
	}

}

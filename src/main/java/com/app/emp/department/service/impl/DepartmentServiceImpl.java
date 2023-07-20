package com.app.emp.department.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.emp.department.service.IDepartmentService;
import com.app.emp.dto.DepartmentDTO;
import com.app.emp.repository.DepartmentRepository;
import com.app.emp.response.DataResponse;
import com.app.emp.util.exception.ResourceNotFoundException;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public DepartmentDTO createDepartment(DepartmentDTO department) {
		com.app.emp.entity.Department createdDepartment = repository.save(mapper.map(department, com.app.emp.entity.Department.class));
		return mapper.map(createdDepartment, DepartmentDTO.class);
	}
	
	private DepartmentDTO convertEntityToDTO(com.app.emp.entity.Department department)
	{
		return mapper.map(department, DepartmentDTO.class);
	}

	@Override
	public DataResponse getDepartments() {
		List<com.app.emp.entity.Department> departments =repository.findAll();
		List<DepartmentDTO> listOfDepartments=  departments.stream().map(department->convertEntityToDTO(department)).collect(Collectors.toList());
		DataResponse response = new DataResponse();
		response.setIsSuccess(true);
		response.setData(listOfDepartments);
		return response;
	}

	@Override
	public DataResponse getDepartmentByIdOrName(String departmentIdOrName) {
		
		String regex = "^[0-9]";
		com.app.emp.entity.Department department=null;
		DataResponse response = new DataResponse();
		response.setIsSuccess(true);
		if(departmentIdOrName.matches(regex))
		{
			department = repository.findById(Long.parseLong(departmentIdOrName))
					.orElseThrow(()-> new ResourceNotFoundException("Department", "departmentId", departmentIdOrName));
			response.setData(department);
		}
		else
		{
			List<com.app.emp.entity.Department> departments =repository.findByDepartmentName(departmentIdOrName);
			response.setData(departments);
		}
		return response;
	}
	

}

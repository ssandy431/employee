package com.app.emp.department.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.emp.department.service.IDepartmentService;
import com.app.emp.dto.Department;
import com.app.emp.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public Department createDepartment(Department department) {
		com.app.emp.entity.Department createdDepartment = repository.save(mapper.map(department, com.app.emp.entity.Department.class));
		return mapper.map(createdDepartment, Department.class);
	}
	

}

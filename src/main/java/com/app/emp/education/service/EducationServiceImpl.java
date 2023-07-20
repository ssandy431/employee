package com.app.emp.education.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.emp.dto.EducationDTO;
import com.app.emp.entity.Education;
import com.app.emp.entity.Employee;
import com.app.emp.repository.EducationRepository;
import com.app.emp.repository.EmployeeRepository;
import com.app.emp.response.DataResponse;
import com.app.emp.util.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class EducationServiceImpl {
	
	
	@Autowired
	private EducationRepository repository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ModelMapper mapper;

	
	@Transactional
	public DataResponse addEducation(String employeeId,EducationDTO dto)
	{
		DataResponse response = new DataResponse();
		Education education = mapper.map(dto, Education.class);
		
		Employee employee = employeeRepository.findByEmployeeId(employeeId)
				.orElseThrow(()->new ResourceNotFoundException("Employee", "employeeId", employeeId));
		education.setEmployee(employee);
		Education savedEducation = repository.save(education);
		response.setIsSuccess(true);
		response.setData(mapper.map(savedEducation, EducationDTO.class));
		return response;
	}
}

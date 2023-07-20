package com.app.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.emp.dto.EducationDTO;
import com.app.emp.education.service.EducationServiceImpl;
import com.app.emp.response.DataResponse;

@RestController
@RequestMapping(value = "/education")
public class EducationController {
	
	@Autowired
	private EducationServiceImpl service;
	
	@PostMapping("/{employeeId}")
	public ResponseEntity<DataResponse> addEducation(@PathVariable String employeeId, @RequestBody EducationDTO dto)
	{
		return new ResponseEntity<DataResponse>(service.addEducation(employeeId, dto),HttpStatus.CREATED);
	}

}

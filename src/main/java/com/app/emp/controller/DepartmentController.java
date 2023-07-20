package com.app.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.emp.department.service.IDepartmentService;
import com.app.emp.dto.DepartmentDTO;
import com.app.emp.response.DataResponse;

@RestController
@RequestMapping("/rest/api/v1/department")
public class DepartmentController {
	
	@Autowired
	private IDepartmentService service;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/create",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO department)
	{
		return new ResponseEntity<>(service.createDepartment(department),HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<DataResponse> getDepartments()
	{
		return new ResponseEntity<DataResponse>(service.getDepartments(),HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{departmentIdOrName}")
	public ResponseEntity<DataResponse> getDepartmentById(@PathVariable String departmentIdOrName)
	{
		return new ResponseEntity<DataResponse>(service.getDepartmentByIdOrName(departmentIdOrName),HttpStatus.OK);
	}

}

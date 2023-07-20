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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.emp.dto.EmployeeDTO;
import com.app.emp.response.DataResponse;
import com.app.emp.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/home")
	public String getHomePage()
	{
		return "Home Page...";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value ="/create",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO dto)
	{
		EmployeeDTO emp = employeeService.createEmployee(dto);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<DataResponse> getAllEmployees(@RequestParam(defaultValue = "0",required = false) int pageNo,
			@RequestParam(defaultValue = "10",required = false) int pageSize,
			@RequestParam(defaultValue = "id",required = false) String sortBy,
			@RequestParam(defaultValue = "asc",required = false) String sortDir)
	{
		return new ResponseEntity<>(employeeService.getAllEmployees(pageNo, pageSize, sortBy, sortDir),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{empId}")
	public ResponseEntity<DataResponse> getEmployeeById(@PathVariable String empId)
	{
		return new ResponseEntity<DataResponse>(employeeService.getEmployeeById(empId),HttpStatus.OK);
	}
	
	@PostMapping(value = "/update/{empId}")
	public ResponseEntity<EmployeeDTO> updateEmployeeByEmpId(@RequestBody EmployeeDTO dto,@PathVariable String empId)
	{
		return new ResponseEntity<EmployeeDTO>(employeeService.updateEmployeeById(dto, empId),HttpStatus.OK);
	}
	
}

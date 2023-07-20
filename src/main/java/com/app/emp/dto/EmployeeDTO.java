package com.app.emp.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	
	private Long id;
	
	@NotBlank(message = "Please provide employee id.")
	private String employeeId;
	@NotBlank(message = "Employee firstName field is mandatory.")
    private String firstName;
	@NotBlank(message = "Employee lastName field is mandatory.")
    private String lastName;
	@NotNull(message = "Employee dateOfBirth  is mandatory.")
	@NotBlank(message = "Employee dateOfBirth field is mandatory.")
    private String dateOfBirth;
	@NotBlank(message = "Employee gender is manadatory")
    private String gender;
	@Email(message = "please provide valid email.")
	@NotBlank(message = "Employee email is manadatory")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Please enter valid Email Id.")
    private String email;
	@NotBlank(message = "Employee phoneNumber field is manadatory")
//	@Size(min = 10,max = 10,message = "Please provide valid phone number.")
	@Pattern(regexp = "^\\d{10}$", message = "Please enter valid phone number.")
    private String phoneNumber;
    private AddressDTO address;
    @NotNull(message = "Employee doj(date of joining) field is manadatory")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",message = "Please enter valid date in YYYY-MM-DD format only.")
    private String doj;
	private DepartmentDTO department;
	private Set<EducationDTO> education;
	@NotBlank(message = "jobTitle field is missing.")
    private String jobTitle;
	@NotNull(message = "salary is mandatory.")
	@NotBlank(message = "salary is mandatory.")
	@Pattern(regexp = "^\\d{5,6}$",message = "No decimal points are allowed.")
    private String salary;
	@NotBlank(message = "Designation is manadatory.")
	@NotNull(message = "Designation is manadatory.")
	private String designation;
    private String managerId;
    @NotNull(message = "Martial Status is manadatory.")
    @NotBlank(message = "Martial Status is manadatory.")
    private String martialStatus;
    @NotNull(message = "bloodGroup is manadatory.")
    @NotBlank(message = "bloodGroup is manadatory.")
    private String bloodGroup;
    @Pattern(regexp = "^[0-9]*", message = "Please enter valid alternate mobile number.")
    private String alternatePhoneNumber;
    @NotNull(message = "officialEmailId is manadatory.")
    @NotBlank(message = "officialEmailId is manadatory.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Please enter valid Email Id.")
    private String officialEmailId;
}

package com.app.emp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {
	private Long id;
    private String qualificationType;
    private String courseName;
    private String courseType;
    private String stream;
    private String collegeName;
    private String universityName;
    private Date startDate;
    private Date endDate;
    private EmployeeDTO employee;
}


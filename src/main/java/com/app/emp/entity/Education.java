package com.app.emp.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "qualification_type",nullable = false) 
    private String qualificationType;
	@Column(name = "course_name",nullable = false)
    private String courseName;
	@Column(name = "course_type",nullable = false)
    private String courseType;
	@Column(nullable = false)
	private String stream;
	@Column(name = "college_name",nullable = false)
	private String collegeName;
	@Column(name = "university_name",nullable = false)
	private String universityName;
	@Column(name = "course_start_date",nullable = false)
    private Date startDate;
	@Column(name = "course_end_date",nullable = false)
	private Date endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;
}


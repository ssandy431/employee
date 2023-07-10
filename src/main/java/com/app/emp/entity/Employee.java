package com.app.emp.entity;

import java.util.Date;

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
@Table(name="employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "EMP_ID",unique = true,nullable = false)
	private String employeeId;
	@Column(name = "fisrt_name",nullable = false)
    private String firstName;
	@Column(name = "last_name",nullable = false)
    private String lastName;
	@Column(name = "DOB",nullable = false)
    private Date dateOfBirth;
	@Column(nullable = false)
    private String gender;
	@Column(unique = true, nullable = false)
    private String email;
	@Column(name = "phone_number",unique = true,nullable = false)
    private String phoneNumber;
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    private Address address;
	@Column(name = "DOJ",nullable = false)
    private Date doj;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="department_id",nullable = false)
	private Department department;
//	@OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval = true)
//	private Set<Education> education;
    private String jobTitle;
    private double salary;
    private String designation;
    private String managerId;
    @Column(name = "martial_status",nullable = false)
    private String martialStatus;
    @Column(name = "blood_group",nullable = false)
    private String bloodGroup;
    @Column(name = "alternate_number")
    private String alternatePhoneNumber;
    @Column(name = "official_emailId",nullable = false)
    private String officialEmailId;
}

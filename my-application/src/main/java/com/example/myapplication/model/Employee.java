package com.hexaware.hrms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

	@Id
	private Integer employeeId
	private Integer employeeCode
	private String employeeName
	private String education
	private String address
	private String designation
	private String role
	private String department


}



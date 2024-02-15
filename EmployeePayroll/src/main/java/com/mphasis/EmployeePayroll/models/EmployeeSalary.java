package com.mphasis.EmployeePayroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/******************************EMPLOYEE SALARY*****************************************/
@Entity
public class EmployeeSalary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long sid;
	
	@Column
	private String month;
	
	@Column
	private Long salary;
	
/****************************************CONSTRUCTORS******************************************/	
public EmployeeSalary(String month, Long salary) {
		super();
		this.month = month;
		this.salary = salary;
	}

public EmployeeSalary() {
	super();
}

/************************************GETTERS AND SETTERS**************************************/
	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}
	
	
	
}

package com.mphasis.EmployeePayroll.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*************************************EMPLOYEE ATTENDANCE***************************************/
@Entity
public class EmployeeAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long aid;
	@Column
	private String date;
	
	@Column(nullable=false)
	private String status;
/*************************************GETTERS AND SETTERS****************************************/
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public void setStatus(String status) {
		this.status = status;
	}
/*****************************************CONSTRUCTORS****************************************/
	public EmployeeAttendance(String date, String status) {
		super();
		this.date = date;
		this.status = status;
	}

	public EmployeeAttendance() {
		super();
	}
	
}

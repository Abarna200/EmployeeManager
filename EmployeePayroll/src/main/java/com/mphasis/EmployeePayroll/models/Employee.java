package com.mphasis.EmployeePayroll.models;

import java.sql.Date;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/*******************************************EMPLOYEE*******************************************/
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long eid;
	@Column
	private String name;
	@Column
	private String DOB;
	@Column(unique=true)
	private String email;
	@Column
	private String designation;
	@Column
	private String address;
	@Column
	private String gender;
	@Column
	private String phone;
	@Column
	private String passwd;


	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="emp_empsalary", joinColumns= {@JoinColumn(name="eid")}, inverseJoinColumns= {@JoinColumn(name="sid")})
	private Set<EmployeeSalary>salaries=new HashSet<EmployeeSalary>();

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="emp_empschedule", joinColumns= {@JoinColumn(name="eid")}, inverseJoinColumns= {@JoinColumn(name="schdId")})
	private Set<EmployeeSchedule>schedules=new HashSet<EmployeeSchedule>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="emp_empattendance", joinColumns= {@JoinColumn(name="eid")}, inverseJoinColumns= {@JoinColumn(name="aid")})
	private Set<EmployeeAttendance>attendances=new HashSet<EmployeeAttendance>();
	
	
	/**************************************CONSTRUCTOR************************************/


	public Employee(String name, String dOB, String email, String designation, String address, String gender,
			String phone) {
		super();
		this.name = name;
		DOB = dOB;
		this.email = email;
		this.designation = designation;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
	}
	
	public Employee() {
		super();
	}

	/*********************************GETTERS AND SETTERS**************************************/
	


	public String getName() {
		return name;
	}


	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Set<EmployeeSalary> getSalaries() {
		return salaries;
	}

	public void setSalaries(Set<EmployeeSalary> salaries) {
		this.salaries = salaries;
	}
	public void addSalary(EmployeeSalary salary) {
		this.salaries.add(salary);
		}
	
	public void removeSalary(EmployeeSalary salary) {
		this.salaries.remove(salary);
	}
	
	public Set<EmployeeSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Set<EmployeeSchedule> schedules) {
		this.schedules = schedules;
	}
	public void addSchedule(EmployeeSchedule schedule) {
		this.schedules.add(schedule);
		}
	
	public void removeSchedule(EmployeeSchedule schedule) {
		this.schedules.remove(schedule);
	}

	public Set<EmployeeAttendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(Set<EmployeeAttendance> attendances) {
		this.attendances = attendances;
	}
    
	public void addAttendance(EmployeeAttendance attendance) {
		this.attendances.add(attendance);
		}
	
	public void removeAttendance(EmployeeAttendance attendance) {
		this.attendances.remove(attendance);
	}
	

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}



	
}
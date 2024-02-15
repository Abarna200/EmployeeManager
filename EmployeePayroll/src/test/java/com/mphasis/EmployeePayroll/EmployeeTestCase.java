package com.mphasis.EmployeePayroll;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;


import com.mphasis.EmployeePayroll.Repositories.*;
import com.mphasis.EmployeePayroll.models.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EmployeeTestCase {

	@Autowired
	EmployeeRepository employeerepository;
	ScheduleRepository schedulerepo;
	SalaryRepository salaryrepo;
	AttendanceRepository attendancerepo;

	@Test
	@Order(1)
	public void TestRegisterEmployee() {
		Employee a = new Employee();
		a.setName("Sowmiya");
		a.setDOB("2000-05-08");
		a.setEmail("sowmiya2001@gmail.com");
		a.setDesignation("Manager");
		a.setAddress("Vellore");
		a.setGender("Female");
		a.setPhone("1234567098");
		employeerepository.save(a);
		Assertions.assertThat(a.getEid()).isGreaterThan(0);

	}
	
	@Test
	@Order(2)
	public void testlogin() {
		Employee admin=employeerepository.findByEmail("sowmiya2001@gmail.com");
		 Assertions.assertThat(admin.getEmail()).isEqualTo("sowmiya2001@gmail.com");
	}
	
	@Test
	@Order(4)
	public void testUpdateprofile() {
		Employee employee = employeerepository.findById(1L).get();
        employee.setEmail("sow@gmail.com");
        Employee employeeUpdated =  employeerepository.save(employee);
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("sow@gmail.com");
	}
	 @Test 
	 @Order(5)
	  public void testSingleEmployeeAttendance() {
	 EmployeeAttendance  employeeSalary	= attendancerepo.findById(1l).get();
	  	 assertEquals("present", employeeSalary.getStatus());
	  }

	@Test 
	 @Order(6)
	  public void testSingleEmployeeSchedule() {
		 EmployeeSchedule  employeeschedule	= schedulerepo.findById(1L).get();
	  	 assertEquals("12hrs", employeeschedule.getTimeline());	  }
	 @Test 
	 @Order(7)
	  public void testSingleEmployeeSalary() {
	 EmployeeSalary  employeeSalary	= salaryrepo.findById(1l).get();
	  	 assertEquals(3000L, employeeSalary.getSalary());
	  }



}
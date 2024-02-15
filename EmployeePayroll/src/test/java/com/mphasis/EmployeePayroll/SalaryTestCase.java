package com.mphasis.EmployeePayroll;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.junit.jupiter.api.TestMethodOrder;
import com.mphasis.EmployeePayroll.Repositories.SalaryRepository;
import com.mphasis.EmployeePayroll.models.EmployeeSalary;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest

public class SalaryTestCase {
	@Autowired
	SalaryRepository salaryrepo;
	
	
	@Test
	@Order(1)
	  public void testCreate() {	
EmployeeSalary s =new EmployeeSalary();
		 s.setSalary(3000L);
		 s.setMonth("may");
		 s.setSid(8L);

		 salaryrepo.save(s);
		 assertNotNull(salaryrepo.findById(1l).get());}
	
	
	@Test
	@Order(2)
	 public void testReadAll() {
	 	  List < EmployeeSalary> list =salaryrepo.findAll();
	 	assertThat(list).size().isGreaterThan(0);
	 }
	
	 @Test 
	 @Order(3)
	  public void testSingleEmployeeSalary() {
	 EmployeeSalary  employeeSalary	= salaryrepo.findById(1l).get();
	  	 assertEquals(3000L, employeeSalary.getSalary());
	  }
	 @Test
	 @Order(4)
	  public void testUpdate() {
		  EmployeeSalary e=salaryrepo.findById(1l).get();
		  e.setSalary(5000L);
		  salaryrepo.save(e);
		  assertNotEquals(3000L,salaryrepo.findById(1l).get().getSalary());  
	  }
	
	
	 
	 
	 
}
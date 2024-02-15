package com.mphasis.EmployeePayroll;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.mphasis.EmployeePayroll.Repositories.ScheduleRepository;
import com.mphasis.EmployeePayroll.models.EmployeeSchedule;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest

class ScheduleTestCase {
	@Autowired
	ScheduleRepository schedulerepo;
	@Test
	@Order(1)
	  public void testCreate() {
		EmployeeSchedule e= new EmployeeSchedule();
		e.setSchdId(3L);
		e.setFromdate("2022-2-14");
		e.setTodate("2022-2-14");
		e.setTimeline("12hrs");
		schedulerepo.save(e);
		 assertNotNull(schedulerepo.findByTimeline("12hrs").get(0));
	
}
	@Test
	@Order(2)
	public void testReadAll() {
	 	  List <EmployeeSchedule> list =schedulerepo.findAll();
	 	assertThat(list).size().isGreaterThan(0);
	 }	
	 @Test 
	 @Order(3)
	  public void testSingleEmployeeSchedule() {
		 EmployeeSchedule  employeeschedule	= schedulerepo.findByTimeline("12hrs").get(0);
	  	 assertEquals("12hrs", employeeschedule.getTimeline());	  }


	 @Test
	 @Order(4)
	  public void testUpdate() {
		 EmployeeSchedule  e=schedulerepo.findByTimeline("18hrs").get(0);
		  e.setFromdate("2022-2-13");
		  schedulerepo.save(e);
		  assertNotEquals("2022-2-14",schedulerepo.findByTimeline("18hrs").get(0));
	  }
	
		  
	  
	 
	 
	 
}
package com.mphasis.EmployeePayroll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mphasis.EmployeePayroll.Repositories.*;
import com.mphasis.EmployeePayroll.models.*;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AttendanceTestCase {

	@Autowired
	AttendanceRepository aRepo;
		@Test
		@Order(1)
			public void testCreate ()
			{
			EmployeeAttendance a=new EmployeeAttendance();
		a.setDate("2022-10-20");
		a.setStatus("present");
		assertNotNull(aRepo.findById(2l).get());
			
			}
		@Test
		@Order(2)
		public void testRead () {
			EmployeeAttendance attendance = aRepo.findById(2L).get();
			assertEquals("present", attendance.getStatus());
		}
		@Test
		@Order(3)
		public void testReadAll () {
			List list = aRepo.findAll();
			assertThat(list).size().isGreaterThan(0);
		}
		
		@Test
		@Order(4)
		public void testUpdate () {
			EmployeeAttendance  a = aRepo.findById(2L).get();
			a.setDate("2022-10-20");
			aRepo.save(a);
			assertNotEquals(20/10/2022, aRepo.findById(2L).get().getDate());
		}

		
		@Test
		@Order(5)
		public void testDelete () {
			aRepo.deleteById(2L);
			assertThat(aRepo.existsById(2L)).isFalse();
		}
}


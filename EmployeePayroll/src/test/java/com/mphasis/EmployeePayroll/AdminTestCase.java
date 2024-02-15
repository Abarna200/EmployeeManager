package com.mphasis.EmployeePayroll;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Assertions;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.mphasis.EmployeePayroll.models.*;
import com.mphasis.EmployeePayroll.Repositories.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AdminTestCase {

	@Autowired
	AdminRepository adminrepository;

	@Test
	@Order(1)
	public void TestCreateAdmin() {
		Employee a = new Employee();
		a.setName("Savitha");
		a.setDOB("2001-08-12");
		a.setEmail("savithasini@gmail.com");
		a.setDesignation("Executive Officer");
		a.setAddress("Chennai");
		a.setGender("Female");
		a.setPhone("987654302");
		adminrepository.save(a);
		Assertions.assertThat(a.getEid()).isGreaterThan(0);

	}
	@Test
	@Order(2)
	public void testgetAllAdmin() {
		List<Employee> list = adminrepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	

	@Test
	@Order(3)
	public void testFindByEmail() {
		Employee admin=adminrepository.findByEmail("savithasini@gmail.com");
		 Assertions.assertThat(admin.getEmail()).isEqualTo("savithasini@gmail.com");
	}
	
	@Test
	@Order(4)
	public void testUpdateAdmin() {
		Employee employee = adminrepository.findById(2L).get();
        employee.setEmail("savitha@gmail.com");
        Employee employeeUpdated =  adminrepository.save(employee);
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("savitha@gmail.com");
	}
	
	@Test
	@Order()
	public void testDelete() {
		adminrepository.deleteById(1L);
		assertThat(adminrepository.existsById(1L)).isFalse();

	}
}

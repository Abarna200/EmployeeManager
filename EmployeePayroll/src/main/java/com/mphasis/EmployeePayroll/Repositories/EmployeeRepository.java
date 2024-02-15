package com.mphasis.EmployeePayroll.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mphasis.EmployeePayroll.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findByEmail(String email);
}

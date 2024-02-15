package com.mphasis.EmployeePayroll.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mphasis.EmployeePayroll.models.Employee;



public interface AdminRepository extends JpaRepository<Employee, Long> {
 List<Employee> findByName(String name);
 Employee findByEmail(String email);
}

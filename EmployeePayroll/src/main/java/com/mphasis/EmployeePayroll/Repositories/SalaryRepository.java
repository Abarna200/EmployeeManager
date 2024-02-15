package com.mphasis.EmployeePayroll.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mphasis.EmployeePayroll.models.EmployeeSalary;



public interface SalaryRepository extends JpaRepository<EmployeeSalary, Long> {

}

package com.mphasis.EmployeePayroll.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mphasis.EmployeePayroll.models.EmployeeAttendance;



public interface AttendanceRepository extends JpaRepository<EmployeeAttendance, Long>  {
List<EmployeeAttendance> findByStatus(String status);
}

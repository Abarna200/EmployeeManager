package com.mphasis.EmployeePayroll.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mphasis.EmployeePayroll.models.EmployeeSchedule;



public interface ScheduleRepository extends JpaRepository<EmployeeSchedule, Long> {
	List<EmployeeSchedule> findByTimeline(String timeline);
}

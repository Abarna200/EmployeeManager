package com.mphasis.EmployeePayroll.Services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.EmployeePayroll.Repositories.EmployeeRepository;
import com.mphasis.EmployeePayroll.models.Employee;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	EmployeeRepository emprepo;

	public Employee addEmployee(Employee employee) {
	    
	    return emprepo.save(employee);
	}
	public List<Employee> findAllEmployees() {
	    return emprepo.findAll();
	}

	public Employee updateEmployee(Employee employee) {
		Optional<Employee> prevemp=emprepo.findById(employee.getEid());
		Employee emp=prevemp.get();
		employee.setSalaries(emp.getSalaries());
		employee.setSchedules(emp.getSchedules());
		employee.setAttendances(emp.getAttendances());
	    return emprepo.save(employee);
	}
	
	public Employee findEmployeeByMail(String email) {
		return emprepo.findByEmail(email);
	}
}

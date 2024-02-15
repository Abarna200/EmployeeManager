package com.mphasis.EmployeePayroll.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.EmployeePayroll.Repositories.AdminRepository;
import com.mphasis.EmployeePayroll.models.Employee;


@Service
@Transactional
public class AdminService {
@Autowired
AdminRepository adminrepo;

public Employee addEmployee(Employee employee) {
    
    return adminrepo.save(employee);
}


public List<Employee> findAllEmployees() {
    return adminrepo.findAll();
}

public Employee updateEmployee(Employee employee) {
	Optional<Employee> prevemp=adminrepo.findById(employee.getEid());
	Employee emp=prevemp.get();
	employee.setSalaries(emp.getSalaries());
	employee.setSchedules(emp.getSchedules());
	employee.setAttendances(emp.getAttendances());
    return adminrepo.save(employee);
}

public Employee findEmployeeByMail(String email) {
	return adminrepo.findByEmail(email);
}

public List<Employee> findEmployeeByName(String name) {
	return adminrepo.findByName(name);
}

public void DeleteEmployeeById(Long id) {
	adminrepo.deleteById(id);
	
}



}

package com.mphasis.EmployeePayroll.Controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.EmployeePayroll.Exception.ItemNotFoundException;
import com.mphasis.EmployeePayroll.Repositories.EmployeeRepository;
import com.mphasis.EmployeePayroll.Services.AdminService;
import com.mphasis.EmployeePayroll.Services.EmployeeService;
import com.mphasis.EmployeePayroll.models.Employee;
import com.mphasis.EmployeePayroll.models.EmployeeAttendance;
import com.mphasis.EmployeePayroll.models.EmployeeSalary;
import com.mphasis.EmployeePayroll.models.EmployeeSchedule;

@RestController
@RequestMapping("/Employee")
public class Employee_operations {
	@Autowired
	EmployeeRepository emprepo;
	
	EmployeeService empserv;
	
	
	public Employee_operations(EmployeeService service) {
		super();
		this.empserv = service;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) {
		    Employee emp= empserv.addEmployee(employee);
	      
	        return new ResponseEntity<>(emp, HttpStatus.CREATED);
	    }
	
	@GetMapping("/login/{email}")
	public ResponseEntity<Employee> loginEmployee(@PathVariable("email") String email){
		Employee tempemp=empserv.findEmployeeByMail(email);
		if(tempemp==null) {
			 throw new ItemNotFoundException("Employee with mail" + email + " is not Found.Pls Give another mail!");}
		return new ResponseEntity<>(tempemp, HttpStatus.OK);
		
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
	    Employee emp= empserv.updateEmployee(employee);
      
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
	
	@GetMapping("/salary/{id}")//or email
	public ResponseEntity<Set<EmployeeSalary>> EmployeeSalaryById(@PathVariable("id") Long id){
		try {
		Optional<Employee> tempemployees= emprepo.findById(id);
		Employee employee=tempemployees.get();
		
		 return new ResponseEntity<>(employee.getSalaries(), HttpStatus.OK);}
		catch(Exception e){
            throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another id!");
        }
	}
	
	@GetMapping("/attendance/{id}")//or email
	public ResponseEntity<Set<EmployeeAttendance>> EmployeeAttendanceById(@PathVariable("id") Long id){
				try {
				Optional<Employee> tempemployees= emprepo.findById(id);
				Employee employee=tempemployees.get();
				
				 return new ResponseEntity<>(employee.getAttendances(), HttpStatus.OK);}
				catch(Exception e){
		            throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another id!");
		        }
	}
	
	@GetMapping("/schedule/{id}")
	public ResponseEntity<Set<EmployeeSchedule>> EmployeeScheduleById(@PathVariable("id") Long id){
		try {
		Optional<Employee> tempemployees= emprepo.findById(id);
		Employee employee=tempemployees.get();
		
		 return new ResponseEntity<>(employee.getSchedules(), HttpStatus.OK);}
		catch(Exception e){
            throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another id!");
        }
	}
		
	
	
}

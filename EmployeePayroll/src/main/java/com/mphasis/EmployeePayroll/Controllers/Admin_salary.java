package com.mphasis.EmployeePayroll.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.EmployeePayroll.Exception.ItemNotFoundException;
import com.mphasis.EmployeePayroll.Repositories.AdminRepository;
import com.mphasis.EmployeePayroll.Repositories.SalaryRepository;
import com.mphasis.EmployeePayroll.Services.AdminService;
import com.mphasis.EmployeePayroll.models.Employee;
import com.mphasis.EmployeePayroll.models.EmployeeSalary;
/***********************************CONTROLLER FOR EMPLOYEE SALARY******************************/
@RestController
@RequestMapping("/Admin_salary")
public class Admin_salary {
	
	@Autowired
	AdminRepository adminrepo;
    
	@Autowired
	SalaryRepository salaryrepo;
	AdminService adminserv;
	
	public Admin_salary(AdminService service) {
		super();
		this.adminserv = service;
	}
	
	//Get salaries of employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<Set<EmployeeSalary>> EmployeeById(@PathVariable("id") Long id){
		try {
		Optional<Employee> tempemployees= adminrepo.findById(id);
		Employee employee=tempemployees.get();
		
		 return new ResponseEntity<>(employee.getSalaries(), HttpStatus.OK);}
		 catch(Exception e){
			        throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another Id!");
			        }
	}
	//create salary for employee
	@PostMapping("/create/{empid}")
	public ResponseEntity<EmployeeSalary> addSalary(@RequestBody EmployeeSalary salary,
			@PathVariable("empid") Long id){
		try {
		Optional<Employee> tempemployees= adminrepo.findById(id);
		Employee employee=tempemployees.get();
		salaryrepo.save(salary);
		employee.addSalary(salary);
		adminrepo.save(employee);
		
		return new ResponseEntity<>(salary, HttpStatus.OK);}
		catch(Exception e){
            throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another Id!");
        }
		
	}

	//update employee salary
	@PutMapping("/update/{empid}")
	public ResponseEntity<EmployeeSalary> editSalary(@RequestBody EmployeeSalary salary,
			@PathVariable("empid") Long id){
		try{
		Optional<Employee> tempemployees= adminrepo.findById(id);
		Employee employee=tempemployees.get();
		salaryrepo.save(salary);
		//employee.addSalary(salary);
		adminrepo.save(employee);
		
		return new ResponseEntity<>(salary, HttpStatus.OK);}
		catch(Exception e){
            throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another Id!");
        }
		
	}
	//delete employee salary
	@DeleteMapping("/delete/{empid}/{sid}")
	public ResponseEntity<?> deleteSalary(//@RequestBody EmployeeSalary salary,
			@PathVariable("sid") Long sid,
			@PathVariable("empid") Long eid){
		try {
		Optional<Employee> tempemployees= adminrepo.findById(eid);
		Employee employee=tempemployees.get();
		Optional<EmployeeSalary> tempsalary= salaryrepo.findById(sid);
		EmployeeSalary salary=tempsalary.get();
		employee.removeSalary(salary);
		adminrepo.save(employee);
		salaryrepo.deleteById(sid);//
		//salaryrepo.delete(salary);
		adminrepo.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
            throw new ItemNotFoundException("Employee with id" + eid + " is not Found.Pls Give another Id!");
        }
	}
}

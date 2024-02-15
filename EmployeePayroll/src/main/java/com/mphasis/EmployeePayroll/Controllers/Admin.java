package com.mphasis.EmployeePayroll.Controllers;

import java.util.List;
import java.util.Optional;

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

import com.mphasis.EmployeePayroll.Repositories.AdminRepository;
import com.mphasis.EmployeePayroll.Services.AdminService;
import com.mphasis.EmployeePayroll.models.Employee;

import com.mphasis.EmployeePayroll.Exception.*;


/*******************THIS CONTROLLER DOES EMPLOYEE CRUD*************************************/

@RestController
@RequestMapping("/Admin")
public class Admin {
	@Autowired
	AdminRepository adminrepo;

	AdminService adminserv;
	
	public Admin(AdminService service) {
		super();
		this.adminserv = service;
	}
	//search all employees
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getallEmployees(){
		
		List<Employee> employees= adminserv.findAllEmployees();
		 return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	//search employee by id
		@GetMapping("/id/{id}")
		public ResponseEntity<Employee> EmployeeById(@PathVariable("id") Long eid){
			//List<Employee> employees= adminserv.findEmployeeByName(name);
			Optional<Employee> tempemp=adminrepo.findById(eid);
			Employee emp=tempemp.get();
			if(emp==null) {
				throw new ItemNotFoundException("Admin with mail" + eid + " is not Found.Pls Give another mail!");
			}
			
			 return new ResponseEntity<>(emp, HttpStatus.OK);
			
		}
	
	
	//search employee by name
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Employee>> EmployeeByName(@PathVariable("name") String name){
		List<Employee> employees= adminserv.findEmployeeByName(name);
		if(employees.isEmpty()) {
		throw new ItemNotFoundException("Admin with name" + name + " is not Found.Pls Give another name!");}
		 return new ResponseEntity<>(employees, HttpStatus.OK);
		
	}
	//search employee by mail
	@GetMapping("/mail/{mail}")
	public ResponseEntity<Employee> EmployeeByMail(@PathVariable("mail") String mail){
		Employee employee= adminserv.findEmployeeByMail(mail);
		if(employee==null) {
			throw new ItemNotFoundException("Admin with mail" + mail + " is not Found.Pls Give another mail!");
		}
		
		 return new ResponseEntity<>(employee, HttpStatus.OK);
	
	}
	//create employee
	@PostMapping("/add")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		    Employee emp= adminserv.addEmployee(employee);
	      
	        return new ResponseEntity<>(emp, HttpStatus.CREATED);
	    }
	//update employee
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		    Employee emp= adminserv.updateEmployee(employee);
	      
	        return new ResponseEntity<>(emp, HttpStatus.OK);
	    }
	//delete employee
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
		 try {
	        adminserv.DeleteEmployeeById(id);
	        return new ResponseEntity<>(HttpStatus.OK);}
		 catch(Exception e){
	            throw new ItemNotFoundException("No records found in Given id"+id);
	        }
		 
	    }
	
	
	
}

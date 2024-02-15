package com.mphasis.EmployeePayroll.Controllers;

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
import com.mphasis.EmployeePayroll.Repositories.ScheduleRepository;
import com.mphasis.EmployeePayroll.Services.AdminService;
import com.mphasis.EmployeePayroll.models.Employee;
import com.mphasis.EmployeePayroll.models.EmployeeSalary;
import com.mphasis.EmployeePayroll.models.EmployeeSchedule;

@RestController
@RequestMapping("/Admin_schedule")
public class Admin_schedule {
	@Autowired
	AdminRepository adminrepo;
	
	@Autowired
	ScheduleRepository schedulerepo;
	
    AdminService adminserv;
	
	public Admin_schedule(AdminService service) {
		super();
		this.adminserv = service;
	}
	
	//Get schedules of employee by ID
		@GetMapping("/{id}")
		public ResponseEntity<Set<EmployeeSchedule>> EmployeeById(@PathVariable("id") Long id){
			try {
			Optional<Employee> tempemployees= adminrepo.findById(id);
			Employee employee=tempemployees.get();
			
			 return new ResponseEntity<>(employee.getSchedules(), HttpStatus.OK);}
			catch(Exception e){
	            throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another Id!");
	        }
		}
		//create schedule for employee
		@PostMapping("/create/{empid}")
		public ResponseEntity<EmployeeSchedule> addSchedule(@RequestBody EmployeeSchedule schedule,
				@PathVariable("empid") Long id){
			try {
			Optional<Employee> tempemployees= adminrepo.findById(id);
			Employee employee=tempemployees.get();
			schedulerepo.save(schedule);
			employee.addSchedule(schedule);
			adminrepo.save(employee);
			
			return new ResponseEntity<>(schedule, HttpStatus.OK);}
			catch(Exception e){
	            throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another Id!");
	        }
			
		}

		//update employee schedule
		@PutMapping("/update/{empid}")
		public ResponseEntity<EmployeeSchedule> editSchedule(@RequestBody EmployeeSchedule schedule,
				@PathVariable("empid") Long id){
			try {
			Optional<Employee> tempemployees= adminrepo.findById(id);
			Employee employee=tempemployees.get();
			schedulerepo.save(schedule);
			//employee.addSchedule(schedule);
			adminrepo.save(employee);
			
			return new ResponseEntity<>(schedule, HttpStatus.OK);}
			catch(Exception e){
	            throw new ItemNotFoundException("Employee with id" + id + " is not Found.Pls Give another Id!");
	        }
			
		}
		//delete employee schedule
		@DeleteMapping("/delete/{empid}/{schid}")
		public ResponseEntity<?> deleteSchedule(//@RequestBody EmployeeSalary salary,
				@PathVariable("schid") Long schid,
				@PathVariable("empid") Long eid){
			try{Optional<Employee> tempemployees= adminrepo.findById(eid);
			Employee employee=tempemployees.get();
			Optional<EmployeeSchedule> tempschedule= schedulerepo.findById(schid);
			EmployeeSchedule schedule=tempschedule.get();
			employee.removeSchedule(schedule);
			
			adminrepo.save(employee);
			schedulerepo.deleteById(schid);//
			//salaryrepo.delete(salary);
			
			adminrepo.save(employee);
			return new ResponseEntity<>(HttpStatus.OK);}
			catch(Exception e){
	            throw new ItemNotFoundException("Employee with id" + eid + " is not Found.Pls Give another Id!");
	        }
			
		}
}

package com.app.manager_assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.manager_assignment.entity.Employee;
import com.app.manager_assignment.request.EmployeeResquest;
import com.app.manager_assignment.request.EmployeeUpdateRequest;
import com.app.manager_assignment.service.EmployeeService;

@RequestMapping("/api/v1")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add/employee")
	public Employee saveEmployee(@RequestBody EmployeeResquest employeeResquest) {
		return employeeService.saveEmployee(employeeResquest);
	}
	
	@PutMapping("/update/employee/{employeeId}")
	public Employee updateEmployee(@PathVariable(name = "employeeId") Long employeeId,@RequestBody EmployeeUpdateRequest employeeResquest) {
		return employeeService.updateEmployee(employeeId,employeeResquest);
	}
	
	@GetMapping("/get/employees/{userId}")
	public List<Employee> getAllEmployee(@PathVariable(name = "userId") Long userId) {
		return employeeService.getAllEmployee(userId);
	}
	
	@GetMapping("/get/employees/with/pagination/{userId}")
	public List<Employee> getEmployeesWithPagination(@PathVariable(name = "userId") Long userId,
													 @RequestParam Integer pageNo, 
													 @RequestParam Integer pageSize) {
		return employeeService.getEmployeesWithPagination(userId,pageNo,pageSize);
	}
	
	@GetMapping("/get/employee/{employeeId}")
	public Employee getEmployeeDetail(@PathVariable(name = "employeeId") Long employeeId) {
		return employeeService.getEmployee(employeeId);
	}
	
	@DeleteMapping("/delete/employee/{employeeId}")
	public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		 employeeService.deleteEmployee(employeeId);
	}
	
}

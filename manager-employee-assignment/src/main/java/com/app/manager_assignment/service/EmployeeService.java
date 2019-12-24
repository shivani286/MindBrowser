package com.app.manager_assignment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.manager_assignment.entity.Employee;
import com.app.manager_assignment.request.EmployeeResquest;
import com.app.manager_assignment.request.EmployeeUpdateRequest;

public interface EmployeeService  {

	Employee saveEmployee(EmployeeResquest employeeResquest);

	//List<Employee> getAllEmployee(Long managerId);

	Employee getEmployee(Long employeeId);

	void deleteEmployee(Long employeeId);

	Employee updateEmployee(Long employeeId, EmployeeUpdateRequest employeeResquest);

	List<Employee> getAllEmployee(Long userId);

	List<Employee> getEmployeesWithPagination(Long userId, Integer pageNo, Integer pageSize);

}

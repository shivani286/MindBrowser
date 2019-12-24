package com.app.manager_assignment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.manager_assignment.constants.EmployeeConstants;
import com.app.manager_assignment.dao.EmployeeDao;
import com.app.manager_assignment.dao.UserDao;
import com.app.manager_assignment.entity.Employee;
import com.app.manager_assignment.entity.User;
import com.app.manager_assignment.request.EmployeeResquest;
import com.app.manager_assignment.request.EmployeeUpdateRequest;
import com.app.manager_assignment.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private UserDao userDao;

	@Override
	public Employee saveEmployee(EmployeeResquest employeeResquest) {

		if (Objects.isNull(employeeResquest.getEmailId()) || employeeResquest.getEmailId().trim().isEmpty()) 
			throw new NullPointerException("Please enter your valid  email ID");

		if (Objects.isNull(employeeResquest.getUserId())) 
			throw new NullPointerException("Manager Id not be null");

			User employeeManger = userDao.findUserByEmailId(employeeResquest.getEmailId());
			
			if (Objects.nonNull(employeeManger)) 
					throw new IllegalArgumentException("you cannot add manager employee");
				
			Employee employee = employeeDao.findEmployeeByEmailIdAndUserId(employeeResquest.getEmailId(),employeeResquest.getUserId());
			
			if (Objects.nonNull(employee)) {
				if (employee.getEmailId().equals(employeeResquest.getEmailId())&& employee.getUserId().equals(employeeResquest.getUserId())) 
					throw new IllegalArgumentException("employee already exist");
			}
		

		Employee employeeCreate = new Employee();
		employeeCreate.setFirstName(employeeResquest.getFirstName());
		employeeCreate.setLastName(employeeResquest.getLastName());
		employeeCreate.setEmailId(employeeResquest.getEmailId());
		employeeCreate.setAddress(employeeResquest.getAddress());
		employeeCreate.setCity(employeeResquest.getCity());
		Date dob;
		try {
			dob = new SimpleDateFormat("yyyy-MM-dd").parse(employeeResquest.getDateOfBirth());
			employeeCreate.setDateOfBirth(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		employeeCreate.setPhoneNumber(employeeResquest.getPhoneNumber());
		employeeCreate.setUserId(employeeResquest.getUserId());
		return employeeDao.save(employeeCreate);
	}

	@Override
	public List<Employee> getAllEmployee(Long userId) {
		if(Objects.isNull(userId))
			throw new NullPointerException("ManagerId not be null");
		
		List<Employee> getList = employeeDao.findAllByUserId(userId);
		return getList;
	}

	@Override
	public Employee getEmployee(Long employeeId) {
		return employeeDao.findOne(employeeId);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		
		if(Objects.isNull(employeeId))
			throw new NullPointerException("EmployeeId not be null");
		
		employeeDao.delete(employeeId);
	}

	@Override
	public Employee updateEmployee(Long employeeId, EmployeeUpdateRequest employeeResquest) {
		if(Objects.isNull(employeeId))
			throw new NullPointerException("EmployeeID not be null");
		
		Employee updateEmployee = employeeDao.findOne(employeeId);
		updateEmployee.setFirstName(Objects.nonNull(employeeResquest.getFirstName()) ? employeeResquest.getFirstName() : updateEmployee.getFirstName());
		updateEmployee.setLastName(Objects.nonNull(employeeResquest.getLastName()) ? employeeResquest.getLastName() : updateEmployee.getLastName());
		updateEmployee.setEmailId(updateEmployee.getEmailId());
		updateEmployee.setAddress(Objects.nonNull(employeeResquest.getAddress()) ? employeeResquest.getAddress() : updateEmployee.getAddress());
		updateEmployee.setCity(Objects.nonNull(employeeResquest.getCity()) ? employeeResquest.getCity() : updateEmployee.getCity());
		Date dob;
		try {
			if(Objects.nonNull(employeeResquest.getDateOfBirth())) {
			dob = new SimpleDateFormat("yyyy-MM-dd").parse(employeeResquest.getDateOfBirth());
			updateEmployee.setDateOfBirth(dob);
			}else {
				updateEmployee.setDateOfBirth(updateEmployee.getDateOfBirth());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		updateEmployee.setPhoneNumber(Objects.nonNull(employeeResquest.getPhoneNumber()) ? employeeResquest.getPhoneNumber() : updateEmployee.getPhoneNumber());
		updateEmployee.setUserId(updateEmployee.getUserId());
		
		
		return employeeDao.saveAndFlush(updateEmployee);
	}

	@Override
	public List<Employee> getEmployeesWithPagination(Long userId, Integer pageNo, Integer pageSize) {
		
		Pageable paging = new PageRequest(pageNo, pageSize); 
		 
		Slice<Employee> slicedResult =employeeDao.findAllByUserId(userId, paging); 
		 
		List<Employee> employeeList = slicedResult.getContent();
		
		return employeeList;
	}

}

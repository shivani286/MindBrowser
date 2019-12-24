package com.app.manager_assignment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.manager_assignment.constants.EmployeeConstants;
import com.app.manager_assignment.controller.UserController;
import com.app.manager_assignment.dao.EmployeeDao;
import com.app.manager_assignment.dao.UserDao;
import com.app.manager_assignment.entity.Employee;
import com.app.manager_assignment.entity.User;
import com.app.manager_assignment.exceptionmapping.EntityAlreadyExistsException;
import com.app.manager_assignment.exceptionmapping.PermissionDeniedException;
import com.app.manager_assignment.request.EmployeeResquest;
import com.app.manager_assignment.request.LoginRequest;
import com.app.manager_assignment.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public User saveRegistration(EmployeeResquest employeeResquest) {
		
		if (Objects.isNull(employeeResquest.getEmailId()) || employeeResquest.getEmailId().isEmpty()) {
			throw new NullPointerException("Please enter your valid  email ID");
		}
		
		if (Objects.isNull(employeeResquest.getPassword())) {
			throw new NullPointerException("Please enter your password");
		}
		
		if (Objects.nonNull(Objects.nonNull(employeeResquest.getEmailId())
				? userDao.findUserByEmailId(employeeResquest.getEmailId())
				: null)) {
			throw new EntityAlreadyExistsException("Sorry, the email ID you have entered is already registered.");
		}
		
		if(Objects.nonNull(employeeResquest.getEmailId())) {
			List<Employee> list  = employeeDao.findEmployeeByEmailId(employeeResquest.getEmailId());
			if(Objects.nonNull(list) && !list.isEmpty()) {
				throw new PermissionDeniedException("Sorry,you haven't permission.");
			}
		}
		
		
		User createRegi = new User();
		createRegi.setFirstName(employeeResquest.getFirstName());
		createRegi.setLastName(employeeResquest.getLastName());
		createRegi.setEmailId(employeeResquest.getEmailId());
		String base64ResetPassword =Base64.getEncoder().encodeToString(employeeResquest.getPassword().getBytes());
		createRegi.setPassword(base64ResetPassword);
		
		createRegi.setAddress(employeeResquest.getAddress());
		createRegi.setCompany(employeeResquest.getCompany());
		Date dob;
		try {
			dob = new SimpleDateFormat("yyyy-MM-dd").parse(employeeResquest.getDateOfBirth());
			createRegi.setDateOfBirth(dob);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		User registor = userDao.save(createRegi);

		return registor;
	}

	@Override
	public User login(LoginRequest userCredentials) {
		
		if (Objects.isNull(userCredentials) || Objects.isNull(userCredentials.getUserName())
				|| Objects.isNull(userCredentials.getPassword())) {
			return null;
		}
		User user = userDao.findUserByEmailId(userCredentials.getUserName());
		
		String userCurrentPassword = new String(Base64.getDecoder().decode(user.getPassword()));

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("Wrong Login Credentials");
		}
		if(!userCurrentPassword.equals(userCredentials.getPassword())) {
			throw new IllegalArgumentException("Wrong Password");
		}
		
		return user;
	}

}

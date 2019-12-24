package com.app.manager_assignment.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.manager_assignment.entity.User;
import com.app.manager_assignment.request.EmployeeResquest;
import com.app.manager_assignment.request.LoginRequest;
import com.app.manager_assignment.service.UserService;

@RequestMapping("/api")
@RestController
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService registrationService;
	
	@PostMapping("/v1/registration")
	public User createRegistration(@RequestBody EmployeeResquest employeeResquest,HttpServletRequest request) {
			logger.info("......create NewRegistration....");
			return registrationService.saveRegistration(employeeResquest);
	}
	
	@PostMapping("/v1/login")
	public User Login(@RequestBody LoginRequest loginRequest,HttpServletRequest request) {
			logger.info("........Login........");
			return registrationService.login(loginRequest);
	}
}

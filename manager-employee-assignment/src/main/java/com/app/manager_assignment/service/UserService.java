package com.app.manager_assignment.service;

import com.app.manager_assignment.entity.User;
import com.app.manager_assignment.request.EmployeeResquest;
import com.app.manager_assignment.request.LoginRequest;

public interface UserService {

	User saveRegistration(EmployeeResquest employeeResquest);

	User login(LoginRequest loginRequest);

}

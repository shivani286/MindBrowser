package com.app.manager_assignment.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResquest {
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String phoneNumber;
    private String dateOfBirth;
	private String address;
    private String company;
    private String city;
    private Long userId;
}

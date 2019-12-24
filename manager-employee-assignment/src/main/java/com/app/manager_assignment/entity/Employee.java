package com.app.manager_assignment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 8986435970383517614L;

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;

	@NotNull
	@Size(max = 65)
	@Column(name = "first_name")
	private String firstName;

	@Size(max = 65)
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull
	@Size(max = 100)
	@Column(name = "email_id")
	private String emailId;

	@Column(name = "phone_number")
	@Size(max = 15)
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;
	
	@Size(max = 100)
	@Column(name = "address")
	private String address;
	
	@Size(max = 100)
    private String city;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "user_id")
	private Long userId;
	
	 	

}

package com.app.manager_assignment.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.manager_assignment.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long>{

	List<Employee> findEmployeeByEmailId(String emailId);

	Employee findEmployeeByEmailIdAndRole(String emailId, String managerEmployeeRole);

	Employee findEmployeeByEmailIdAndUserId(String emailId, Long userId);

	List<Employee> findAllByUserId(Long userId);

	Slice<Employee> findAllByUserId(Long userId, Pageable paging);

}

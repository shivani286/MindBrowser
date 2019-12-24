package com.app.manager_assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.manager_assignment.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	public User findUserByEmailId(String emailId);

}

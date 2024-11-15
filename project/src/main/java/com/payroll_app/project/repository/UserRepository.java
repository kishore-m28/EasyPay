package com.payroll_app.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payroll_app.project.model.User;

 
@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.username=?1")
	Optional<User> findByUsername(String username);


}

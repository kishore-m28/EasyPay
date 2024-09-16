package com.payroll_app.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payroll_app.project.model.User;
import com.payroll_app.project.repository.UserRepository;

 

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User addUser(User user) {
		//encrypt the password 
		String plainText = user.getPassword();
		//encode this  
		String encryptedPassword  = passwordEncoder.encode(plainText);
		//attach this to User 
		user.setPassword(encryptedPassword);
		
		return userRepository.save(user);
	}

}

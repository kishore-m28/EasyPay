package com.payroll_app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.JwtUtil;
import com.payroll_app.project.model.User;
import com.payroll_app.project.repository.UserRepository;
import com.payroll_app.project.service.MyUserDetailsService;

 
@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	 
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/auth/token")
    public String createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
 
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }
 
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        System.out.println(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
 
        return jwt;
    }
    
    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello, User!";
    }
 
 
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello, Admin!";
    }
    
    @PostMapping("/auth/signup")
    public void signup(@RequestBody User userInfo) {
    	userInfo.setRole("ROLE_JOBSEEKER");
    	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
    	userRepository.save(userInfo);
    }

}

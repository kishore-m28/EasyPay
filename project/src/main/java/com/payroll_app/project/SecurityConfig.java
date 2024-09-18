package com.payroll_app.project;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.payroll_app.project.service.MyUserDetailsService;
 


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService; 
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth -> auth
            		   .requestMatchers("/auth/signup/jobseeker").permitAll()
            		   .requestMatchers("/auth/signup/hr").permitAll()
                       .requestMatchers("/auth/token").permitAll()
                       .requestMatchers("/jobSeeker/add").permitAll()
                       .requestMatchers("/job/add").hasRole("HR")
                       .requestMatchers("/job/all").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/job/one/{jobId}").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/jobSeeker/job/apply/{jobId}").hasRole("JOBSEEKER")
                       .requestMatchers("/employee/add").hasRole("HR")    
                       .requestMatchers("/employee/salary").hasRole("EMPLOYEE")
                       .requestMatchers("/salary/compute/{eid}").hasRole("HR")
                       .requestMatchers("/salary/process/{eid}").hasRole("HR")
                       .requestMatchers("/jobSeeker/basic/details").hasRole("JOBSEEKER")
                       .requestMatchers("/jobSeeker/display/applied/jobs").hasRole("JOBSEEKER")
                       .requestMatchers("/jobSeeker/status/TechnicalInterview").hasRole("JOBSEEKER")
                       .requestMatchers("/jobSeeker/status/HrInterview").hasRole("JOBSEEKER")
                       .requestMatchers("/job/display/specific/details").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/job/display/specific/details/{jobId}").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/admin/hello").hasRole("HR")
                       .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                       
                       .requestMatchers("/manager/project").hasRole("MANAGER")
                       .requestMatchers("/manager/employee").hasRole("MANAGER")
                       .requestMatchers("/manager/employee/count").hasRole("MANAGER")
                       .requestMatchers("/project/{pid}").hasRole("MANAGER")
                       .requestMatchers("/project/employee/stat").hasRole("MANAGER")
                       .requestMatchers("/project/employee/{eid}").hasRole("MANAGER")
                       .requestMatchers("/leave/request/approval").hasRole("MANAGER")
                       .requestMatchers("/issue/track").hasRole("MANAGER")            
                       .requestMatchers("/tech-interview/schedule/{jid}/{mid}").hasRole("HR")
                       .requestMatchers("/tech-scoresheet/update/{jid}").hasRole("MANAGER")        
                       .requestMatchers("/hr-interview/schedule/{jid}").hasRole("HR")
                       .requestMatchers("/hr-scoresheet/update/{jid}").hasRole("HR")
                       .anyRequest().authenticated()
               )
               .sessionManagement(session -> session
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               );

       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
   }
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userDetailsService);
       authProvider.setPasswordEncoder(passwordEncoder);
       return authProvider;
   }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }

	
	@Bean
	PasswordEncoder getEncoder() {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder;
	}

}

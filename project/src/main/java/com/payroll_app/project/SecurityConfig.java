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
                       .requestMatchers("/job/search/location").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/admin/hello").hasRole("HR")
                       .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                       
                       .requestMatchers("/jobSeeker/add").permitAll()
                       .requestMatchers("/job/add").hasRole("HR")
                       .requestMatchers("/job/all").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/job/one/{jobId}").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/jobSeeker/job/apply/{jobId}").hasRole("JOBSEEKER")
                       
                       .requestMatchers("/employee/add").hasRole("HR")   
                       .requestMatchers("/employee/one/{eid}").hasAnyRole("EMPLOYEE", "MANAGER","HR")
                       .requestMatchers("/employee/update/{eid}").hasAnyRole("EMPLOYEE", "HR")
                       .requestMatchers("/employee/delete/{eid}").hasRole("HR")
                       .requestMatchers("/employee/salary").hasRole("EMPLOYEE")

                       .requestMatchers("/employee/add-account-details/{eid}").hasRole("HR")

                       .requestMatchers("/salary/compute/{eid}").hasRole("HR")
                       .requestMatchers("/salary/process/{eid}").hasRole("HR")
                       .requestMatchers("/jobSeeker/basic/details").hasRole("JOBSEEKER")
                       .requestMatchers("/jobSeeker/display/applied/jobs").hasRole("JOBSEEKER")
                       .requestMatchers("/jobSeeker/status/TechnicalInterview").hasRole("JOBSEEKER")
                       .requestMatchers("/jobSeeker/status/HrInterview").hasRole("JOBSEEKER")
                       .requestMatchers("/job/display/specific/details").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/job/display/specific/details/{jobId}").hasAnyRole("HR", "JOBSEEKER")
 
                       .requestMatchers("/job/search").hasAnyRole("HR", "JOBSEEKER")
                      
 

                       .requestMatchers("/admin/hello").hasRole("HR")
                       .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                       .requestMatchers("/employee/active-count").hasRole("HR")
                       .requestMatchers("/compliance-report/generate/{complianceId}").hasRole("HR")
                       .requestMatchers("/salary/set/{eid}").hasRole("HR")
 
                       
                       .requestMatchers("/manager/add").hasRole("HR")
                       .requestMatchers("/manager/project").hasRole("MANAGER")
                       .requestMatchers("/manager/employee").hasRole("MANAGER")
                       .requestMatchers("/manager/employee/count").hasRole("MANAGER")
                       .requestMatchers("/project/add/{managerId}").hasRole("HR")
                       .requestMatchers("/project/{pid}").hasAnyRole("MANAGER","HR")
                       .requestMatchers("/employee/project/add/{eid}/{pid}").hasRole("HR")
                       .requestMatchers("/project/employee/stat").hasRole("MANAGER")
                       .requestMatchers("/project/employee/{eid}").hasAnyRole("MANAGER","HR")
                       .requestMatchers("/leave/approval/{lid}/{status}").hasRole("MANAGER")
                       .requestMatchers("/leave/{lid}/{status}").hasRole("MANAGER")
                       .requestMatchers("/issue/track/{iid}").hasRole("MANAGER")            
                       .requestMatchers("/tech-interview/schedule/{jid}/{mid}").hasRole("HR")
                       .requestMatchers("/tech-scoresheet/update/{jid}").hasRole("MANAGER")        
                       .requestMatchers("/hr-interview/schedule/{jid}").hasRole("HR")
                       .requestMatchers("/hr-scoresheet/update/{jid}").hasRole("HR")
                       .requestMatchers("/salary/compute/{empId}").hasRole("HR")
                       
                       
                       //employee side and recruit hr - lavanya
                       .requestMatchers("/leave/record/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts rectified
                       .requestMatchers("/employee/attendance/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts rectified
                       .requestMatchers("/issue/record/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts rectified
                       .requestMatchers("/employee/salary/payroll").hasRole("EMPLOYEE")// works perfectly conflicts rectified if proper data in db
                       .requestMatchers("/job/add").permitAll() //works perfectly
                       .requestMatchers("/job/all").hasAnyRole("HR", "JOBSEEKER") //works perfectly
                       .requestMatchers("/job/one/{jobId}").hasAnyRole("HR", "JOBSEEKER") //works perfectly
                       .requestMatchers("/hr/screentest/experience/{appId}").hasAnyRole("HR")
                       .requestMatchers("/dashboard/recruit/display").permitAll()//works perfectly
                       .requestMatchers("/hr/screentest/skills/{appId}").permitAll()//works perfectly with proper data
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

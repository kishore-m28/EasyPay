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
		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
				.requestMatchers("/employees-by-title").permitAll()
				.requestMatchers("project/employees").permitAll()
				.requestMatchers("/manager/all").permitAll()
				.requestMatchers("/project/all/in-progress").permitAll()
				.requestMatchers("/project/all/completed").permitAll()
				.requestMatchers("project/set/status/completed/{pid}").permitAll()
				.requestMatchers("/auth/signup/jobseeker").permitAll().requestMatchers("/auth/signup/hr").permitAll()
				.requestMatchers("/auth/token").permitAll().requestMatchers("/auth/login").permitAll()
				.requestMatchers("/job/search/location").hasAnyRole("HR", "JOBSEEKER").requestMatchers("/admin/hello")
				.hasRole("HR").requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")

				.requestMatchers("/jobSeeker/add").permitAll().requestMatchers("/job/add").permitAll()// hasRole("HR")
				.requestMatchers("/job/all").permitAll()// hasAnyRole("HR", "JOBSEEKER")
				.requestMatchers("/job/one/{jobId}").permitAll()// hasAnyRole("HR", "JOBSEEKER")
				.requestMatchers("/jobSeeker/job/apply/{jobId}").hasRole("JOBSEEKER")

				.requestMatchers("/employee/add").hasRole("HR").requestMatchers("/employee/all").permitAll()
				.requestMatchers("/employee/one/{eid}").hasAnyRole("EMPLOYEE", "MANAGER", "HR")
				.requestMatchers("/employee/update/{eid}").hasAnyRole("EMPLOYEE", "HR")
				.requestMatchers("/employee/delete/{eid}").permitAll()
            		   .requestMatchers("/auth/signup/jobseeker").permitAll()
            		   .requestMatchers("/auth/signup/hr").permitAll()
                       .requestMatchers("/auth/token").permitAll()
                       .requestMatchers("/auth/login").permitAll()
                       .requestMatchers("/job/search/location").hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/admin/hello").hasRole("HR")
                       .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                       
                       .requestMatchers("/jobSeeker/add").permitAll()
                       .requestMatchers("/job/add").permitAll()//hasRole("HR")
                       .requestMatchers("/job/all").permitAll()//hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/job/one/{jobId}").permitAll()//hasAnyRole("HR", "JOBSEEKER")
                       .requestMatchers("/jobSeeker/job/apply/{jobId}").hasRole("JOBSEEKER")
                       .requestMatchers("/jobseeker/details/{appId}").permitAll()//hasRole("HR")
                       .requestMatchers("/jobseeker/application/all").permitAll()//hasRole("HR")
                       
                       .requestMatchers("/employee/add").hasRole("HR")   
                       .requestMatchers("/employee/all").permitAll()
                       .requestMatchers("/employee/one/{eid}").hasAnyRole("EMPLOYEE", "MANAGER","HR")
                       .requestMatchers("/employee/update/{eid}").hasAnyRole("EMPLOYEE", "HR")
                       .requestMatchers("/employee/delete/{eid}").permitAll()
             
                       .requestMatchers("/employee/salary").hasRole("EMPLOYEE")
                       .requestMatchers("/employee/onboard/{hrScoreSheetId}").hasRole("HR")
                       .requestMatchers("/employee/add-account-details/{eid}").hasRole("HR")

                       .requestMatchers("/salary/compute/{eid}").permitAll()
                       .requestMatchers("/salary/process/{eid}").permitAll()
                       .requestMatchers("/jobSeeker/basic/details").permitAll()
                       .requestMatchers("/jobSeeker/display/applied/jobs").hasRole("JOBSEEKER")
                       .requestMatchers("/jobSeeker/status/TechnicalInterview").hasRole("JOBSEEKER")
                       .requestMatchers("/jobSeeker/status/HrInterview").hasRole("JOBSEEKER")
                       .requestMatchers("/job/display/specific/details").permitAll()
                       .requestMatchers("/job/display/specific/details/{jobId}").permitAll()
                       .requestMatchers("/employee/display/filter").permitAll()
                       .requestMatchers("/employee/designation").permitAll()
                       .requestMatchers("/employee/department").permitAll()
                       .requestMatchers("/employee/city").permitAll()
                       .requestMatchers("/salary/status/pending/{id}").permitAll()
                       .requestMatchers("/salary/filter/display").permitAll()
                       .requestMatchers("/salary/process/inBatch").permitAll()
 
                       .requestMatchers("/job/search").hasAnyRole("HR", "JOBSEEKER")
                      
                       .requestMatchers("/admin/hello").hasRole("HR")
                       .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                       .requestMatchers("/employee/active-count").permitAll()
                       .requestMatchers("/employee/gender/stat").permitAll()
                       
                      
                       .requestMatchers("/compliance/minimum-wage/{employeeId}").hasRole("HR")
                       .requestMatchers("/compliance-report/generate/{complianceId}").hasRole("HR")
                       .requestMatchers("/compliance-report/view/{complianceId}").permitAll()
                       .requestMatchers("/salary/set/{eid}").hasRole("HR")
 
                       
                       .requestMatchers("/manager/add").hasRole("HR")
                       .requestMatchers("/manager/project").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/manager/employee").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/manager/employee/{eid}").permitAll()//.hasRole("MANAGER")
                       .requestMatchers("/manager/employee/project/{eid}").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/manager/employee/count").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/manager/employee/count/gender").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/work/assign/{eid}").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/work/get/{eid}").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/leave/all").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/manager/leave/requests").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/leave/approval/{lid}/{status}").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/leave/{lid}/{status}").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/issue/all").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/issue/{iid}").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/issue/reply/{iid}").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/project/add/{managerId}").permitAll()//.hasRole("HR")
                       .requestMatchers("/project/{pid}").permitAll()//hasAnyRole("MANAGER","HR")
                       .requestMatchers("/employee/project/add/{eid}/{pid}").permitAll()//hasRole("HR") 
                       .requestMatchers("/project/employee/stat").hasRole("MANAGER")
                       .requestMatchers("/project/employee/{eid}").hasAnyRole("MANAGER","HR")
                       
                       
                       .requestMatchers("/hr/screentest/{appId}").permitAll()//hasAnyRole("HR")
                       .requestMatchers("/tech-interview/schedule/{aid}/{mid}").permitAll()//.hasRole("HR")
                       .requestMatchers("/tech-interview/all").permitAll()//hasRole("MANAGER")
                       .requestMatchers("/tech-scoresheet/update/{aid}").permitAll()//hasRole("MANAGER") 
                       .requestMatchers("/tech-scoresheet/status/all").permitAll()//hasRole("HR")
                       .requestMatchers("/hr-interview/schedule/{aid}").permitAll()//hasRole("HR")
                       .requestMatchers("/hr-interview/all").permitAll()//hasRole("HR")
                       .requestMatchers("/hr-scoresheet/update/{aid}").permitAll()//hasRole("HR")
                       .requestMatchers("/hr-scoresheet/status/all").permitAll()//hasRole("HR")
                       .requestMatchers("/salary/compute/{empId}").permitAll()//hasRole("HR")
                       
                       
                       //employee side and recruit hr - lavanya
                       .requestMatchers("/leave/record/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts rectified
                       .requestMatchers("/employee/attendance/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts rectified
                       .requestMatchers("/issue/record/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts rectified
                       .requestMatchers("/employee/salary/payroll").hasRole("EMPLOYEE")// works perfectly conflicts rectified if proper data in db
                       .requestMatchers("/job/add").permitAll() //works perfectly
                       .requestMatchers("/job/all").hasAnyRole("HR", "JOBSEEKER") //works perfectly
                       .requestMatchers("/job/one/{jobId}").hasAnyRole("HR", "JOBSEEKER") //works perfectly
                       .requestMatchers("/dashboard/recruit/display").permitAll()//works perfectly
                       .requestMatchers("/hr/screentest/skills/{appId}").permitAll()//works perfectly with proper data
                       .requestMatchers("/employee/salary").hasRole("EMPLOYEE")
       				.requestMatchers("/employee/onboard/{hrScoreSheetId}").hasRole("HR")
       				.requestMatchers("/employee/add-account-details/{eid}").hasRole("HR")

       				.requestMatchers("/salary/compute/{eid}").hasRole("HR").requestMatchers("/salary/process/{eid}")
       				.hasRole("HR").requestMatchers("/jobSeeker/basic/details").permitAll()
       				.requestMatchers("/jobSeeker/display/applied/jobs").hasRole("JOBSEEKER")
       				.requestMatchers("/jobSeeker/status/TechnicalInterview").hasRole("JOBSEEKER")
       				.requestMatchers("/jobSeeker/status/HrInterview").hasRole("JOBSEEKER")
       				.requestMatchers("/job/display/specific/details").permitAll()
       				.requestMatchers("/job/display/specific/details/{jobId}").hasAnyRole("HR", "JOBSEEKER")

       				.requestMatchers("/job/search").hasAnyRole("HR", "JOBSEEKER")

       				.requestMatchers("/admin/hello").hasRole("HR").requestMatchers("/user/hello")
       				.hasAnyRole("USER", "ADMIN").requestMatchers("/employee/active-count").permitAll()

       				.requestMatchers("/compliance/minimum-wage/{employeeId}").hasRole("HR")
       				.requestMatchers("/compliance-report/generate/{complianceId}").hasRole("HR")
       				.requestMatchers("/compliance-report/view/{complianceId}").permitAll()
       				.requestMatchers("/salary/set/{eid}").hasRole("HR")

       				.requestMatchers("/manager/add").hasRole("HR").requestMatchers("/manager/project").hasRole("MANAGER")
       				.requestMatchers("/manager/employee").permitAll()// .hasRole("MANAGER")
       				.requestMatchers("/manager/employee/{eid}").permitAll()// .hasRole("MANAGER")
       				.requestMatchers("/manager/employee/project/{eid}").permitAll()// .hasRole("MANAGER")
       				.requestMatchers("/manager/employee/count").permitAll()// hasRole("MANAGER")
       				.requestMatchers("/project/add/{managerId}").hasRole("HR").requestMatchers("/project/{pid}")
       				.hasAnyRole("MANAGER", "HR")
       				
       				.requestMatchers("/employee/project/add/{eid}/{pid}").hasRole("HR")
       				.requestMatchers("/project/employee/stat").hasRole("MANAGER").requestMatchers("/project/employee/{eid}")
       				.hasAnyRole("MANAGER", "HR").requestMatchers("/work/assign/{eid}").permitAll()// hasAnyRole("MANAGER")
       				.requestMatchers("/work/get/{eid}").permitAll()// hasAnyRole("MANAGER")
       				.requestMatchers("/leave/all").permitAll()// hasAnyRole("MANAGER")
       				.requestMatchers("/manager/leave/requests").permitAll()// hasAnyRole("MANAGER")
       				.requestMatchers("/leave/approval/{lid}/{status}").permitAll()// hasRole("MANAGER")
       				.requestMatchers("/leave/{lid}/{status}").permitAll()// hasRole("MANAGER")
       				.requestMatchers("/issue/all").permitAll()// hasAnyRole("MANAGER")
       				.requestMatchers("/issue/{iid}").permitAll()// hasAnyRole("MANAGER")
       				.requestMatchers("/issue/reply/{iid}").permitAll()// hasAnyRole("MANAGER")
       				.requestMatchers("/tech-interview/schedule/{jid}/{mid}").hasRole("HR")
       				.requestMatchers("/tech-scoresheet/update/{jid}").hasRole("MANAGER")
       				.requestMatchers("/hr-interview/schedule/{jid}").hasRole("HR")
       				.requestMatchers("/hr-scoresheet/update/{jid}").hasRole("HR").requestMatchers("/salary/compute/{empId}")
       				.hasRole("HR").requestMatchers("/hr/jobseeker/status").permitAll()
       				
       				//employee-side - kishore
       				.requestMatchers("work/view").permitAll()
       				.requestMatchers("/project/types").permitAll()
       				.requestMatchers("/project/active-count").permitAll()
       				.requestMatchers("/project/completed-count").permitAll()
       				.requestMatchers("/project/upcoming-count").permitAll()
       				.requestMatchers("/project/overdue-count").permitAll()
       				.requestMatchers("project/update-status/{id}").permitAll()
       				.requestMatchers("/project/assign-employees").permitAll()
       				
       				
       				

       				// employee side and recruit hr - lavanya
       				.requestMatchers("/leave/record/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts rectified
       				.requestMatchers("/employee/attendance/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts
       																						// rectified
       				.requestMatchers("/issue/record/add/{mid}").hasRole("EMPLOYEE")// works perfectly conflicts rectified
       				.requestMatchers("/employee/salary/payroll").hasRole("EMPLOYEE")// works perfectly conflicts rectified
       																				// if proper data in db
       				.requestMatchers("/job/add").permitAll() // works perfectly
       				.requestMatchers("/job/all").hasAnyRole("HR", "JOBSEEKER") // works perfectly
       				.requestMatchers("/job/one/{jobId}").hasAnyRole("HR", "JOBSEEKER") // works perfectly
       				.requestMatchers("/hr/screentest/{appId}").permitAll()// hasAnyRole("HR")
       				.requestMatchers("/dashboard/recruit/display").permitAll()// works perfectly
       				.requestMatchers("/hr/screentest/skills/{appId}").permitAll()// works perfectly with proper data
                       .anyRequest().authenticated()
               )
               .sessionManagement(session -> session
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               );

				

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder getEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}

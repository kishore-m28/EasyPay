package com.payroll_app.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Issue;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.model.TechnicalInterview;
import com.payroll_app.project.repository.IssueRepository;
import com.payroll_app.project.repository.ManagerRepository;
import com.payroll_app.project.repository.ProjectRepository;
import com.payroll_app.project.repository.TechnicalInterviewRepository;
import com.payroll_app.project.service.IssueService;
import com.payroll_app.project.service.ManagerService;
import com.payroll_app.project.service.TechnicalInterviewService;

@SpringBootTest(classes = ProjectApplication.class)
public class ManagerServiceTest {
	
	@InjectMocks
	private ManagerService managerService;
	
	@InjectMocks
	private IssueService issueService;
	
	@InjectMocks
	private TechnicalInterviewService technicalInterviewService;
	
	@Mock
	private ManagerRepository managerRepository;
	
	@Mock
	private ProjectRepository projectRepository;
	
	@Mock
	private IssueRepository issueRepository;
	
	@Mock
	private TechnicalInterviewRepository technicalInterviewRepository;
	
	@Test
	public void getProjectByManagerUsernameTest() {
		Project p1 = new Project();
		p1.setName("Booking Platform");
		
		Project p2 = new Project();
		p2.setName("HealthCare Platform");
		
		List<Project> expectedList = Arrays.asList(p1,p2);
		
		when( managerService.getProjectByManagerUsername("Harry")).thenReturn(expectedList); 
		
		List<Project> actualList = managerService.getProjectByManagerUsername("Harry");
		
		assertNotNull(actualList);
		
		int expectedValue= expectedList.size();
		int actualValue = actualList.size();
		
		assertEquals(expectedValue, actualValue);		
	}
	
	@Test
	public void getEmployeeByManagerUsernameTest() {
		Employee e1 = new Employee();
		e1.setName("abc");
		
		Employee e2 = new Employee();
		e2.setName("xyz");

		Employee e3 = new Employee();
		e3.setName("def");

		Employee e4 = new Employee();
		e4.setName("abcde");

		Employee e5 = new Employee();
		e5.setName("abcxyz");
		
		List<Employee> expectedList = Arrays.asList(e1,e2,e3,e4,e5);
		int expectedValue = 5;
		
		when(managerService.getEmployeeByManagerUsername("Annie")).thenReturn(expectedList);
		
		List<Employee> actualList = managerService.getEmployeeByManagerUsername("Annie");
		assertNotNull(actualList);
		
		int actualValue = actualList.size();
		assertEquals(expectedValue, actualValue);
		
	}
	
	
	@Test
	public void getIssueByIdTest() {
		
		Issue i = new Issue();
		i.setDescription("description");
		
		//Valid value
		try {
			when(issueService.getById(1)).thenReturn(i);
			Issue i2 = issueService.getById(1);
			assertEquals(i, i2);
		}
		catch(InvalidIdException e) {
			
		}
		
		//Invalid value
		try {
			when(issueService.getById(1000)).thenThrow(InvalidIdException.class);
			issueService.getById(1000);
		}
		catch(InvalidIdException e) {
			assertNotNull(e);
		}
		
	}
	
	@Test
	public void getAllTechnicalInterviewsTest() {
		TechnicalInterview t1 = new TechnicalInterview();
		t1.setId(1);
		
		TechnicalInterview t2 = new TechnicalInterview();
		
		TechnicalInterview t3 = new TechnicalInterview();
		
		List<TechnicalInterview> expectedList = Arrays.asList(t1,t2,t3);
		int expectedValue = 3;
		
		when(technicalInterviewService.getAll("Harry")).thenReturn(expectedList);
		List<TechnicalInterview> actualList = technicalInterviewService.getAll("Harry");
		
		assertNotNull(actualList);
		int actualValue = actualList.size();
		assertEquals(expectedValue, actualValue);
	}

}

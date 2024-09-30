package com.payroll_app.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.RecruitDashBoardDto;
import com.payroll_app.project.service.RecruitDashBoardService;

@RestController
@RequestMapping("/dashboard")
public class RecruitDashBoardController {
	
	@Autowired
	private RecruitDashBoardService recruitDashBoardService;
	
	@GetMapping("/recruit/display")
	public List<RecruitDashBoardDto> recruitDashBoard()
	{
		return recruitDashBoardService.recruitDashBoard();
	}

}

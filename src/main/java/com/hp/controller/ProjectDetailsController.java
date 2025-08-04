package com.hp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hp.service.ProjectDetailsService;

@Controller
public class ProjectDetailsController {

	@Autowired
	ProjectDetailsService projectDetailsService;
	
	@RequestMapping(value="get_project_data", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_project_data(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		String search = request.getParameter("search[value]");
		String employee_id =request.getParameter("employee_id");
		String status =request.getParameter("status");
		String month =request.getParameter("month");
		String year =request.getParameter("year");
		response = projectDetailsService.get_project_data(start,length,search,employee_id,status,month,year);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

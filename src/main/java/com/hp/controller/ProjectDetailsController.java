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
		String module =request.getParameter("module");
		String from_date =request.getParameter("from_date");
		String to_date =request.getParameter("to_date");
		response = projectDetailsService.get_project_data(start,length,search,employee_id,status,from_date,to_date,module);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_details", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_details(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String employee_id =request.getParameter("employee_id");
		String from_date =request.getParameter("from_date");
		String to_date =request.getParameter("to_date");
		response = projectDetailsService.get_details(employee_id,from_date,to_date);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_article_details", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_article_details(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String from_date =request.getParameter("from_date");
		String to_date =request.getParameter("to_date");
		response = projectDetailsService.get_article_details(from_date,to_date);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_positions_details", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_positions_details(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String journal_name =request.getParameter("journal_name");
		String from_date =request.getParameter("from_date");
		String to_date =request.getParameter("to_date");
		response = projectDetailsService.get_positions_details(journal_name,from_date,to_date);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_notifications", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_notifications(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String employee_id =request.getParameter("employee_id");
		String from_date =request.getParameter("from_date");
		String to_date =request.getParameter("to_date");
		response = projectDetailsService.get_notifications(employee_id,from_date,to_date);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

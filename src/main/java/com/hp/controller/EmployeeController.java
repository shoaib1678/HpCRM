package com.hp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hp.model.Employee;
import com.hp.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "add_employee", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_employee(@RequestBody Employee employee){
		Map<String, Object> response = new HashMap<String,Object>();
		response = employeeService.add_employee(employee);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_employee", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_employee(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		String search = request.getParameter("search[value]");
		String employee_id =request.getParameter("employee_id");
		response = employeeService.get_employee(start,length,search,employee_id);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/get_empById",method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> get_empById(HttpServletRequest request ){
		String sno = request.getParameter("sno");
		Map<String, Object> response = employeeService.get_empById(sno);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

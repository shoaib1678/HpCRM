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

import com.hp.model.ContactDetails;
import com.hp.model.WritingDetails;
import com.hp.service.WritingService;

@Controller
public class WritingController {

	@Autowired
	WritingService writingService;
	
	@RequestMapping(value = "add_writing_details", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_writing_details(@RequestBody WritingDetails writingDetails){
		Map<String, Object> response = new HashMap<String,Object>();
		response = writingService.add_writing_details(writingDetails);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_writing_details", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_writing_details(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		String search = request.getParameter("search[value]");
		String employee_id =request.getParameter("employee_id");
		String status =request.getParameter("status");
		response = writingService.get_writing_details(start,length,search,employee_id,status);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="add_subject_area", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_subject_area(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String subject_area =request.getParameter("subject_area");
		String sno =request.getParameter("sno");
		response = writingService.add_subject_area(subject_area,sno);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="writing_recieved_payment", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> writing_recieved_payment(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno =request.getParameter("sno");
		response = writingService.writing_recieved_payment(sno);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="sent_data_confirm", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> sent_data_confirm(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno =request.getParameter("sno");
		String status =request.getParameter("status");
		response = writingService.sent_data_confirm(sno,status);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

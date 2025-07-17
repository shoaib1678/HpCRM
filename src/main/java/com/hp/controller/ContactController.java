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
import com.hp.model.ContactRemarks;
import com.hp.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@RequestMapping(value = "add_contact", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_contact(@RequestBody ContactDetails contactDetails){
		Map<String, Object> response = new HashMap<String,Object>();
		response = contactService.add_contact(contactDetails);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_contact", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_contact(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		String search = request.getParameter("search[value]");
		String employee_id =request.getParameter("employee_id");
		String status =request.getParameter("status");
		response = contactService.get_contact(start,length,search,employee_id,status);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value = "add_remarks", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_remarks(@RequestBody ContactRemarks crRemarks){
		Map<String, Object> response = new HashMap<String,Object>();
		response = contactService.add_remarks(crRemarks);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	
}

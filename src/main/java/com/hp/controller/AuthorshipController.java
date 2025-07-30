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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hp.model.AuthorshipDetails;
import com.hp.model.Authorship_Article;
import com.hp.service.AuthorshipService;

@Controller
public class AuthorshipController {

	@Autowired
	AuthorshipService authorshipService;
	
	@RequestMapping(value = "add_author", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_author(@RequestBody AuthorshipDetails ad){
		Map<String, Object> response = new HashMap<String,Object>();
		response = authorshipService.add_author(ad);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_author", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_author(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		String search = request.getParameter("search[value]");
		String employee_id = request.getParameter("employee_id");
		String status = request.getParameter("status");
		response = authorshipService.get_author(start,length,search,employee_id,status);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="received_authorship_payment", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> received_authorship_payment(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno = request.getParameter("sno");
		response = authorshipService.received_authorship_payment(sno);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="send_author_acceptance", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> send_author_acceptance(HttpServletRequest request,@RequestParam(value="file", required = false) MultipartFile file){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno =request.getParameter("sno");
		String status =request.getParameter("status");
		response = authorshipService.send_author_acceptance(sno,file,status);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="add_galleryproof_date", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_galleryproof_date(HttpServletRequest request,@RequestParam(value="file", required = false) MultipartFile file){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno =request.getParameter("sno");
		String proof_date =request.getParameter("proof_date");
		response = authorshipService.add_galleryproof_date(sno,file,proof_date);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

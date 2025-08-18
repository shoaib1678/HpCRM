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

import com.hp.model.Authorship_Article;
import com.hp.model.Employee;
import com.hp.service.AuthorshipArticleService;

@Controller
public class AuthorshipArticleController {

	@Autowired
	AuthorshipArticleService authorshipArticleService;
	
	@RequestMapping(value = "add_aa", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_aa(@RequestBody Authorship_Article ap){
		Map<String, Object> response = new HashMap<String,Object>();
		response = authorshipArticleService.add_aa(ap);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_aa", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_employee(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		String search = request.getParameter("search[value]");
		response = authorshipArticleService.get_aa(start,length,search);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_aaById", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_aabyId(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno = request.getParameter("sno");
		response = authorshipArticleService.get_aabyId(sno);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_position", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_position(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno = request.getParameter("sno");
		response = authorshipArticleService.get_position(sno);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_title", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_title(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String journal_name = request.getParameter("journal_name");
		response = authorshipArticleService.get_title(journal_name);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

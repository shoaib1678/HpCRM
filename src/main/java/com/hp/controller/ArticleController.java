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

import com.google.gson.Gson;
import com.hp.model.ArticleDetails;
import com.hp.model.ContactDetails;
import com.hp.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value = "add_articledetails", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_articledetails(@RequestParam(value = "articledata") String articledata,@RequestParam(value = "file",required = false) MultipartFile file){
		Map<String, Object> response = new HashMap<String,Object>();
		Gson gson = new Gson();
		ArticleDetails articleDetails = gson.fromJson(articledata, ArticleDetails.class);
		response = articleService.add_articledetails(articleDetails,file);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="get_articledetails", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> get_articledetails(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		String search = request.getParameter("search[value]");
		String employee_id =request.getParameter("employee_id");
		String status =request.getParameter("status");
		response = articleService.get_articledetails(start,length,search,employee_id,status);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="edit_articledetails", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> edit_articledetails(HttpServletRequest request){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno =request.getParameter("sno");
		response = articleService.edit_articledetails(sno);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="send_foracceptance", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> send_foracceptance(HttpServletRequest request,@RequestParam(value="file", required = false) MultipartFile file){
		Map<String, Object> response = new HashMap<String,Object>();
		String sno =request.getParameter("sno");
		String status =request.getParameter("status");
		response = articleService.send_foracceptance(sno,file,status);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value = "add_gallery_proof", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_gallery_proof(@RequestBody ArticleDetails art){
		Map<String, Object> response = new HashMap<String,Object>();
		response = articleService.add_gallery_proof(art);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

package com.hp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hp.service.LoginService;


@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/checklogin", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(HttpServletRequest request,@RequestParam("email") String email, @RequestParam("password") String password){
		Map<String, Object> resposne = new HashMap<String,Object>();
		String authentication_id = request.getParameter("authentication_id");
		resposne = loginService.login(email,password,authentication_id);
		return new ResponseEntity<Map<String,Object>>(resposne,HttpStatus.OK);
	}
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		session.invalidate();		
		return new ModelAndView("redirect:./");
	}
	@RequestMapping(value="/change_password",method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> change_password1(HttpServletRequest request){
		String email = request.getParameter("email");
		Map<String, Object> response = loginService.change_password(email);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="/reset_password",method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> reset_password(HttpServletRequest request){
		String sno = request.getParameter("sno");
		String password = request.getParameter("password");
		Map<String, Object> response = loginService.reset_password(sno,password);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

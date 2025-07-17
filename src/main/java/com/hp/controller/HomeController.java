package com.hp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("CRM/Authentication/login");
	}
	@RequestMapping(value="/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) throws IOException{
		return new ModelAndView("CRM/Dashboard/dashboard");
	}
	@RequestMapping(value="/manage_employee")
	public ModelAndView manage_employee(HttpServletRequest request) throws IOException{
		return new ModelAndView("CRM/Employee/employee");
	}
	@RequestMapping(value="/manage_contact")
	public ModelAndView manage_contact(HttpServletRequest request) throws IOException{
		return new ModelAndView("CRM/Contact/contact");
	}
}

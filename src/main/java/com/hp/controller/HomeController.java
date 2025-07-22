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
	@RequestMapping(value="/manage_converted_client")
	public ModelAndView manage_converted_client(HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView("CRM/Publication/converted_client");
		return mv;
	}
	@RequestMapping(value="/manage_article_detaisl")
	public ModelAndView manage_article_detaisl(HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView("CRM/Publication/article_details");
		return mv;
	}
	@RequestMapping(value="/manage_acceptance")
	public ModelAndView manage_acceptance(HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView("CRM/Publication/acceptance");
		return mv;
	}
	@RequestMapping(value="/publication_payment")
	public ModelAndView publication_payment(HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView("CRM/Publication/publication_payment");
		return mv;
	}
	@RequestMapping(value="/publication_gallery_proof")
	public ModelAndView publication_gallery_proof(HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView("CRM/Publication/gallery_proof");
		return mv;
	}
	@RequestMapping(value="/publication_remaining_payment")
	public ModelAndView publication_remaining_payment(HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView("CRM/Publication/remaining");
		return mv;
	}
	@RequestMapping(value="/publication_details")
	public ModelAndView publication_details(HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView("CRM/Publication/publication_details");
		return mv;
	}
}

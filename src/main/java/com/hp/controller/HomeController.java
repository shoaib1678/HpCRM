package com.hp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.ContentDisposition;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hp.dao.CommonDao;
import com.hp.model.ArticleDetails;
import com.hp.model.AuthorshipDetails;
import com.hp.model.Authorship_Article;
import com.hp.model.ContactDetails;
import com.hp.model.ConvertedModule;
import com.hp.model.Employee;
import com.hp.model.LoginCredentials;
import com.hp.model.WritingDetails;
import com.hp.utils.EncriptionData;
import com.hp.utils.Utils;


@Controller
public class HomeController {
	
	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("CRM/Authentication/login");
	}
	@RequestMapping(value="/change_password")
	public ModelAndView change_password(HttpServletResponse response) throws IOException{
		return new ModelAndView("CRM/Authentication/reset_password");
	}
	@RequestMapping(value="/reset")
	public ModelAndView reset(HttpServletRequest request) throws IOException{
		String sno = request.getParameter("id");
		System.out.println("sno="+sno);
		ModelAndView mv =  new ModelAndView("CRM/Authentication/reset_url");
		mv.addObject("sno", sno);
		return mv;
	}
	@RequestMapping(value="/dashboard")
	public ModelAndView dashboard(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Dashboard/dashboard");
			Map<String, Object> mp = new HashMap<String, Object>();
			if(!log.getUser_type().equalsIgnoreCase("Admin")) {
				mp.put("employee_id", log.getEmployee_id());
			}
			return mv;
		}else {
			
			String email = request.getParameter("email");
			String demail = email;
			String eml="";
			try {
				eml = encriptionData.encrypt(email);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", eml);
			List<LoginCredentials> login = (List<LoginCredentials>)commonDao.getDataByMap(map, new LoginCredentials(), null, null, 0, -1);
			if(login.size() > 0) {
				login.get(0).setEmail(demail);
				Map<String, Object> mp = new HashMap<String, Object>();
				if(!login.get(0).getUser_type().equalsIgnoreCase("Admin")) {
					mp.put("employee_id", login.get(0).getEmployee_id());
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("email", eml);
					List<Employee> emp = (List<Employee>)commonDao.getDataByMap(map1, new Employee(), null, null, 0, -1);
					login.get(0).setEmp_name(emp.get(0).getEmployee_name());
				}
				ModelAndView mv = new ModelAndView("CRM/Dashboard/dashboard");
				
				session.setAttribute("loginData", login.get(0));
				return mv;
			}else {
				return new ModelAndView("redirect:./");
			}
			
			
		}
	}
	@RequestMapping(value="/manage_employee")
	public ModelAndView manage_employee(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			return new ModelAndView("CRM/Employee/employee");
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/manage_contact")
	public ModelAndView manage_contact(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			return new ModelAndView("CRM/Contact/contact");
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	
	@RequestMapping(value = "/displaydocument", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayDocument(HttpServletRequest request) throws IOException {
	    String fileName = request.getParameter("url");
	    File file = new File(Utils.staticimages + "/" + fileName);

	    if (!file.exists()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    // Guess content type
	    String mimeType = Files.probeContentType(file.toPath());
	    if (mimeType == null) {
	        // Set manually if guessing fails
	        if (fileName.endsWith(".doc")) {
	            mimeType = "application/msword";
	        } else if (fileName.endsWith(".docx")) {
	            mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	        } else if (fileName.endsWith(".pdf")) {
	            mimeType = "application/pdf";
	        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
	            mimeType = "image/jpeg";
	        } else if (fileName.endsWith(".png")) {
	            mimeType = "image/png";
	        } else {
	            mimeType = "application/octet-stream";
	        }
	    }

	    byte[] fileContent;
	    try (InputStream in = new FileInputStream(file)) {
	        fileContent = IOUtils.toByteArray(in);
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType(mimeType));
	    // Set to inline to display in browser if supported
	    headers.add("Content-Disposition", "inline; filename=\"" + fileName + "\"");
	    headers.setCacheControl("no-cache");

	    return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}



	
	/*******************************Publication***************************************/
	
	@RequestMapping(value="/manage_converted_client")
	public ModelAndView manage_converted_client(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Publication/converted_client");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/manage_article_details")
	public ModelAndView manage_article_detaisl(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Publication/article_details");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/manage_acceptance")
	public ModelAndView manage_acceptance(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Publication/acceptance");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/publication_payment")
	public ModelAndView publication_payment(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Publication/publication_payment");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/publication_galley_proof")
	public ModelAndView publication_gallery_proof(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Publication/gallery_proof");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/publication_remaining_payment")
	public ModelAndView publication_remaining_payment(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Publication/remaining");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/publication_details")
	public ModelAndView publication_details(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Publication/publication_details");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/published_article_details")
	public ModelAndView published_paper_details(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Publication/published_paper");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	
	/******************Writing Article********************/
	
	@RequestMapping(value="/writing_converted_client")
	public ModelAndView writing_converted_client(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/converted_client");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/manage_writing_article")
	public ModelAndView manage_writing_article(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/writing_article");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/manage_subject_area")
	public ModelAndView manage_subject_area(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/subject_area");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/manage_writing_initial_payment")
	public ModelAndView manage_writing_initial_payment(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/writing_payment");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/manage_initial_work")
	public ModelAndView manage_initial_work(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/initial_work");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/writing_paper_confirmation")
	public ModelAndView writing_paper_confirmation(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/confirmation");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/writing_paper_remaining_amount")
	public ModelAndView writing_paper_remaining_amount(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/remaining_amount");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/writing_paper_full_work")
	public ModelAndView writing_paper_full_work(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/full_work");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/writing_paper_full_work_confirmation")
	public ModelAndView writing_paper_full_work_confirmation(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/work_confirmation");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/writing_paper_final_work_details")
	public ModelAndView writing_paper_final_work_details(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/WritingPaper/final_work_details");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	
	/***********************************Authorship*****************************************/
	@RequestMapping(value="/authorship_article_details")
	public ModelAndView authorship_article_details(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/article_details");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/authorship_converted_client")
	public ModelAndView authorship_converted_client(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/converted_client");
			List<String> journal_name= (List<String>)commonDao.getDistinctData("journal_name", new Authorship_Article());
			mv.addObject("journal_name", journal_name);
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/author_details")
	public ModelAndView author_details(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/client_details");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/authorship_initial_payment")
	public ModelAndView authorship_initial_payment(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/initial_payment");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/authorship_acceptance")
	public ModelAndView authorship_acceptance(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/acceptance");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/authorship_remaining_payment")
	public ModelAndView authorship_remaining_payment(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/remaining");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/authorship_final_payment")
	public ModelAndView authorship_final_payment(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/final_payment");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/authorship_galley_proof")
	public ModelAndView authorship_gallery_proof(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/gallery_proof");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	@RequestMapping(value="/authorship_publication_details")
	public ModelAndView authorship_publication_details(HttpServletRequest request, HttpSession session) throws IOException{
		LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
		if(log != null) {
			ModelAndView mv = new ModelAndView("CRM/Authorship/publication_details");
			return mv;
		}else {
			return new ModelAndView("redirect:./");
		}
		
	}
	
}

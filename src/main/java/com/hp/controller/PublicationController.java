package com.hp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.hp.model.PublicationDetails;
import com.hp.service.PublicationService;

@Controller
public class PublicationController {

	@Autowired
	PublicationService publicationService;
	
	@RequestMapping(value = "add_publication_details", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_publication_details(@RequestParam(value = "publicationdata") String publicationdata,@RequestParam(value = "publicationpdf",required = false) MultipartFile publicationpdf){
		Map<String, Object> response = new HashMap<String,Object>();
		Gson gson = new Gson();
		PublicationDetails publication = gson.fromJson(publicationdata, PublicationDetails.class);
		response = publicationService.add_publication_details(publication,publicationpdf);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

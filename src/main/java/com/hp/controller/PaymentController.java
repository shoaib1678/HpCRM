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
import com.hp.model.ArticleDetails;
import com.hp.model.Payment;
import com.hp.service.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@RequestMapping(value = "add_payment", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add_payment(@RequestParam(value = "paymentdata") String paymentdata,@RequestParam(value = "file",required = false) MultipartFile file){
		Map<String, Object> response = new HashMap<String,Object>();
		Gson gson = new Gson();
		Payment payment = gson.fromJson(paymentdata, Payment.class);
		response = paymentService.add_payment(payment,file);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}

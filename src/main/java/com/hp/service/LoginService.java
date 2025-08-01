package com.hp.service;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.dao.CommonDao;
import com.hp.model.Employee;
import com.hp.model.LoginCredentials;
import com.hp.utils.EncriptionData;


@Service
public class LoginService {

	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;
	@Autowired
	EmailService emailService;
	
	public Map<String, Object> login(String email, String password, String authentication_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String eml = encriptionData.encrypt(email);
			String pass = encriptionData.encrypt(password);
			map.put("email", eml);
			map.put("password", pass);
			map.put("status", "Active");
			List<LoginCredentials> data = (List<LoginCredentials>) commonDao.getDataByMap(map, new LoginCredentials(),null, null, 0, -1);
			System.out.println("size="+data.size());
			if (data.size() > 0) {
				if(data.get(0).getUser_type().equalsIgnoreCase("Admin")) {
					response.put("status", "Success");
					return response;
				}else {
					if(data.get(0).getAuthentication_id().equalsIgnoreCase(authentication_id)) {
						response.put("status", "Success");
						return response;
					}else {
						response.put("status", "Failed");
						return response;
					}
				}
				
			} else {
				response.put("status", "Failed");
				response.put("message", "Something Went Wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong" + e);
		}
		return response;
		
	}
	public Map<String, Object> change_password(String email) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			String eml = encriptionData.encrypt(email);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", eml);
			List<LoginCredentials> data = (List<LoginCredentials>) commonDao.getDataByMap(map, new LoginCredentials(),null, null, 0, -1);
			if (data.size() > 0) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("sno", data.get(0).getEmployee_id());
				List<Employee> emp = (List<Employee>) commonDao.getDataByMap(map1, new Employee(),null, null, 0, -1);
				String name="";
				if(emp.size() > 0) {
					name=emp.get(0).getEmployee_name();
				}
				String id = encriptionData.encrypt(String.valueOf(data.get(0).getSno()));
				String encodedId = URLEncoder.encode(id, "UTF-8");
				String resetURL = "https://crm.haliconpub.com/reset?id=" + encodedId;
				//String resetURL = "http://localhost:8082/HpCRM/reset?id="+encodedId;
				String subject = "Your Halicon Publication CRM Password Reset Request";
				String message =
					    "<!DOCTYPE html>" +
					    "<html><head>" +
					    "<style>" +
					    "  body { font-family: Arial, sans-serif; line-height: 1.6; background-color: #f9f9f9; padding: 20px; }" +
					    "  .container { max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #fff; margin: auto; }" +
					    "  h2 { color: #2c3e50; text-align: center; }" +
					    "  .highlight { font-weight: bold; color: #333; }" +
					    "  .footer { margin-top: 20px; font-size: 14px; color: #777; text-align: center; }" +
					    "  .logo { display: block; margin: 0 auto 20px; max-height: 80px; }" +
					    "  .btn { display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff !important; text-decoration: none; border-radius: 5px; margin-top: 20px; }" +
					    "</style>" +
					    "</head><body>" +
					    "<div class='container'>" +
					    "<img src='https://haliconpub.com/assets/img/hlogo.png' alt='Company Logo' class='logo'>" +
					    "<h2>Password Reset Request</h2>" +
					    "<p>Dear <span class='highlight'>" + name + "</span>,</p>" +
					    "<p>We received a request to reset your password. Click the button below to reset your password:</p>" +
					    "<p style='text-align: center;'><a href='" + resetURL + "' class='btn'>Reset Password</a></p>" +
					    "<p>If you didn’t request this, you can ignore this email. Your password will remain unchanged.</p>" +
					    "<p>Best regards,<br/>" +
					    "IT Support Team<br/>" +
					    "Halicon Publication</p>" +
					    "<div class='footer'>" +
					    "This is an automated email. Please do not reply directly." +
					    "</div>" +
					    "</div></body></html>";
					emailService.sendEmailMessage(email, subject, message);
				response.put("status", "Success");
				response.put("message", "Password Changed Successfully");
				return response;		
			} else {
				System.out.println("Failed");
				response.put("status", "Failure");
				response.put("message", "Something went wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong" + e);
		}
		return response;
	}

	public Map<String, Object> reset_password(String sno, String password) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			System.out.println("sn="+sno);
			String sn = encriptionData.decrypt(sno);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sno", Integer.parseInt(sn));
			List<LoginCredentials> data = (List<LoginCredentials>) commonDao.getDataByMap(map, new LoginCredentials(),null, null, 0, -1);
			if (data.size() > 0) {
				String pass = encriptionData.encrypt(password);
				data.get(0).setPassword(pass);
				commonDao.updateDataToDb(data.get(0));
					response.put("status", "Success");
					response.put("message", "Password Reset Successfully");
			} else {
				response.put("status", "Failed");
				response.put("message", "Invalid Current Password");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong" + e);
		}
		return response;
	}
}

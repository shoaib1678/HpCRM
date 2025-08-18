package com.hp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.dao.CommonDao;
import com.hp.model.Employee;
import com.hp.model.LoginCredentials;
import com.hp.utils.EncriptionData;
import com.hp.utils.Utils;

@Service
public class EmployeeService {

	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;
	@Autowired
	EmailService emailService;

	public Map<String, Object> add_employee(Employee employee) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			String encEmail = encriptionData.encrypt(employee.getEmail());
			if(employee.getSno() > 0) {
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("sno", employee.getSno());
				List<Employee> data = (List<Employee>)commonDao.getDataByMap(map, new Employee(), null, null, 0, -1);
				data.get(0).setEmployee_name(employee.getEmployee_name());
				data.get(0).setMobile_number(employee.getMobile_number());
				data.get(0).setEmail(encEmail);
				commonDao.updateDataToDb(data.get(0));
				Map<String, Object> map1 = new HashMap<String,Object>();
				map1.put("employee_id", employee.getSno());
				List<LoginCredentials> log = (List<LoginCredentials>)commonDao.getDataByMap(map1, new LoginCredentials(), null, null, 0, -1);
				log.get(0).setAuthentication_id(employee.getAuthentication_id());
				log.get(0).setEmail(encEmail);
				commonDao.updateDataToDb(log.get(0));
				response.put("status", "Success");
				response.put("message", "Employee Details Updated Successfully");
			}else {
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("email", encEmail);
				map.put("mobile_number", employee.getMobile_number());
				List<Employee> data = (List<Employee>)commonDao.getDataByMapOr(map, new Employee(), null, null, 0, -1);
				if(data.size() > 0) {
					response.put("status", "Already_Exist");
					response.put("message", "Employee Details Already Exist");
				}else {
					String pass = Utils.generateRandomPassword(8);
					String encPassword = encriptionData.encrypt(pass);
					
					employee.setEmail(encEmail);
					employee.setStatus("Active");
					employee.setCtreatedAt(new Date());
					int i = commonDao.addDataToDb(employee);
					if(i > 0) {
						String decemail = encriptionData.decrypt(encEmail);
						String decpas = encriptionData.decrypt(encPassword);
						LoginCredentials login = new LoginCredentials();
						login.setEmail(encEmail);
						login.setPassword(encPassword);
						login.setAuthentication_id(employee.getAuthentication_id());
						login.setEmployee_id(i);
						login.setUser_type("Employee");
						login.setStatus("Active");
						login.setCreatedAt(new Date());
						commonDao.addDataToDb(login);
						String loginURL ="https://crm.haliconpub.com/";
			            String subject = "Access Credentials for Your Employee Account";
						String message =
							    "<!DOCTYPE html>" +
							    "<html><head>" +
							    "<style>" +
							    "  body { font-family: Arial, sans-serif; line-height: 1.6; }" +
							    "  .container { max-width: 600px; padding: 20px; border: 1px solid #ddd; border-radius: 8px; margin: auto; }" +
							    "  h2 { color: #2c3e50; text-align: center; }" +
							    "  .highlight { font-weight: bold; color: #333; }" +
							    "  .footer { margin-top: 20px; font-size: 14px; color: #555; text-align: center; }" +
							    "  .logo { display: block; margin: 0 auto 20px; max-height: 80px; }" +
							    "</style>" +
							    "</head><body>" +
							    "<div class='container'>" +
							    "<img src='https://haliconpub.com/assets/img/hlogo.png' alt='Company Logo' class='logo'>" +
							    "<h2>Welcome to Halicon Publication</h2>" +
							    "<p>Dear <span class='highlight'>" +employee.getEmployee_name()+ "</span>,</p>" +
							    "<p>Your login credentials have been successfully created. Below are your details:</p>" +
							    "<table>" +
							    "  <tr><td class='highlight'>Username:</td><td>" + decemail + "</td></tr>" +
							    "  <tr><td class='highlight'>Password:</td><td>" + decpas + "</td></tr>" +
							    "</table>" +
							    "<p>You can log in to the portal using the following link:</p>" +
							    "<p><a href='" + loginURL + "'>" + loginURL + "</a></p>" +
							    "<p>We recommend changing your password after your first login for security purposes.</p>" +
							    "<p>If you have any questions or issues, feel free to reach out to the IT support team.</p>" +
							    "<p>Best regards,<br/>" +
							    "HR Team<br/>" +
							    "Halicon Publication</p>" +
							    "<div class='footer'>" +
							    "This is an automated email. Please do not reply directly." +
							    "</div>" +
							    "</div></body></html>";
						

						emailService.sendEmailMessage(decemail, subject, message);
						
						response.put("status", "Success");
						response.put("message", "Employee Details Added Successfully");
					}else {
						response.put("status", "Failed");
						response.put("message", "Internal Server Error");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong"+e);
		}
		return response;
	}

	public Map<String, Object> get_employee(int start, int length,String search, String employee_id) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			if(Integer.parseInt(employee_id) > 0) {
				map.put("sno", Integer.parseInt(employee_id));
			}
			Map<String, Object> mapor = new HashMap<String,Object>();
			mapor.put("employee_name", search);
			mapor.put("email", search);
			mapor.put("mobile_number", search);
			List<Employee> data = (List<Employee>) commonDao.getDataByMapSearchAnd(map,mapor, new Employee(), "sno", "desc", start, length);
			int count = commonDao.getDataByMapSearchAndSize(map,mapor, new Employee(), "sno", "desc");
			if(data.size() >0) {
				for(Employee e : data) {
					Map<String, Object> map1 = new HashMap<String,Object>();
					map1.put("employee_id", e.getSno());
					List<LoginCredentials > login = (List<LoginCredentials>)commonDao.getDataByMap(map1, new LoginCredentials(), null, null, 0, -1);
					String decpass = encriptionData.decrypt(login.get(0).getPassword());
					String deceml = encriptionData.decrypt(login.get(0).getEmail());
					e.setPassword(decpass);
					e.setEmail(deceml);
				}
				response.put("status", "Success");
				response.put("message", "Data Fetched Successfully");
				response.put("data", data);
				response.put("recordsFiltered", count);
				response.put("recordsTotal", count);
			}else {
				response.put("status", "Failed");
				response.put("message", "No Data Found");
				response.put("data", new ArrayList<>());
				response.put("recordsFiltered", 0);
				response.put("recordsTotal", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong"+e);
		}
		return response;
	}

	public Map<String, Object> get_empById(String sno) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<Employee> data = (List<Employee>)commonDao.getDataByMap(map, new Employee(), null, null, 0, -1);
			if(data.size() >0) {
				String deceml = encriptionData.decrypt(data.get(0).getEmail());
				data.get(0).setEmail(deceml);
				Map<String, Object> mp = new HashMap<String,Object>();
				mp.put("employee_id", Integer.parseInt(sno));
				List<LoginCredentials> log = (List<LoginCredentials>)commonDao.getDataByMap(mp, new LoginCredentials(), null, null, 0, -1);
				data.get(0).setAuthentication_id(log.get(0).getAuthentication_id());
				
				response.put("status", "Success");
				response.put("data", data);
				response.put("message", "Data Fetched Successfully");
			}else {
				response.put("status", "Failed");
				response.put("message", "No Data Found");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong"+e);
		}
		return response;
	}
}

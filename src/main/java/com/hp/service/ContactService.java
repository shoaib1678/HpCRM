package com.hp.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.dao.CommonDao;
import com.hp.model.ContactDetails;
import com.hp.model.ContactRemarks;
import com.hp.utils.EncriptionData;

@Service
public class ContactService {

	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;

	public Map<String, Object> add_contact(ContactDetails contactDetails) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", contactDetails.getSno());
			List<ContactDetails> data = (List<ContactDetails>)commonDao.getDataByMap(map, new ContactDetails(), null, null, 0, -1);
			if(data.size() >0) {
				String enc = encriptionData.encrypt(contactDetails.getContact_number());
				data.get(0).setClient_name(contactDetails.getClient_name());
				data.get(0).setContact_number(enc);
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Data Updated Successfully");
			}else {
				Map<String, Object> map1 = new HashMap<String,Object>();
				map1.put("employee_id", contactDetails.getEmployee_id());
				map1.put("contact_number", contactDetails.getContact_number());
				List<ContactDetails> data1 = (List<ContactDetails>)commonDao.getDataByMap(map1, new ContactDetails(), null, null, 0, -1);
				if(data1.size() > 0) {
					response.put("status", "Already_Exist");
					response.put("message", "Employee Details Already Exist");
				}else {
					String enc = encriptionData.encrypt(contactDetails.getContact_number());
					contactDetails.setContact_number(enc);
					contactDetails.setStatus("Pending");
					contactDetails.setCreatedAt(new Date());
					int i = commonDao.addDataToDb(contactDetails);
					if(i>0){
						response.put("status", "Success");
						response.put("message", "Contact details Added Successfully");
					}else {
						response.put("status", "Failed");
						response.put("message", "Internal server error");
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

	public Map<String, Object> get_contact(int start, int length, String search, String employee_id, String status) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			if(Integer.parseInt(employee_id) > 0) {
				map.put("sno", Integer.parseInt(employee_id));
			}
			map.put("status", status);
			Map<String, Object> mapor = new HashMap<String,Object>();
			mapor.put("contact_number", search);
			mapor.put("client_name", search);
			List<ContactDetails> data = (List<ContactDetails>) commonDao.getDataByMapSearchAnd(map,mapor, new ContactDetails(), "sno", "desc", start, length);
			int count = commonDao.getDataByMapSearchAndSize(map,mapor, new ContactDetails(), "sno", "desc");
			if(data.size() >0) {
				for(ContactDetails c: data) {
					Map<String, Object> mp = new HashMap<String,Object>();
					mp.put("sno", c.getSno());
					List<ContactDetails> cd = (List<ContactDetails>)commonDao.getDataByMap(mp, new ContactDetails(), null, null, 0, -1);
					String decc = encriptionData.decrypt(cd.get(0).getContact_number());
					c.setContact_number(decc);
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

	public Map<String, Object> add_remarks(ContactRemarks crRemarks) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			 LocalTime now = LocalTime.now();
		     String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		     crRemarks.setConnected_time(currentTime);
		     crRemarks.setRemarksDate(new Date());
		     int i = commonDao.addDataToDb(crRemarks);
		     if(i > 0) {
		    	 response.put("status", "Success");
		    	 response.put("message", "Remarks Added Successfully");
		     }else {
		    	 response.put("status", "Failed");
		    	 response.put("message", "Internal server error"); 
		     }
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong"+e);
		}
		return response;
	}
}

package com.hp.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.dao.CommonDao;
import com.hp.model.ContactDetails;
import com.hp.model.ContactRemarks;
import com.hp.model.ConvertedModule;
import com.hp.model.Employee;
import com.hp.model.InactiveClient;
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
				if(contactDetails.getEmail() != null && !contactDetails.getEmail().isEmpty()) {
					String eml = encriptionData.encrypt(contactDetails.getEmail());
					data.get(0).setEmail(eml);
				}
				
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
					String eml = encriptionData.encrypt(contactDetails.getEmail());
					contactDetails.setContact_number(enc);
					contactDetails.setEmail(eml);
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

	public Map<String, Object> get_contact(int start, int length, String search, String employee_id, String status, String module) {
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
			List<ContactDetails> list = new ArrayList<ContactDetails>();
			if(data.size() >0) {
				for(ContactDetails c: data) {
					String decc = encriptionData.decrypt(c.getContact_number());
					c.setContact_number(decc);
					if(c.getEmail() != null && !c.getEmail().isEmpty()) {
						String eml = encriptionData.decrypt(c.getEmail());
						c.setEmail(eml);
					}
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

	public Map<String, Object> update_status(String sno, String status, String remarks, String module, String employee_id) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			 LocalTime now = LocalTime.now();
		     String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
			ContactRemarks rem = new ContactRemarks();
			Map<String, Object> map = new HashMap<String,Object>();
			if(status.equalsIgnoreCase("Connected")) {
				map.put("sno", Integer.parseInt(sno));
				List<ContactDetails> cd = (List<ContactDetails>)commonDao.getDataByMap(map, new ContactDetails(), null, null, 0, -1);
				cd.get(0).setStatus(status);
				commonDao.updateDataToDb(cd.get(0));
				rem.setContact_id(Integer.parseInt(sno));
				rem.setEmployee_id(Integer.parseInt(employee_id));
				rem.setRemarks(remarks);
				rem.setConnected_time(currentTime);
				rem.setRemarksDate(new Date());
				commonDao.addDataToDb(rem);
				 response.put("status", "Success");
		    	 response.put("message", "Status Updated Successfully");
		    	 return response;
			}else if(status.equalsIgnoreCase("Converted")) {
				map.put("sno", Integer.parseInt(sno));
				List<ContactDetails> cd = (List<ContactDetails>)commonDao.getDataByMap(map, new ContactDetails(), null, null, 0, -1);
				cd.get(0).setModule(module);
				cd.get(0).setStatus(status);
				commonDao.updateDataToDb(cd.get(0));
				rem.setContact_id(Integer.parseInt(sno));
				rem.setEmployee_id(Integer.parseInt(employee_id));
				rem.setRemarks(remarks);
				rem.setConnected_time(currentTime);
				rem.setRemarksDate(new Date());
				commonDao.addDataToDb(rem);
				ConvertedModule con = new ConvertedModule();
				String[] mod = module.split(",");
				for(int i=0; i< mod.length; i++) {
					con.setContact_id(cd.get(0).getSno());
					con.setModule(mod[i]);
					con.setStatus(status);
					con.setCreatedAt(new Date());
					commonDao.addDataToDb(con);
				}
				response.put("status", "Success");
		    	response.put("message", "Status Updated Successfully");
		    	return response;
			}else {
				InactiveClient cl = new InactiveClient();
				Map<String,Object> mm = new HashMap<String, Object>();
				mm.put("contact_id", Integer.parseInt(sno));
				List<InactiveClient> inc = (List<InactiveClient>)commonDao.getDataByMap(mm, new InactiveClient(), null, null, 0, -1);
				if(inc != null) {
					if(inc.size() < 3) {
						map.put("sno", Integer.parseInt(sno));
						List<ContactDetails> cd = (List<ContactDetails>)commonDao.getDataByMap(map, new ContactDetails(), null, null, 0, -1);
						if(inc.size() != 2) {
							cd.get(0).setStatus(status);
						}else {
							cd.get(0).setStatus("Inactive");
						}
						commonDao.updateDataToDb(cd.get(0));
						cl.setContact_id(Integer.parseInt(sno));
						cl.setTime(currentTime);
						cl.setDate(new Date());
						commonDao.addDataToDb(cl);
						response.put("status", "Success");
				    	 response.put("message", "Status Updated Successfully");
				    	 return response;
					}
				}else {
					map.put("sno", Integer.parseInt(sno));
					List<ContactDetails> cd = (List<ContactDetails>)commonDao.getDataByMap(map, new ContactDetails(), null, null, 0, -1);
					cd.get(0).setStatus(status);
					commonDao.updateDataToDb(cd.get(0));
					cl.setContact_id(Integer.parseInt(sno));
					cl.setTime(currentTime);
					cl.setDate(new Date());
					commonDao.addDataToDb(cl);
					response.put("status", "Success");
			    	 response.put("message", "Status Updated Successfully");
			    	 return response;
				}
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong"+e);
		}
		return response;
	}

	public Map<String, Object> get_remarks(int start, int length, String search, String employee_id,
			String contact_id) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			if(Integer.parseInt(employee_id) > 0) {
				map.put("sno", Integer.parseInt(employee_id));
			}
			map.put("contact_id", Integer.parseInt(contact_id));
			Map<String, Object> mapor = new HashMap<String,Object>();
			
			List<ContactRemarks> data = (List<ContactRemarks>) commonDao.getDataByMapSearchAnd(map,mapor, new ContactRemarks(), "sno", "desc", start, length);
			int count = commonDao.getDataByMapSearchAndSize(map,mapor, new ContactRemarks(), "sno", "desc");
			if(data.size() >0) {
				for(ContactRemarks c: data) {
					Map<String, Object> mp = new HashMap<String,Object>();
					mp.put("sno", c.getEmployee_id());
					List<Employee> cd = (List<Employee>)commonDao.getDataByMap(mp, new Employee(), null, null, 0, -1);
					if(cd.size() > 0) {
						c.setEmployee_name(cd.get(0).getEmployee_name());
					}else {
						c.setEmployee_name("Admin");
					}
					
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

	public Map<String, Object> edit_contact(String sno) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<ContactDetails> data = (List<ContactDetails>)commonDao.getDataByMap(map, new ContactDetails(), null, null, 0, -1);
			if(data.size() >0) {
				String con = encriptionData.decrypt(data.get(0).getContact_number());
				String eml = encriptionData.decrypt(data.get(0).getEmail());
				data.get(0).setContact_number(con);
				data.get(0).setEmail(eml);
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

	public Map<String, Object> get_inactive(String contact_id) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("contact_id", Integer.parseInt(contact_id));
			List<InactiveClient> data = (List<InactiveClient>)commonDao.getDataByMap(map, new InactiveClient(), "sno", "asc", 0, -1);
			if(data.size() >0) {
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

	public Map<String, Object> get_converted_contact(int start, int length, String search, String employee_id,
			String status, String module) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("employee_id", Integer.parseInt(employee_id));
			map.put("status", status);
			map.put("module", module);
			List<ConvertedModule> conv = (List<ConvertedModule>)commonDao.getDataByMap(map, new ConvertedModule(), null, null, 0, -1);
			List<ContactDetails> con = new ArrayList<ContactDetails>();
			int count =0;
			if(conv.size() > 0) {
				for(ConvertedModule c: conv) {
					Map<String, Object> mp = new HashMap<String, Object>();
					Map<String, Object> mpor = new HashMap<String, Object>();
					mp.put("sno", c.getContact_id());
					List<ContactDetails> contactDetails = (List<ContactDetails>)commonDao.getDataByMapSearchAnd(mp,mpor, new ContactDetails(), "sno", "desc", start, length);
					count = commonDao.getDataByMapSearchAndSize(mp,mpor, new ContactDetails(), "sno", "desc");
					con.addAll(contactDetails);
				}
			}
			if(con.size() >0) {
				for(ContactDetails c: con) {
					String decc = encriptionData.decrypt(c.getContact_number());
					c.setContact_number(decc);
					if(c.getEmail() != null && !c.getEmail().isEmpty()) {
						String eml = encriptionData.decrypt(c.getEmail());
						c.setEmail(eml);
					}
				}
				response.put("status", "Success");
				response.put("message", "Data Fetched Successfully");
				response.put("data", con);
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
}

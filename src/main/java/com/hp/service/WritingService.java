package com.hp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hp.dao.CommonDao;
import com.hp.model.ContactDetails;
import com.hp.model.ConvertedModule;
import com.hp.model.Payment;
import com.hp.model.WritingDetails;
import com.hp.utils.EncriptionData;
import com.hp.utils.Utils;

@Service
public class WritingService {

	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;

	public Map<String, Object> add_writing_details(WritingDetails writingDetails) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", writingDetails.getSno());
			List<WritingDetails> data = (List<WritingDetails>)commonDao.getDataByMap(map, new WritingDetails(), null, null, 0, -1);
			if(data.size() >0) {
				data.get(0).setWriting_type(writingDetails.getWriting_type());
				data.get(0).setTitle(writingDetails.getTitle());
				data.get(0).setArticle_id(writingDetails.getArticle_id());
				data.get(0).setAmount(writingDetails.getAmount());
				commonDao.updateDataToDb(data.get(0));
				Map<String, Object> map1 = new HashMap<String,Object>();
				map.put("ad_id", writingDetails.getSno());
				map.put("module", "Writing Paper");
				List<Payment> p = (List<Payment>)commonDao.getDataByMap(map1, new Payment(), null, null, 0, -1);
				p.get(0).setTotal_amount(writingDetails.getAmount());
				p.get(0).setRemaining_amount(writingDetails.getAmount());
				commonDao.updateDataToDb(p.get(0));
				response.put("status", "Success");
				response.put("message", "Data Updated Successfully");
			}else {
				Map<String, Object> map1 = new HashMap<String,Object>();
				map1.put("article_id", writingDetails.getArticle_id());
				List<WritingDetails> data1 = (List<WritingDetails>)commonDao.getDataByMap(map1, new WritingDetails(), null, null, 0, -1);
				if(data1.size() > 0) {
					response.put("status", "Already_Exist");
					response.put("message", "Article ID Already Exist");
				}else {
					writingDetails.setStatus("Pending");
					writingDetails.setCreatedAt(new Date());
					int i = commonDao.addDataToDb(writingDetails);
					if(i>0){
						Payment pay = new Payment();
						pay.setAd_id(i);
						pay.setEmployee_id(writingDetails.getEmployee_id());
						pay.setTotal_amount(writingDetails.getAmount());
						pay.setRemaining_amount(writingDetails.getAmount());
						pay.setPaid_amount(0);
						pay.setModule("Writing Paper");
						pay.setPayment_status("Pending");
						commonDao.addDataToDb(pay);
						Map<String, Object> mpp = new HashMap<String, Object>();
						mpp.put("contact_id", writingDetails.getContact_id());
						mpp.put("module", "Writing Paper");
						mpp.put("status", "Converted");
						List<ConvertedModule> con = (List<ConvertedModule>)commonDao.getDataByMap(mpp, new ConvertedModule(), null, null, 0, -1);
						con.get(0).setStatus("Active");
						commonDao.updateDataToDb(con.get(0));
						response.put("status", "Success");
						response.put("message", "Writing Paper Details Added Successfully");
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

	public Map<String, Object> get_writing_details(int start, int length, String search, String employee_id, String status) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			Map<String, Object> mpp = new HashMap<String,Object>();
			if(Integer.parseInt(employee_id) > 0) {
				map.put("employee_id", Integer.parseInt(employee_id));
			}
			map.put("status", status);
			Map<String, Object> mapor = new HashMap<String,Object>();
			mapor.put("article_id", search);
			mapor.put("title", search);
			List<WritingDetails> data = (List<WritingDetails>) commonDao.getDataByMapSearchAnd(map,mapor, new WritingDetails(), "sno", "desc", start, length);
			int count = commonDao.getDataByMapSearchAndSize(map,mapor, new WritingDetails(), "sno", "desc");
			if(data.size() >0) {
				for(WritingDetails c: data) {
					Map<String, Object> mapr = new HashMap<String,Object>();
					mapr.put("sno", c.getContact_id());
					List<ContactDetails> con = (List<ContactDetails>)commonDao.getDataByMap(mapr, new ContactDetails(), null, null, 0, -1);
						String decc = encriptionData.decrypt(con.get(0).getContact_number());
						c.setContact_number(decc);
						String eml = encriptionData.decrypt(con.get(0).getEmail());
						c.setEmail(eml);
						if(c.getStatus().equalsIgnoreCase("Received") 
								|| c.getStatus().equalsIgnoreCase("Partially Paid") 
								|| c.getStatus().equalsIgnoreCase("Sent Confirmation") 
								|| c.getStatus().equalsIgnoreCase("Confirmed") 
								|| c.getStatus().equalsIgnoreCase("Full Work Confirmation") 
								|| c.getStatus().equalsIgnoreCase("Completed") 
								|| c.getStatus().equalsIgnoreCase("Paid")) {
							mpp.put("ad_id", c.getSno());
							mpp.put("module","Writing Paper");
							List<Payment> pay  = (List<Payment>)commonDao.getDataByMap(mpp, new Payment(), null, null, 0, -1);
							if(pay.size() > 0) {
								c.setPaid_amount(pay.get(0).getPaid_amount());
								c.setRemaining_amount(pay.get(0).getRemaining_amount());
								c.setPayment_mode(pay.get(0).getPayment_mode());
								c.setPayment_status(pay.get(0).getPayment_status());
							}
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

	public Map<String, Object> add_subject_area(String subject_area, String sno) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<WritingDetails> data = (List<WritingDetails>)commonDao.getDataByMap(map, new WritingDetails(), null, null, 0, -1);
			if(data.size() > 0) {
				data.get(0).setSubject_area(subject_area);
				data.get(0).setStatus("Active");
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Subject Area Added Successfully");
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

	public Map<String, Object> writing_recieved_payment(String sno) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<WritingDetails> data = (List<WritingDetails>)commonDao.getDataByMap(map, new WritingDetails(), null, null, 0, -1);
			if(data.size() > 0) {
				data.get(0).setStatus("Received");
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Payment Received Successfully");
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
	
	public Map<String, Object> sent_data_confirm(String sno, String status) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<WritingDetails> data = (List<WritingDetails>)commonDao.getDataByMap(map, new WritingDetails(), null, null, 0, -1);
			if(data.size() > 0) {
				data.get(0).setStatus(status);
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Confirmation Sent Successfully");
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

	public Map<String, Object> add_writing_file(String sno, String status, MultipartFile file) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<WritingDetails> data = (List<WritingDetails>)commonDao.getDataByMap(map, new WritingDetails(), null, null, 0, -1);
			if(data.size() > 0) {
				Utils utils = new Utils();
				String f = utils.uploadImage(file);
				data.get(0).setWriting_file(f);
				data.get(0).setStatus(status);
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Successfully");
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

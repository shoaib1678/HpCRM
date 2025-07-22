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
import org.springframework.web.multipart.MultipartFile;

import com.hp.dao.CommonDao;
import com.hp.model.ArticleDetails;
import com.hp.model.ContactDetails;
import com.hp.model.ContactRemarks;
import com.hp.model.Payment;
import com.hp.model.PublicationDetails;
import com.hp.utils.EncriptionData;
import com.hp.utils.Utils;

@Service
public class ArticleService {

	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;

	public Map<String, Object> add_articledetails(ArticleDetails articleDetails, MultipartFile file) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Utils utils = new Utils();
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", articleDetails.getSno());
			List<ArticleDetails> data = (List<ArticleDetails>)commonDao.getDataByMap(map, new ArticleDetails(), null, null, 0, -1);
			if(data.size() >0) {
				if(file != null  && !file.isEmpty()) {
					String fi = utils.uploadImage(file);
					data.get(0).setFile(fi);
				}
				
				String enc = encriptionData.encrypt(articleDetails.getContact_number());
				String eml = encriptionData.encrypt(articleDetails.getEmail());
				data.get(0).setClient_name(articleDetails.getClient_name());
				data.get(0).setContact_number(enc);
				data.get(0).setEmail(eml);
				data.get(0).setJournal_name(articleDetails.getJournal_name());
				data.get(0).setEmail(eml);
				data.get(0).setArticle_id(articleDetails.getArticle_id());
				data.get(0).setArticle_title(articleDetails.getArticle_title());
				data.get(0).setDealed_amount(articleDetails.getDealed_amount());
				data.get(0).setAffilliation(articleDetails.getAffilliation());
				commonDao.updateDataToDb(data.get(0));
				Map<String, Object> map1 = new HashMap<String,Object>();
				map.put("ad_id", articleDetails.getSno());
				List<Payment> p = (List<Payment>)commonDao.getDataByMap(map1, new Payment(), null, null, 0, -1);
				p.get(0).setTotal_amount(articleDetails.getDealed_amount());
				p.get(0).setRemaining_amount(articleDetails.getDealed_amount());
				commonDao.updateDataToDb(p.get(0));
				response.put("status", "Success");
				response.put("message", "Data Updated Successfully");
			}else {
				Map<String, Object> map1 = new HashMap<String,Object>();
				map1.put("employee_id", articleDetails.getEmployee_id());
				map1.put("article_id", articleDetails.getContact_number());
				List<ArticleDetails> data1 = (List<ArticleDetails>)commonDao.getDataByMap(map1, new ArticleDetails(), null, null, 0, -1);
				if(data1.size() > 0) {
					response.put("status", "Already_Exist");
					response.put("message", "Article Id Already Exist");
				}else {
					String enc = encriptionData.encrypt(articleDetails.getContact_number());
					String eml = encriptionData.encrypt(articleDetails.getEmail());
					articleDetails.setContact_number(enc);
					articleDetails.setEmail(eml);
					if(file != null  && !file.isEmpty()) {
						String fi = utils.uploadImage(file);
						articleDetails.setFile(fi);
					}
					articleDetails.setStatus("Active");
					articleDetails.setCreatedAt(new Date());
					int i = commonDao.addDataToDb(articleDetails);
					if(i>0){
						Payment pay = new Payment();
						pay.setAd_id(i);
						pay.setEmployee_id(articleDetails.getEmployee_id());
						pay.setTotal_amount(articleDetails.getDealed_amount());
						pay.setRemaining_amount(articleDetails.getDealed_amount());
						pay.setPaid_amount(0);
						pay.setPayment_status("Pending");
						commonDao.addDataToDb(pay);
						Map<String, Object> mapp = new HashMap<String,Object>();
						mapp.put("sno", articleDetails.getContact_id());
						List<ContactDetails> cid = (List<ContactDetails>)commonDao.getDataByMap(mapp, new ContactDetails(), null, null, 0, -1);
						cid.get(0).setStatus("Active");
						commonDao.updateDataToDb(cid.get(0));
						response.put("status", "Success");
						response.put("message", "Article details Added Successfully");
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

	public Map<String, Object> get_articledetails(int start, int length, String search, String employee_id,
			String status) {
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
			mapor.put("article_id", search);
			mapor.put("article_title", search);
			mapor.put("email", search);
			List<ArticleDetails> data = (List<ArticleDetails>) commonDao.getDataByMapSearchAnd(map,mapor, new ArticleDetails(), "sno", "desc", start, length);
			int count = commonDao.getDataByMapSearchAndSize(map,mapor, new ArticleDetails(), "sno", "desc");
			if(data.size() >0) {
				for(ArticleDetails c: data) {
					Map<String, Object> mp = new HashMap<String,Object>();
					Map<String, Object> mpp = new HashMap<String,Object>();
					mp.put("sno", c.getSno());
					List<ArticleDetails> cd = (List<ArticleDetails>)commonDao.getDataByMap(mp, new ArticleDetails(), null, null, 0, -1);
					String decc = encriptionData.decrypt(cd.get(0).getContact_number());
					String eml = encriptionData.decrypt(cd.get(0).getEmail());
					c.setContact_number(decc);
					c.setEmail(eml);
					if(c.getStatus().equalsIgnoreCase("Received") || c.getStatus().equalsIgnoreCase("Partially Paid") || c.getStatus().equalsIgnoreCase("Proved") || c.getStatus().equalsIgnoreCase("Paid")) {
						mpp.put("ad_id", c.getSno());
						List<Payment> pay  = (List<Payment>)commonDao.getDataByMap(mpp, new Payment(), null, null, 0, -1);
						if(pay.size() > 0) {
							c.setPaid_amount(pay.get(0).getPaid_amount());
							c.setRemaining_amount(pay.get(0).getRemaining_amount());
							c.setPayment_mode(pay.get(0).getPayment_mode());
							c.setPayment_status(pay.get(0).getPayment_status());
						}
					}
					if(c.getStatus().equalsIgnoreCase("Published")) {
						mpp.put("ad_id", c.getSno());
						List<PublicationDetails> pd = (List<PublicationDetails>)commonDao.getDataByMap(mpp, new PublicationDetails(), null, null, 0, -1);
						c.setUrl(pd.get(0).getPublication_url());
						c.setPdf(pd.get(0).getPublication_pdf());
						c.setPublication_date(pd.get(0).getPublication_date());
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

	public Map<String, Object> edit_articledetails(String sno) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<ArticleDetails> data = (List<ArticleDetails>)commonDao.getDataByMap(map, new ArticleDetails(), null, null, 0, -1);
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

	public Map<String, Object> send_foracceptance(String sno, MultipartFile file, String status) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<ArticleDetails> data = (List<ArticleDetails>)commonDao.getDataByMap(map, new ArticleDetails(), null, null, 0, -1);
			if(data.size() >0) {
				Utils utils = new Utils();
				if(file != null && !file.isEmpty()) {
					String fi = utils.uploadImage(file);
					data.get(0).setAcceptance_file(fi);
				}
				data.get(0).setStatus(status);
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Sent Successfully");
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

	public Map<String, Object> add_gallery_proof(ArticleDetails art) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", art.getSno());
			List<ArticleDetails> data = (List<ArticleDetails>)commonDao.getDataByMap(map, new ArticleDetails(), null, null, 0, -1);
			if(data.size() >0) {
				data.get(0).setGallery_proof_date(art.getGallery_proof_date());
				data.get(0).setStatus("Proved");
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Gallery Proof date added Successfully");
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

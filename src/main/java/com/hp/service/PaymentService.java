package com.hp.service;

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
import com.hp.model.AuthorshipDetails;
import com.hp.model.ContactDetails;
import com.hp.model.Payment;
import com.hp.model.PaymentReceipt;
import com.hp.model.ProjectDetails;
import com.hp.model.WritingDetails;
import com.hp.utils.EncriptionData;
import com.hp.utils.Utils;

@Service
public class PaymentService {

	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;

	public Map<String, Object> add_payment(Payment payment, MultipartFile file) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			float remamt = 0f;
			Utils utils = new Utils();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ad_id", payment.getAd_id());
			map.put("module", payment.getModule());
			List<Payment> pa = (List<Payment>)commonDao.getDataByMap(map, new Payment(), null, null, 0, -1);
			if(pa.size() > 0) {
				remamt = pa.get(0).getRemaining_amount()-payment.getPaid_amount();
				if(remamt > 0) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("sno", payment.getAd_id());
					if(payment.getModule().equalsIgnoreCase("Publication")) {
						List<ArticleDetails> ad = (List<ArticleDetails>)commonDao.getDataByMap(map1, new ArticleDetails(), null, null, 0, -1);
						if(ad.get(0).getStatus().equalsIgnoreCase("Proved")) {
							ad.get(0).setStatus("Proved");
							commonDao.updateDataToDb(ad.get(0));
						}else {
							pa.get(0).setPayment_status("Partially Paid");
							ad.get(0).setStatus("Partially Paid");
							commonDao.updateDataToDb(ad.get(0));
						}
					}else if(payment.getModule().equalsIgnoreCase("Writing Paper")) {
						List<WritingDetails> ad = (List<WritingDetails>)commonDao.getDataByMap(map1, new WritingDetails(), null, null, 0, -1);
						if(ad.get(0).getStatus().equalsIgnoreCase("Initiated")) {
							ad.get(0).setStatus("Initiated");
							commonDao.updateDataToDb(ad.get(0));
						}else {
							pa.get(0).setPayment_status("Partially Paid");
							ad.get(0).setStatus("Confirmed");
							commonDao.updateDataToDb(ad.get(0));
						}
					}else {
						List<AuthorshipDetails> ad = (List<AuthorshipDetails>)commonDao.getDataByMap(map1, new AuthorshipDetails(), null, null, 0, -1);
						if(payment.getPayment_type().equalsIgnoreCase("Remaining")) {
							ad.get(0).setStatus("Partially");
							commonDao.updateDataToDb(ad.get(0));
						}else if(payment.getPayment_type().equalsIgnoreCase("Full")) {
							ad.get(0).setStatus("Proved");
							commonDao.updateDataToDb(ad.get(0));
						}else{
							pa.get(0).setPayment_status("Partially Paid");
							ad.get(0).setStatus("Initiated");
							commonDao.updateDataToDb(ad.get(0));
						}
					}
				}else{
					if(payment.getModule().equalsIgnoreCase("Publication")) {
						pa.get(0).setPayment_status("Paid");
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("sno", payment.getAd_id());
						List<ArticleDetails> art = (List<ArticleDetails>)commonDao.getDataByMap(map1, new ArticleDetails(), null, null, 0, -1);
						art.get(0).setStatus("Paid");
						commonDao.updateDataToDb(art.get(0));
					}else if(payment.getModule().equalsIgnoreCase("Writing Paper")) {
						pa.get(0).setPayment_status("Paid");
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("sno", payment.getAd_id());
						List<WritingDetails> art = (List<WritingDetails>)commonDao.getDataByMap(map1, new WritingDetails(), null, null, 0, -1);
						art.get(0).setStatus("Paid");
						commonDao.updateDataToDb(art.get(0));
					}else {
						if(payment.getPayment_type().equalsIgnoreCase("Remaining")) {
							pa.get(0).setPayment_status("Paid");
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("sno", payment.getAd_id());
							List<AuthorshipDetails> art = (List<AuthorshipDetails>)commonDao.getDataByMap(map1, new AuthorshipDetails(), null, null, 0, -1);
							art.get(0).setStatus("Proved");
							commonDao.updateDataToDb(art.get(0));
						}else if(payment.getPayment_type().equalsIgnoreCase("Full")) {
							pa.get(0).setPayment_status("Paid");
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("sno", payment.getAd_id());
							List<AuthorshipDetails> art = (List<AuthorshipDetails>)commonDao.getDataByMap(map1, new AuthorshipDetails(), null, null, 0, -1);
							art.get(0).setStatus("Completed");
							commonDao.updateDataToDb(art.get(0));
						}
						
					}
					
				}
				pa.get(0).setPaid_amount(pa.get(0).getPaid_amount() + payment.getPaid_amount());
				pa.get(0).setPayment_mode(payment.getPayment_mode());
				pa.get(0).setRemaining_amount(remamt);
				commonDao.updateDataToDb(pa.get(0));
				PaymentReceipt pr = new PaymentReceipt();
				pr.setTransaction_id(payment.getTransaction_id());
				if(file!= null && !file.isEmpty()) {
					String fi = utils.uploadImage(file);
					pr.setPayment_id(pa.get(0).getSno());
					pr.setReceipt(fi);
					pr.setCreatedAt(new Date());
					commonDao.addDataToDb(pr);
				}
				response.put("status", "Success");
				response.put("message", "Payment Added Successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong"+e);
		}
		return response;
	}

	public Map<String, Object> get_payment(int start, int length, String search, String employee_id, String status, String from_date, String to_date) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			Map<String, Object> mpp = new HashMap<String,Object>();
			if(Integer.parseInt(employee_id) > 0) {
				map.put("employee_id", Integer.parseInt(employee_id));
			}
			if(status != null && !status.isEmpty()) {
				map.put("payment_status", status);
			}
			Map<String, Object> mapor = new HashMap<String,Object>();
			mapor.put("payment_status", search);
			//mapor.put("title", search);
			List<Payment> data = (List<Payment>) commonDao.getDataByMapWithDateRange(map,mapor, new Payment(), "sno", "desc", start, length,from_date,to_date);
			int count = commonDao.getDataByMapWithDateRangeSize(map,mapor, new Payment(), "sno", "desc",from_date,to_date);
			if(data.size() >0) {
				for(Payment p: data) {
					Map<String, Object> mapr = new HashMap<String,Object>();
					mapr.put("sno", p.getContact_id());
					List<ContactDetails> con = (List<ContactDetails>)commonDao.getDataByMap(mapr, new ContactDetails(), null, null, 0, -1);
					String decemail = encriptionData.decrypt(con.get(0).getEmail());
					String deccont = encriptionData.decrypt(con.get(0).getContact_number());
					p.setContact_number(deccont);	
					p.setEmail(decemail);	
					Map<String, Object> mp = new HashMap<String,Object>();
					mp.put("sno", p.getAd_id());
					if(p.getModule().equalsIgnoreCase("Publication")) {
						List<ArticleDetails> ad = (List<ArticleDetails>)commonDao.getDataByMap(mp, new ArticleDetails(), null, null, 0, -1);
						p.setArticle_id(ad.get(0).getArticle_id());
						p.setTitle(ad.get(0).getArticle_title());
						p.setJournal_name(ad.get(0).getJournal_name());
					}else if(p.getModule().equalsIgnoreCase("Writing Paper")) {
						List<WritingDetails> ad = (List<WritingDetails>)commonDao.getDataByMap(mp, new WritingDetails(), null, null, 0, -1);
						p.setArticle_id(ad.get(0).getArticle_id());
						p.setTitle(ad.get(0).getTitle());
						p.setJournal_name("NA");
					}else {
						List<AuthorshipDetails> ad = (List<AuthorshipDetails>)commonDao.getDataByMap(mp, new AuthorshipDetails(), null, null, 0, -1);
						p.setArticle_id(ad.get(0).getAuthor_id());
						p.setTitle(ad.get(0).getTitle());
						p.setJournal_name(ad.get(0).getJournal_name());
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
}

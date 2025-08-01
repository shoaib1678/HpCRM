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
import com.hp.model.AuthorshipDetails;
import com.hp.model.Authorship_Article;
import com.hp.model.Authorship_Position;
import com.hp.model.ContactDetails;
import com.hp.model.ContactRemarks;
import com.hp.model.ConvertedModule;
import com.hp.model.Payment;
import com.hp.utils.EncriptionData;
import com.hp.utils.Utils;

@Service
public class AuthorshipService {

	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;

	public Map<String, Object> add_author(AuthorshipDetails ad) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> mapdata = new HashMap<String,Object>();
			mapdata.put("contact_id", ad.getContact_id());
			mapdata.put("position_id", ad.getPosition_id());
			mapdata.put("aa_id", ad.getAa_id());
			List<AuthorshipDetails> aad = (List<AuthorshipDetails>)commonDao.getDataByMap(mapdata, new AuthorshipDetails(), null, null, 0, -1);
			if(aad.size() > 0) {
				aad.get(0).setAuthor_id(ad.getAuthor_id());
				aad.get(0).setAffiliation(ad.getAffiliation());
				aad.get(0).setStatus(ad.getStatus());
				commonDao.updateDataToDb(aad.get(0));
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("sno", ad.getAa_id());
				List<Authorship_Article> art = (List<Authorship_Article>)commonDao.getDataByMap(map, new Authorship_Article(), null, null, 0, -1);
				if(ad.getStatus().equalsIgnoreCase("Booked")) {
					int p = art.get(0).getAvailable_position() -1;
					art.get(0).setAvailable_position(p);
					commonDao.updateDataToDb(art.get(0));
					Payment pay = new Payment();
					pay.setAd_id(aad.get(0).getSno());
					pay.setEmployee_id(ad.getEmployee_id());
					pay.setModule("Authorship");
					pay.setPaid_amount(0);
					pay.setPayment_status("Pending");
					pay.setTotal_amount(ad.getBooking_amount());
					pay.setRemaining_amount(ad.getBooking_amount());
					commonDao.addDataToDb(pay);
				}
				Map<String, Object> map1 = new HashMap<String,Object>();
				map1.put("sno", ad.getPosition_id());
				List<Authorship_Position> ap = (List<Authorship_Position>)commonDao.getDataByMap(map1, new Authorship_Position(), null, null, 0, -1);
				if(ad.getStatus().equalsIgnoreCase("Booked")) {
					ap.get(0).setContact_id(ad.getContact_id());
					ap.get(0).setEmployee_id(ad.getEmployee_id());
					ap.get(0).setBooked_amount(ad.getBooking_amount());
					ap.get(0).setBooked_date(ad.getBooking_date());
				}
				ap.get(0).setStatus(ad.getStatus());
				commonDao.updateDataToDb(ap.get(0));
				Map<String, Object> map2 = new HashMap<String,Object>();
				map2.put("contact_id", ad.getContact_id());
				map2.put("module", "Authorship");
				List<ConvertedModule> cd = (List<ConvertedModule>)commonDao.getDataByMap(map2, new ConvertedModule(), null, null, 0, -1);
				cd.get(0).setStatus(ad.getStatus());
				commonDao.updateDataToDb(cd.get(0));
				Map<String, Object> map4 = new HashMap<String,Object>();
				map4.put("sno", ad.getContact_id());
				List<ContactDetails> cdd = (List<ContactDetails>)commonDao.getDataByMap(map4, new ContactDetails(), null, null, 0, -1);
				cdd.get(0).setClient_name(ad.getName());
				String eml = encriptionData.encrypt(ad.getEmail());
				cdd.get(0).setEmail(eml);
				commonDao.updateDataToDb(cdd.get(0));
				ContactRemarks cr = new ContactRemarks();
				cr.setContact_id(ad.getContact_id());
				cr.setEmployee_id(ad.getEmployee_id());
				cr.setRemarks(ad.getRemarks());
				 LocalTime now = LocalTime.now();
			     String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
			     cr.setConnected_time(currentTime);
			     cr.setRemarksDate(new Date());
			     commonDao.addDataToDb(cr);
				response.put("status", "Success");
				response.put("message", "Article Details updated Successfully");
			}else {
				Map<String, Object> mpp =new HashMap<String, Object>();
				mpp.put("author_id", ad.getAuthor_id());
				List<AuthorshipDetails> data = (List<AuthorshipDetails>)commonDao.getDataByMap(mpp, new AuthorshipDetails(), null, null, 0, -1);
				if(data.size() > 0) {
					response.put("status", "Already_Exist");
					response.put("message", "Author Id Already Exist");
				}else {
					ad.setCreatedAt(new Date());
					int i = commonDao.addDataToDb(ad);
					if(i > 0) {
						Map<String, Object> map = new HashMap<String,Object>();
						map.put("sno", ad.getAa_id());
						List<Authorship_Article> art = (List<Authorship_Article>)commonDao.getDataByMap(map, new Authorship_Article(), null, null, 0, -1);
						if(ad.getStatus().equalsIgnoreCase("Booked")) {
							int p = art.get(0).getAvailable_position() -1;
							art.get(0).setAvailable_position(p);
							art.get(0).setAvailable_position(p);
							commonDao.updateDataToDb(art.get(0));
							Payment pay = new Payment();
							pay.setAd_id(i);
							pay.setEmployee_id(ad.getEmployee_id());
							pay.setModule("Authorship");
							pay.setPaid_amount(0);
							pay.setPayment_status("Pending");
							pay.setTotal_amount(ad.getBooking_amount());
							pay.setRemaining_amount(ad.getBooking_amount());
							commonDao.addDataToDb(pay);
						}
						Map<String, Object> map1 = new HashMap<String,Object>();
						map1.put("sno", ad.getPosition_id());
						List<Authorship_Position> ap = (List<Authorship_Position>)commonDao.getDataByMap(map1, new Authorship_Position(), null, null, 0, -1);
						if(ad.getStatus().equalsIgnoreCase("Booked")) {
							ap.get(0).setContact_id(ad.getContact_id());
							ap.get(0).setEmployee_id(ad.getEmployee_id());
							ap.get(0).setBooked_amount(ad.getBooking_amount());
							ap.get(0).setBooked_date(ad.getBooking_date());
						}
						ap.get(0).setStatus(ad.getStatus());
						commonDao.updateDataToDb(ap.get(0));
						Map<String, Object> map2 = new HashMap<String,Object>();
						map2.put("contact_id", ad.getContact_id());
						map2.put("module", "Authorship");
						List<ConvertedModule> cd = (List<ConvertedModule>)commonDao.getDataByMap(map2, new ConvertedModule(), null, null, 0, -1);
						cd.get(0).setStatus(ad.getStatus());
						commonDao.updateDataToDb(cd.get(0));
						Map<String, Object> map4 = new HashMap<String,Object>();
						map4.put("sno", ad.getContact_id());
						List<ContactDetails> cdd = (List<ContactDetails>)commonDao.getDataByMap(map4, new ContactDetails(), null, null, 0, -1);
						cdd.get(0).setClient_name(ad.getName());
						String eml = encriptionData.encrypt(ad.getEmail());
						cdd.get(0).setEmail(eml);
						commonDao.updateDataToDb(cdd.get(0));
						ContactRemarks cr = new ContactRemarks();
						cr.setContact_id(ad.getContact_id());
						cr.setEmployee_id(ad.getEmployee_id());
						cr.setRemarks(ad.getRemarks());
						 LocalTime now = LocalTime.now();
					     String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
					     cr.setConnected_time(currentTime);
					     cr.setRemarksDate(new Date());
					     commonDao.addDataToDb(cr);
						response.put("status", "Success");
						response.put("message", "Article Details Added Successfully");
					}else{
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

	public Map<String, Object> get_author(int start, int length, String search, String employee_id, String status) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			if(Integer.parseInt(employee_id) > 0) {
				map.put("employee_id", Integer.parseInt(employee_id));
			}
			map.put("status", status);
			Map<String, Object> mapor = new HashMap<String,Object>();
			mapor.put("author_id", search);
			mapor.put("affiliation", search);
			List<AuthorshipDetails> data = (List<AuthorshipDetails>) commonDao.getDataByMapSearchAnd(map,mapor, new AuthorshipDetails(), "sno", "desc", start, length);
			int count = commonDao.getDataByMapSearchAndSize(map,mapor, new AuthorshipDetails(), "sno", "desc");
			if(data.size() >0) {
				for(AuthorshipDetails a: data) {
					Map<String, Object> map1 = new HashMap<String,Object>();
					Map<String, Object> map2 = new HashMap<String,Object>();
					Map<String, Object> map3 = new HashMap<String,Object>();
					map1.put("sno", a.getAa_id());
					List<Authorship_Article> asa = (List<Authorship_Article>)commonDao.getDataByMap(map1, new Authorship_Article(), null, null, 0, -1);
					a.setJournal_name(asa.get(0).getJournal_name());
					a.setTitle(asa.get(0).getTitle());
					map2.put("sno", a.getPosition_id());
					List<Authorship_Position> asp = (List<Authorship_Position>)commonDao.getDataByMap(map2, new Authorship_Position(), null, null, 0, -1);
					a.setPosition(asp.get(0).getPosition());
					a.setBooking_date(asp.get(0).getBooked_date());
					a.setBooking_amount(asp.get(0).getBooked_amount());
					map3.put("sno", a.getContact_id());
					List<ContactDetails> cd = (List<ContactDetails>)commonDao.getDataByMap(map3, new ContactDetails(), null, null, 0, -1);
					String con = encriptionData.decrypt(cd.get(0).getContact_number());
					String eml = encriptionData.decrypt(cd.get(0).getEmail());
					a.setContact_number(con);
					a.setName(cd.get(0).getClient_name());
					a.setEmail(eml);
					if(a.getStatus().equalsIgnoreCase("Received") 
							|| a.getStatus().equalsIgnoreCase("Initiated") 
							|| a.getStatus().equalsIgnoreCase("Paid") 
							|| a.getStatus().equalsIgnoreCase("Partially") 
							|| a.getStatus().equalsIgnoreCase("Proved") 
							|| a.getStatus().equalsIgnoreCase("Completed") 
							|| a.getStatus().equalsIgnoreCase("Acceptance")) {
						Map<String, Object> mp = new HashMap<String,Object>();
						mp.put("ad_id", a.getSno());
						mp.put("module", "Authorship");
						List<Payment> pay = (List<Payment>)commonDao.getDataByMap(mp, new Payment(), null, null, 0, -1);
						
						a.setPaid_amount(pay.get(0).getPaid_amount());
						a.setRemaining_amount(pay.get(0).getRemaining_amount());
						a.setPayment_mode(pay.get(0).getPayment_mode());
						a.setPayment_status(pay.get(0).getPayment_status());
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

	public Map<String, Object> received_authorship_payment(String sno) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map1 = new HashMap<String,Object>();
			map1.put("sno", Integer.parseInt(sno));
			List<AuthorshipDetails> data = (List<AuthorshipDetails>)commonDao.getDataByMap(map1, new AuthorshipDetails(), null, null, 0, -1);
			if(data.size() > 0) {
				data.get(0).setStatus("Received");
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Payment Received Successfully");
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

	public Map<String, Object> send_author_acceptance(String sno, MultipartFile file, String status) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<AuthorshipDetails> data = (List<AuthorshipDetails>)commonDao.getDataByMap(map, new AuthorshipDetails(), null, null, 0, -1);
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

	public Map<String, Object> add_galleryproof_date(String sno, MultipartFile file, String proof_date) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sno", Integer.parseInt(sno));
			List<AuthorshipDetails> data = (List<AuthorshipDetails>)commonDao.getDataByMap(map, new AuthorshipDetails(), null, null, 0, -1);
			if(data.size() >0) {
				data.get(0).setProof_date(proof_date);
				data.get(0).setStatus("Proved");
				commonDao.updateDataToDb(data.get(0));
				response.put("status", "Success");
				response.put("message", "Gallery Proof Added Successfully");
			}else {
				response.put("status", "Failed");
				response.put("message", "Internal Server Error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong"+e);
		}
		return response;
	}
}

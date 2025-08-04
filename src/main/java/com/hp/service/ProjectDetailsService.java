package com.hp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.dao.CommonDao;
import com.hp.model.ArticleDetails;
import com.hp.model.AuthorshipDetails;
import com.hp.model.ContactDetails;
import com.hp.model.Payment;
import com.hp.model.ProjectDetails;
import com.hp.model.WritingDetails;
import com.hp.utils.EncriptionData;

@Service
public class ProjectDetailsService {

	@Autowired
	CommonDao commonDao;
	@Autowired
	static
	EncriptionData encriptionData;

	public Map<String, Object> get_project_data(int start, int length, String search, String employee_id, String status,String month, String year) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			List<ProjectDetails> pss = new ArrayList<ProjectDetails>();
			ProjectDetails pdd = new ProjectDetails();
			Map<String, Object> map = new HashMap<String,Object>();
			Map<String, Object> mpp = new HashMap<String,Object>();
			if(Integer.parseInt(employee_id) > 0) {
				map.put("employee_id", Integer.parseInt(employee_id));
			}
			if(status != null && !status.isEmpty()) {
				map.put("status", status);
			}
			map.put("month", Integer.parseInt(month));
			map.put("year", Integer.parseInt(year));
			Map<String, Object> mapor = new HashMap<String,Object>();
			mapor.put("payment_status", search);
			//mapor.put("title", search);
			List<Payment> data = (List<Payment>) commonDao.getDataByMapSearchAnd(map,mapor, new Payment(), "sno", "desc", start, length);
			int count = commonDao.getDataByMapSearchAndSize(map,mapor, new Payment(), "sno", "desc");
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
						pdd.setArticle_id(ad.get(0).getArticle_id());
						pdd.setContact_number(deccont);
						pdd.setEmail(decemail);
						pdd.setJournal_name(ad.get(0).getJournal_name());
						pdd.setTitle(ad.get(0).getArticle_title());
						pdd.setAmount(ad.get(0).getDealed_amount());
						pdd.setModule(p.getModule());
						pdd.setStatus(ad.get(0).getStatus());
						pss.add(pdd);
					}else if(p.getModule().equalsIgnoreCase("Writing Paper")) {
						List<WritingDetails> ad = (List<WritingDetails>)commonDao.getDataByMap(mp, new WritingDetails(), null, null, 0, -1);
						pdd.setArticle_id(ad.get(0).getArticle_id());
						pdd.setContact_number(deccont);
						pdd.setEmail(decemail);
						pdd.setJournal_name("NA");
						pdd.setTitle(ad.get(0).getTitle());
						pdd.setAmount(ad.get(0).getAmount());
						pdd.setModule(p.getModule());
						pdd.setStatus(ad.get(0).getStatus());
						pss.add(pdd);
					}else {
						List<AuthorshipDetails> ad = (List<AuthorshipDetails>)commonDao.getDataByMap(mp, new AuthorshipDetails(), null, null, 0, -1);
						pdd.setArticle_id(ad.get(0).getAuthor_id());
						pdd.setContact_number(deccont);
						pdd.setEmail(decemail);
						pdd.setJournal_name(ad.get(0).getJournal_name());
						pdd.setTitle(ad.get(0).getTitle());
						pdd.setAmount(ad.get(0).getBooking_amount());
						pdd.setModule(p.getModule());
						pdd.setStatus(ad.get(0).getStatus());
						pss.add(pdd);
					}
					
				}
				response.put("status", "Success");
				response.put("message", "Data Fetched Successfully");
				response.put("data", pss);
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

package com.hp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.dao.CommonDao;
import com.hp.model.Authorship_Article;
import com.hp.model.Authorship_Position;
import com.hp.model.Employee;
import com.hp.model.LoginCredentials;

@Service
public class AuthorshipArticleService {

	@Autowired
	CommonDao commonDao;

	public Map<String, Object> add_aa(Authorship_Article aa) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			if(aa.getSno() > 0) {
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("sno", aa.getSno());
				List<Authorship_Article> data = (List<Authorship_Article>)commonDao.getDataByMap(map, new Authorship_Article(), null, null, 0, -1);
				data.get(0).setTitle(aa.getTitle());
				data.get(0).setJournal_name(aa.getJournal_name());
				data.get(0).setAvailable_position(aa.getAvailable_position());
				commonDao.updateDataToDb(data.get(0));
				//List<Authorship_Position> ap = aa.getAp();
				response.put("status", "Success");
				response.put("message", "Article Details Updated Successfully");
			}else {
				aa.setStatus("Active");
				aa.setCreatedAt(new Date());
				int i = commonDao.addDataToDb(aa);
				if(i > 0) {
					List<Authorship_Position> ap = aa.getAp();
					Authorship_Position app = new Authorship_Position();
					for(Authorship_Position a : ap) {
						app.setAa_id(i);
						app.setEmployee_id(0);
						app.setContact_id(0);
						app.setPosition(a.getPosition());
						app.setPosition_amount(a.getPosition_amount());
						app.setStatus("Available");
						app.setCreatedAt(new Date());
						commonDao.addDataToDb(app);
					}
					
					response.put("status", "Success");
					response.put("message", "Article Details Added Successfully");
				}else {
					response.put("status", "Failed");
					response.put("message", "Internal Server Error");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
			response.put("message", "Something Went Wrong"+e);
		}
		return response;
	}

	public Map<String, Object> get_aa(int start, int length, String search) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			
			Map<String, Object> mapor = new HashMap<String,Object>();
			mapor.put("title", search);
			mapor.put("journal_name", search);
			List<Authorship_Article> data = (List<Authorship_Article>) commonDao.getDataByMapSearchAnd(map,mapor, new Authorship_Article(), "sno", "desc", start, length);
			int count = commonDao.getDataByMapSearchAndSize(map,mapor, new Authorship_Article(), "sno", "desc");
			if(data.size() >0) {
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

	public Map<String, Object> get_position(String sno) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			Map<String, Object> map1 = new HashMap<String,Object>();
			map.put("aa_id", Integer.parseInt(sno));
			map1.put("sno", Integer.parseInt(sno));
			List<Authorship_Position> dd = (List<Authorship_Position>)commonDao.getDataByMap(map, new Authorship_Position(), "position", "desc", 0, -1);
			List<Authorship_Article> data = (List<Authorship_Article>)commonDao.getDataByMap(map1, new Authorship_Article(), null, null, 0, -1);
			if(data.size() > 0) {
				for(Authorship_Position a: dd) {
					if(!a.getStatus().equalsIgnoreCase("Available")) {
						Map<String, Object> mp = new HashMap<String,Object>();
						if(a.getEmployee_id() > 0) {
							mp.put("sno", a.getEmployee_id());
							List<Employee> em = (List<Employee>)commonDao.getDataByMap(mp, new Employee(), null, null, 0, -1);
							a.setEmployee_name(em.get(0).getEmployee_name());
						}else {
							a.setEmployee_name("Admin");
						}
					}
				}
				data.get(0).setAp(dd);
				response.put("status", "Success");
				response.put("message", "Data Fetched Successfully");
				response.put("data", data);
				response.put("dd", dd);
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

	public Map<String, Object> get_title(String journal_name) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Map<String, Object> map1 = new HashMap<String,Object>();
			map1.put("journal_name", journal_name);
			List<Authorship_Article> data = (List<Authorship_Article>)commonDao.getDataByMap(map1, new Authorship_Article(), null, null, 0, -1);
			if(data.size() > 0) {
				response.put("status", "Success");
				response.put("message", "Data Fetched Successfully");
				response.put("data", data);
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

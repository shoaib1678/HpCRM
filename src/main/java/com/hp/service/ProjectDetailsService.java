package com.hp.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.dao.CommonDao;
import com.hp.model.ArticleDetails;
import com.hp.model.AuthorshipDetails;
import com.hp.model.Authorship_Article;
import com.hp.model.Authorship_Position;
import com.hp.model.ContactDetails;
import com.hp.model.ContactRemarks;
import com.hp.model.ConvertedModule;
import com.hp.model.Employee;
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

	public Map<String, Object> get_project_data(int start, int length, String search, String employee_id, String status, String from_date, String to_date, String module) {
	    Map<String, Object> response = new HashMap<String, Object>();
	    try {
	        List<ProjectDetails> pss = new ArrayList<>();
	        Map<String, Object> map = new HashMap<>();
	        Map<String, Object> mapor = new HashMap<>();

	        if (Integer.parseInt(employee_id) > 0) {
	            map.put("employee_id", Integer.parseInt(employee_id));
	        }

	        if (module != null && !module.isEmpty()) {
	            map.put("module", module);
	        }

	        mapor.put("payment_status", search);

	        List<Payment> data = (List<Payment>) commonDao.getDataByMapWithDateRange(map, mapor, new Payment(), "sno", "desc", start, length,from_date,to_date);
	        int count = commonDao.getDataByMapWithDateRangeSize(map, mapor, new Payment(), "sno", "desc",from_date,to_date);

	        if (data.size() > 0) {
	            for (Payment p : data) {
	                Map<String, Object> mapr = new HashMap<>();
	                mapr.put("sno", p.getContact_id());
	                List<ContactDetails> con = (List<ContactDetails>) commonDao.getDataByMap(mapr, new ContactDetails(), null, null, 0, -1);

	                String decemail = encriptionData.decrypt(con.get(0).getEmail());
	                String deccont = encriptionData.decrypt(con.get(0).getContact_number());
	                p.setContact_number(deccont);
	                p.setEmail(decemail);

	                Map<String, Object> mp = new HashMap<>();
	                mp.put("sno", p.getAd_id());

	                ProjectDetails pdd = new ProjectDetails(); // ✅ create a new instance inside the loop

	                if (p.getModule().equalsIgnoreCase("Publication")) {
	                    List<ArticleDetails> ad = (List<ArticleDetails>) commonDao.getDataByMap(mp, new ArticleDetails(), null, null, 0, -1);
	                    if (!ad.isEmpty()) {
	                    	Map<String, Object> maa = new HashMap<>();
	                    	maa.put("sno", ad.get(0).getEmployee_id());
	                    	List<Employee> emp = (List<Employee>)commonDao.getDataByMap(maa, new Employee(), null, null, 0, -1);
	                       String name="Admin";
	                    	if(emp.size() > 0) {
	                    		name = emp.get(0).getEmployee_name();
	                    	}
	                    	pdd.setArticle_id(ad.get(0).getArticle_id()+"("+name+")");
	                        pdd.setJournal_name(ad.get(0).getJournal_name());
	                        pdd.setTitle(ad.get(0).getArticle_title());
	                        pdd.setAmount(ad.get(0).getDealed_amount());
	                        pdd.setStatus(ad.get(0).getStatus());
	                    }
	                } else if (p.getModule().equalsIgnoreCase("Writing Paper")) {
	                    List<WritingDetails> ad = (List<WritingDetails>) commonDao.getDataByMap(mp, new WritingDetails(), null, null, 0, -1);
	                    if (!ad.isEmpty()) {
	                    	Map<String, Object> maa = new HashMap<>();
	                    	maa.put("sno", ad.get(0).getEmployee_id());
	                    	List<Employee> emp = (List<Employee>)commonDao.getDataByMap(maa, new Employee(), null, null, 0, -1);
	                       String name="Admin";
	                    	if(emp.size() > 0) {
	                    		name = emp.get(0).getEmployee_name();
	                    	}
	                    	pdd.setArticle_id(ad.get(0).getArticle_id()+"("+name+")");
	                        pdd.setJournal_name("NA");
	                        pdd.setTitle(ad.get(0).getTitle());
	                        pdd.setAmount(ad.get(0).getAmount());
	                        pdd.setStatus(ad.get(0).getStatus());
	                    }
	                } else {
	                    List<AuthorshipDetails> ad = (List<AuthorshipDetails>) commonDao.getDataByMap(mp, new AuthorshipDetails(), null, null, 0, -1);
	                    if (!ad.isEmpty()) {
	                    	Map<String, Object> maa = new HashMap<>();
	                    	maa.put("sno", ad.get(0).getEmployee_id());
	                    	List<Employee> emp = (List<Employee>)commonDao.getDataByMap(maa, new Employee(), null, null, 0, -1);
	                       String name="Admin";
	                    	if(emp.size() > 0) {
	                    		name = emp.get(0).getEmployee_name();
	                    	}
	                    	pdd.setArticle_id(ad.get(0).getAuthor_id()+"("+name+")");
	                        pdd.setJournal_name(ad.get(0).getJournal_name());
	                        pdd.setTitle(ad.get(0).getTitle());
	                        pdd.setAmount(ad.get(0).getBooking_amount());
	                        pdd.setStatus(ad.get(0).getStatus());
	                    }
	                }

	                pdd.setContact_number(deccont);
	                pdd.setEmail(decemail);
	                pdd.setModule(p.getModule());

	                pss.add(pdd); // ✅ adding unique object to list
	            }

	            response.put("status", "Success");
	            response.put("message", "Data Fetched Successfully");
	            response.put("data", pss);
	            response.put("recordsFiltered", count);
	            response.put("recordsTotal", count);
	        } else {
	            response.put("status", "Failed");
	            response.put("message", "No Data Found");
	            response.put("data", new ArrayList<>());
	            response.put("recordsFiltered", 0);
	            response.put("recordsTotal", 0);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("status", "Failed");
	        response.put("message", "Something Went Wrong: " + e.getMessage());
	    }

	    return response;
	}

	public Map<String, Object> get_details(String employee_id, String from_date, String to_date) {
		 Map<String, Object> response = new HashMap<String, Object>();
		    try {
		    	Map<String, Object> map = new HashMap<>();
		    	if(Integer.parseInt(employee_id) > 0) {
		    		map.put("employee_id", Integer.parseInt(employee_id));
		    	}
		    	List<ContactDetails> data = (List<ContactDetails>) commonDao.getDataByMapWithDateRange(
		    		    map, new HashMap<String, Object>(), new ContactDetails(), "sno", "desc", 0, -1, from_date, to_date
		    		);

		    		if (data != null && !data.isEmpty()) {
		    		    for (ContactDetails cn : data) {
		    		        Map<String, Object> mpp = new HashMap<String, Object>();
		    		        mpp.put("contact_id", cn.getSno());
		    		        mpp.put("employee_id", cn.getEmployee_id());

		    		        List<ConvertedModule> cmo = (List<ConvertedModule>) commonDao.getDataByMapWithDateRange(
		    		            mpp, new HashMap<String, Object>(), new ConvertedModule(), "sno", "desc", 0, -1, from_date, to_date
		    		        );
		    		        cn.setCm(cmo); // Set ConvertedModule list
		    		    }
		    		}

		    		// Fetch other data (Article, Writing, Authorship)
		    		List<ArticleDetails> art = (List<ArticleDetails>) commonDao.getDataByMapWithDateRange(
		    		    map, new HashMap<String, Object>(), new ArticleDetails(), "sno", "desc", 0, -1, from_date, to_date
		    		);
		    		List<WritingDetails> wrd = (List<WritingDetails>) commonDao.getDataByMapWithDateRange(
		    		    map, new HashMap<String, Object>(), new WritingDetails(), "sno", "desc", 0, -1, from_date, to_date
		    		);
		    		List<AuthorshipDetails> asd = (List<AuthorshipDetails>) commonDao.getDataByMapWithDateRange(
		    		    map, new HashMap<String, Object>(), new AuthorshipDetails(), "sno", "desc", 0, -1, from_date, to_date
		    		);

		    		// Initialize counters
		    		int con = 0, pcon = 0, wcon = 0, acon = 0;
		    		int arts = art.size();
		    		int wrds = wrd.size();
		    		int asds = asd.size();
		    		float per = 0, pper = 0, wper = 0, aper = 0;

		    		// Count conversions and module-wise categorization
		    		if (data != null && !data.isEmpty()) {
		    		    for (ContactDetails c : data) {
		    		        if ("Converted".equalsIgnoreCase(c.getStatus())) {
		    		            con++;

		    		            if (c.getCm() != null && !c.getCm().isEmpty()) {
		    		            	List<ConvertedModule> cm = c.getCm();
		    		            	for(ConvertedModule cc : cm ) {
		    		            		 if ("Publication".equalsIgnoreCase(cc.getModule())) {
				    		                    pcon++;
				    		                } if ("Writing Paper".equalsIgnoreCase(cc.getModule())) {
				    		                    wcon++;
				    		                } if ("Authorship".equalsIgnoreCase(cc.getModule())) {
				    		                    acon++;
				    		                }
		    		            	}
		    		               
		    		            }
		    		        }
		    		    }

		    		    // Overall conversion %
		    		    per = ((float) con / data.size()) * 100;

		    		    // Safe division checks for module-wise %
		    		    if (pcon > 0) {
		    		        pper = ((float) arts / pcon) * 100;
		    		    }
		    		    if (wcon > 0) {
		    		        wper = ((float) wrds / wcon) * 100;
		    		    }
		    		    if (acon > 0) {
		    		        aper = ((float) asds / acon) * 100;
		    		    }
		    		}

		    		response.put("total", data != null ? data.size() : 0);
		    		response.put("converted", con);
		    		response.put("pub_conv", pcon);
		    		response.put("write_conv", wcon);
		    		response.put("auth_conv", acon);
		    		response.put("articles", arts);
		    		response.put("writings", wrds);
		    		response.put("authorships", asds);
		    		response.put("conv_percent", per);
		    		response.put("pub_percent", pper);
		    		response.put("write_percent", wper);
		    		response.put("auth_percent", aper);
		    		response.put("status", "Success");
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("status", "Failed");
		        response.put("message", "Something Went Wrong: " + e.getMessage());
		    }

		    return response;
	}

	public Map<String, Object> get_article_details(String from_date, String to_date) {
		 Map<String, Object> response = new HashMap<String, Object>();
		    try {
		    	List<String> journal_name= (List<String>)commonDao.getDistinctDataByDateRange("journal_name", new Authorship_Article(),from_date,to_date);
		    	
		    	System.out.println(journal_name);
		    	if(journal_name.size() > 0) {
		    		response.put("journal_name", journal_name);
		    		response.put("status", "Success");
		    	}else {
		    		response.put("status", "Failed");
			        response.put("message", "Something Went Wrong");
		    	}
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("status", "Failed");
		        response.put("message", "Something Went Wrong: " + e.getMessage());
		    }

		    return response;
	}

	public Map<String, Object> get_positions_details(String journal_name, String from_date, String to_date) {
		 Map<String, Object> response = new HashMap<String, Object>();
		    try {
		    	 Map<String, Object> map = new HashMap<String, Object>();
		    	 map.put("journal_name", journal_name);
		    	List<Authorship_Article> data= (List<Authorship_Article>)commonDao.getDataByMapWithDateRange(map, new HashMap<String, Object>(), new Authorship_Article(), "sno", "desc", 0, -1,from_date,to_date);
		    	if(data.size() > 0) {
		    		for(Authorship_Article a : data) {
		    			 Map<String, Object> mp = new HashMap<String, Object>();
				    	 mp.put("aa_id", a.getSno());
				    	 List<Authorship_Position> ap = (List<Authorship_Position>)commonDao.getDataByMap(mp, new Authorship_Position(), null, null, 0, -1);
				    	 a.setAp(ap);
		    		}
		    		response.put("data", data);
		    		response.put("status", "Success");
		    	}else {
		    		response.put("status", "Failed");
			        response.put("message", "Something Went Wrong");
		    	}
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("status", "Failed");
		        response.put("message", "Something Went Wrong: " + e.getMessage());
		    }

		    return response;
	}

	public Map<String, Object> get_notifications(String employee_id, String from_date, String to_date) {
		 Map<String, Object> response = new HashMap<String, Object>();
		    try {
		    
		    	 Map<String, Object> map = new HashMap<String, Object>();
		    	 Map<String, Object> map1 = new HashMap<String, Object>();
		    	 if(Integer.parseInt(employee_id) > 0) {
		    		 map.put("employee_id", Integer.parseInt(employee_id));
		    	 }
		    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	String currentDateStr = LocalDate.now(ZoneId.of("Asia/Kolkata")).format(formatter);
		    	 Date cur = new Date();
		         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		         String not_date = sdf.format(cur);
		         map1.put("notify_date", not_date);
		         List<ContactRemarks>  taday = (List<ContactRemarks>)commonDao.getDataByMap(map1, new ContactRemarks(), null, null, 0, -1);
		    	 List<ContactRemarks>  data1 = new ArrayList<ContactRemarks>();
		    	 List<ContactRemarks>  data = new ArrayList<ContactRemarks>();
		    	 if(from_date != null && !from_date.isEmpty() && to_date != null && !to_date.isEmpty()) {
		    		 data= (List<ContactRemarks>)commonDao.getDataByMapWithDateRange(map,new HashMap<String, Object>(), new ContactRemarks(), "notify_date", "desc", 0, -1,from_date, to_date);
		    	 }else {
		    		 data= (List<ContactRemarks>)commonDao.getDataByMap(map, new ContactRemarks(),"notify_date", "asc", 0, -1);
		    	 }
		    	 System.out.println("ContactRemarksContactRemarks===="+data.size());
		    	if(data.size() > 0) {
		    		for(ContactRemarks c : data) {
		    			if(c.getNotify_date() != null && !c.getNotify_date().isEmpty()) {
		    				  LocalDate notifyDate = LocalDate.parse(c.getNotify_date(), formatter);
		    			      LocalDate currentDate = LocalDate.parse(currentDateStr, formatter);
		    			      if (!notifyDate.isAfter(currentDate)) {
				    			 Map<String, Object> mp = new HashMap<String, Object>();
						    	 mp.put("sno", c.getContact_id());
						    	 List<ContactDetails> cd = (List<ContactDetails>)commonDao.getDataByMap(mp, new ContactDetails(), null, null, 0, -1);
						    	String decc = encriptionData.decrypt(cd.get(0).getContact_number());
						    	 c.setContact_number(decc);
						    	 c.setName(cd.get(0).getClient_name());
						    	 Map<String, Object> mp1 = new HashMap<String, Object>();
						    	 mp1.put("sno", c.getEmployee_id());
						    	 List<Employee> emp = (List<Employee>)commonDao.getDataByMap(mp1, new Employee(), null, null, 0, -1);
						    	if(emp.size() > 0) {
						    		c.setEmployee_name(emp.get(0).getEmployee_name());
						    	}else {
						    		c.setEmployee_name("Admin");
						    	}
						    	data1.add(c);
				    		}
		    			}
		    		}
		    		response.put("taday", taday);
		    		response.put("data", data1);
		    		response.put("status", "Success");
		    	}else {
		    		response.put("status", "Failed");
			        response.put("message", "Something Went Wrong");
		    	}
		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("status", "Failed");
		        response.put("message", "Something Went Wrong: " + e.getMessage());
		    }

		    return response;
	}

}

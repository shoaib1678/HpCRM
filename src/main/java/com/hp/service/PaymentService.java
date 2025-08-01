package com.hp.service;

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
import com.hp.model.Payment;
import com.hp.model.PaymentReceipt;
import com.hp.model.WritingDetails;
import com.hp.utils.Utils;

@Service
public class PaymentService {

	@Autowired
	CommonDao commonDao;

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
}

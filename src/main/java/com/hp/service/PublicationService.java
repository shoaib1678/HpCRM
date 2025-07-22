package com.hp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hp.dao.CommonDao;
import com.hp.model.ArticleDetails;
import com.hp.model.Payment;
import com.hp.model.PublicationDetails;
import com.hp.utils.Utils;

@Service
public class PublicationService {

	@Autowired
	CommonDao commonDao;

	public Map<String, Object> add_publication_details(PublicationDetails publication, MultipartFile publicationpdf) {
		Map<String, Object> response = new HashMap<String,Object>();
		try {
			Utils utils = new Utils();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sno", publication.getSno());
			List<PublicationDetails> pa = (List<PublicationDetails>)commonDao.getDataByMap(map, new PublicationDetails(), null, null, 0, -1);
			if(pa.size() > 0) {
				if(publicationpdf != null  && !publicationpdf.isEmpty()) {
					String file = utils.uploadImage(publicationpdf);
					pa.get(0).setPublication_pdf(file);
				}
				pa.get(0).setPublication_pdf(publication.getPublication_url());
				pa.get(0).setPublication_date(publication.getPublication_date());
				commonDao.updateDataToDb(pa.get(0));
				response.put("status", "Success");
				response.put("message", "Publication Details Updated Successfully");
			}else {
				String file = utils.uploadImage(publicationpdf);
				publication.setPublication_pdf(file);
				int i = commonDao.addDataToDb(publication);
				if(i > 0) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("sno", publication.getAd_id());
					List<ArticleDetails> ad = (List<ArticleDetails>)commonDao.getDataByMap(map1, new ArticleDetails(), null, null, 0, -1);
					ad.get(0).setStatus("Published");
					commonDao.updateDataToDb(ad.get(0));
					response.put("status", "Success");
					response.put("message", "Publication Details Added Successfully");
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
}

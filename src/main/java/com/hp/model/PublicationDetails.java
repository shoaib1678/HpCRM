package com.hp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PublicationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sno;
	private int ad_id;
	private int employee_id;
	private String publication_url;
	private String publication_pdf;
	private Date publication_date;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getPublication_url() {
		return publication_url;
	}
	public void setPublication_url(String publication_url) {
		this.publication_url = publication_url;
	}
	public String getPublication_pdf() {
		return publication_pdf;
	}
	public void setPublication_pdf(String publication_pdf) {
		this.publication_pdf = publication_pdf;
	}
	public Date getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}
}

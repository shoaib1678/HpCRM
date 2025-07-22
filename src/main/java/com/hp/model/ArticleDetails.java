package com.hp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class ArticleDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sno;
	private int contact_id;
	private int employee_id;
	private String contact_number;
	private String client_name;
	private String email;
	private String file;
	private String acceptance_file;
	private String journal_name;
	private String article_id;
	private String article_title;
	private float dealed_amount;
	@Transient
	private float paid_amount;
	@Transient
	private float remaining_amount;
	@Transient
	private String payment_mode;
	@Transient
	private String payment_status;
	@Transient
	private String url;
	@Transient
	private String pdf;
	@Transient
	private Date publication_date;
	@Transient
	private String receipt;
	private String affilliation;
	private String status;
	@Temporal(TemporalType.DATE)
	private Date gallery_proof_date;
	private Date createdAt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getContact_id() {
		return contact_id;
	}
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getAcceptance_file() {
		return acceptance_file;
	}
	public void setAcceptance_file(String acceptance_file) {
		this.acceptance_file = acceptance_file;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	public Date getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getGallery_proof_date() {
		return gallery_proof_date;
	}
	public void setGallery_proof_date(Date gallery_proof_date) {
		this.gallery_proof_date = gallery_proof_date;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getJournal_name() {
		return journal_name;
	}
	public void setJournal_name(String journal_name) {
		this.journal_name = journal_name;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public float getDealed_amount() {
		return dealed_amount;
	}
	public void setDealed_amount(float dealed_amount) {
		this.dealed_amount = dealed_amount;
	}
	public float getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(float paid_amount) {
		this.paid_amount = paid_amount;
	}
	public float getRemaining_amount() {
		return remaining_amount;
	}
	public void setRemaining_amount(float remaining_amount) {
		this.remaining_amount = remaining_amount;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getAffilliation() {
		return affilliation;
	}
	public void setAffilliation(String affilliation) {
		this.affilliation = affilliation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}

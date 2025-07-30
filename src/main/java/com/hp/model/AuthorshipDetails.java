package com.hp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class AuthorshipDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sno;
	private int employee_id;
	private int contact_id;
	private int aa_id;
	private int position_id;
	private String author_id;
	@Transient
	private float booking_amount;
	@Transient
	private Date booking_date;
	@Transient
	private String remarks;
	@Transient
	private String email;
	@Transient
	private String name;
	@Transient
	private String contact_number;
	@Transient
	private String journal_name;
	@Transient
	private String title;
	@Transient
	private String position;
	@Transient
	private float paid_amount;
	@Transient
	private float remaining_amount;
	@Transient
	private String payment_mode;
	@Transient
	private String payment_status;
	private String affiliation;
	private String acceptance_file;
	private String proof_date;
	private String status;
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
	public int getAa_id() {
		return aa_id;
	}
	public void setAa_id(int aa_id) {
		this.aa_id = aa_id;
	}
	public int getPosition_id() {
		return position_id;
	}
	public String getAcceptance_file() {
		return acceptance_file;
	}
	public void setAcceptance_file(String acceptance_file) {
		this.acceptance_file = acceptance_file;
	}
	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getContact_number() {
		return contact_number;
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
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getJournal_name() {
		return journal_name;
	}
	public void setJournal_name(String journal_name) {
		this.journal_name = journal_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProof_date() {
		return proof_date;
	}
	public void setProof_date(String proof_date) {
		this.proof_date = proof_date;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	public float getBooking_amount() {
		return booking_amount;
	}
	public void setBooking_amount(float booking_amount) {
		this.booking_amount = booking_amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
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

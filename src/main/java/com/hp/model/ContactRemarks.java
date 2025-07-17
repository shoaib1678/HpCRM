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
public class ContactRemarks {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sno;
	private int contact_id;
	private int employee_id;
	@Transient
	private String employee_name;
	private String remarks;
	private String connected_time;
	@Temporal(TemporalType.DATE)
	private Date remarksDate;
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
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getConnected_time() {
		return connected_time;
	}
	public void setConnected_time(String connected_time) {
		this.connected_time = connected_time;
	}
	public Date getRemarksDate() {
		return remarksDate;
	}
	public void setRemarksDate(Date remarksDate) {
		this.remarksDate = remarksDate;
	}
}

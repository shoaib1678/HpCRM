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
	@Transient
	private String name;
	@Transient
	private String contact_number;
	private String remarks;
	private String module;
	private String notify_satus;
	private String connected_time;
	@Temporal(TemporalType.DATE)
	private Date remarksDate;
	private String notify_date;
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
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getNotify_satus() {
		return notify_satus;
	}
	public void setNotify_satus(String notify_satus) {
		this.notify_satus = notify_satus;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNotify_date() {
		return notify_date;
	}
	public void setNotify_date(String notify_date) {
		this.notify_date = notify_date;
	}
}

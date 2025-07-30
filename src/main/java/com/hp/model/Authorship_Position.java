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
public class Authorship_Position {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sno;
	private int employee_id;
	private int contact_id;
	private int aa_id;
	private String position;
	private float position_amount;
	@Transient
	private float corresponding_amount;
	@Transient
	private String employee_name;
	private float booked_amount;
	private String status;
	@Temporal(TemporalType.DATE)
	private Date booked_date;
	private Date createdAt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getContact_id() {
		return contact_id;
	}
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	public int getAa_id() {
		return aa_id;
	}
	public void setAa_id(int aa_id) {
		this.aa_id = aa_id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public float getPosition_amount() {
		return position_amount;
	}
	public void setPosition_amount(float position_amount) {
		this.position_amount = position_amount;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public float getCorresponding_amount() {
		return corresponding_amount;
	}
	public void setCorresponding_amount(float corresponding_amount) {
		this.corresponding_amount = corresponding_amount;
	}
	public float getBooked_amount() {
		return booked_amount;
	}
	public void setBooked_amount(float booked_amount) {
		this.booked_amount = booked_amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getBooked_date() {
		return booked_date;
	}
	public void setBooked_date(Date booked_date) {
		this.booked_date = booked_date;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}

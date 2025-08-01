package com.hp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sno;
	private String employee_name;
	private String email;
	private String mobile_number;
	@Transient
	private String password;
	@Transient
	private String authentication_id;
	private String status;
	private Date ctreatedAt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getAuthentication_id() {
		return authentication_id;
	}
	public void setAuthentication_id(String authentication_id) {
		this.authentication_id = authentication_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCtreatedAt() {
		return ctreatedAt;
	}
	public void setCtreatedAt(Date ctreatedAt) {
		this.ctreatedAt = ctreatedAt;
	}
}

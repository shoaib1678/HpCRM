package com.hp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Authorship_Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sno;
	private int total_position;
	private int available_position;
	private String title;
	private String journal_name;
	@Transient
	private List<Authorship_Position> ap;
	private String status;
	private Date createdAt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getTotal_position() {
		return total_position;
	}
	public void setTotal_position(int total_position) {
		this.total_position = total_position;
	}
	public int getAvailable_position() {
		return available_position;
	}
	public void setAvailable_position(int available_position) {
		this.available_position = available_position;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Authorship_Position> getAp() {
		return ap;
	}
	public void setAp(List<Authorship_Position> ap) {
		this.ap = ap;
	}
	public String getJournal_name() {
		return journal_name;
	}
	public void setJournal_name(String journal_name) {
		this.journal_name = journal_name;
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

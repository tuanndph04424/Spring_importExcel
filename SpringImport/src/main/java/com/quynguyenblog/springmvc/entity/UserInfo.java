package com.quynguyenblog.springmvc.entity;

import java.util.Date;

public class UserInfo {
	
	private int id;
	private String username;
	private Date inputDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	@Override
	public String toString() {
		return "id=" + id + ", username=" + username;
	}
 
}
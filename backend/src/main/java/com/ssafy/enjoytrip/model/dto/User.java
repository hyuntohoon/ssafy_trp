package com.ssafy.enjoytrip.model.dto;

public class User {

	private String id;
	private String pw;
	private String name;
	private String salt;

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", salt=" + salt + "]";
	}

	public User(String id, String pw, String name, String salt) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.salt = salt;
	}

}

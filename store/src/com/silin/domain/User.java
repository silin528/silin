package com.silin.domain;

public class User {
	//设置成员方法
	/*
	 * 新建数据库user表
	 * 表中有属性
	 * uid,username,password,gender,email,telephone,role(用户角色),registTime(注册时间),
	 * */
	private String uid;
	private String userName;
	private String password;
	private String gender;
	private String email;
	private String telephone;
	private String role;
	private String registTime;
	private int state;
	private String code;
//	public User(String username2, String password2) {
//		// TODO Auto-generated constructor stub
//		
//	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
}

package com.ntt.userdomain;


public class User {
	private String userId;
	private String roleId;
	private String password;
	private String email;
	private String contactNumber;

	// constructor
	public User(String userId, String roleId, String password, String email, String contactNumber) {
		this.userId = userId;
		this.roleId = roleId;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
	}

	

	// getters
	public String getUserId() {
		return userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	// setters
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
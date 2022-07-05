package com.studentRegistration.model;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity 
@Table(name="user")
public class UserBean {
	@Id
	@Column(name="user_id")
	private String uid;
	@Column(name="user_name")
	@NotEmpty
	private String name;
	@Column(name="user_email")
	@NotEmpty
	private String email;
	@Column(name="user_password")
	@NotEmpty
	private String password;
	@Column(name="confirm_pass")
	@NotEmpty
	private String cpwd;
	@Column(name="user_role")
	@NotEmpty
	private String userRole;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpwd() {
		return cpwd;
	}
	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public UserBean(String uid, @NotEmpty String name, @NotEmpty String email, @NotEmpty String password,
			@NotEmpty String cpwd, @NotEmpty String userRole) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpwd = cpwd;
		this.userRole = userRole;
	}
	public UserBean() {
		
	}
	@Override
	public String toString() {
		return "UserBean [id=" + uid + ", name=" + name + ", email=" + email + ", password=" + password + ", cpwd="
				+ cpwd + ", userRole=" + userRole + "]";
	}
	
	
}

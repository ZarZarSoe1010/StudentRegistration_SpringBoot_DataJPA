package com.studentRegistration.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
}

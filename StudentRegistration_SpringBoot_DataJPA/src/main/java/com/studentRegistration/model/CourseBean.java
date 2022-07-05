package com.studentRegistration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;
@Component
@Entity @Table(name="course")
public class CourseBean {
	@Id
	@Column(name="course_id")
	@NotEmpty
	private String cid;
	
	@Column(name="course_name")
	@NotEmpty
	private String name;
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public CourseBean() {
	}

	public CourseBean(@NotEmpty String cid, @NotEmpty String name) {
		super();
		this.cid = cid;
		this.name = name;
	}

	@Override
	public String toString() {
		return "CourseBean [cid=" + cid + ", name=" + name + "]";
	}

	

	
}
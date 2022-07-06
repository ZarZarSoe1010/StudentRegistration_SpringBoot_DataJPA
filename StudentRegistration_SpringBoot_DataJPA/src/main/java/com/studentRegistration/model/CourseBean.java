package com.studentRegistration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity @Table(name="course")
public class CourseBean {
	@Id
	@Column(name="course_id")
	@NotEmpty
	private String cid;
	
	@Column(name="course_name")
	@NotEmpty
	private String name;
	
	

	
}
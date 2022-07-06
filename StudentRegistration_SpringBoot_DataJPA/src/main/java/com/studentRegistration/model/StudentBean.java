package com.studentRegistration.model;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="student")
public class StudentBean {
	@Id
	@NotEmpty
		private String sid;
	@NotEmpty
		private String name;
	@NotEmpty
		private String dob;
	@NotEmpty
		private String gender;
	@NotEmpty
		private String phone;
	@NotEmpty
		private String education;
	@ManyToMany()
	@JoinTable(
		name = "student_course",
		joinColumns =  @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private List<CourseBean>courses;
	

}
/*
 * package com.studentRegistration.model;
 * 
 * import java.util.List;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.ManyToMany; import javax.persistence.OneToMany; import
 * javax.persistence.Table; import javax.validation.constraints.NotEmpty;
 * 
 * import org.springframework.stereotype.Component;
 * 
 * @Component
 * 
 * @Entity @Table(name="student") public class StudentBean {
 * 
 * @Id
 * 
 * @Column(name="stu_id")
 * 
 * @NotEmpty private String sid;
 * 
 * @Column(name="stu_name")
 * 
 * @NotEmpty private String name;
 * 
 * @Column(name="stu_dob")
 * 
 * @NotEmpty private String dob;
 * 
 * @Column(name="stu_gender")
 * 
 * @NotEmpty private String gender;
 * 
 * @Column(name="stu_phone")
 * 
 * @NotEmpty private String phone;
 * 
 * @NotEmpty
 * 
 * @Column(name="stu_education") private String education;
 * 
 * private List<CourseBean>courses;
 * 
 * @NotEmpty
 * 
 * @OneToMany
 * 
 * @JoinColumn(name="stu_course") private List<String> stuCourse;
 * 
 * 
 * public String getSid() { return sid; } public void setSid(String sid) {
 * this.sid = sid; } public String getName() { return name; } public void
 * setName(String name) { this.name = name; } public String getDob() { return
 * dob; } public void setDob(String dob) { this.dob = dob; } public String
 * getGender() { return gender; } public void setGender(String gender) {
 * this.gender = gender; } public String getPhone() { return phone; } public
 * void setPhone(String phone) { this.phone = phone; } public String
 * getEducation() { return education; } public void setEducation(String
 * education) { this.education = education; } public List<CourseBean>
 * getCourses() { return courses; } public void setCourses(List<CourseBean>
 * courses) { this.courses = courses; } public List<String> getStuCourse() {
 * return stuCourse; } public void setStuCourse(List<String> stuCourse) {
 * this.stuCourse = stuCourse; } public StudentBean() {
 * 
 * } public StudentBean(@NotEmpty String sid, @NotEmpty String name, @NotEmpty
 * String dob, @NotEmpty String gender,
 * 
 * @NotEmpty String phone, @NotEmpty String education, List<CourseBean> courses,
 * 
 * @NotEmpty List<String> stuCourse) { super(); this.sid = sid; this.name =
 * name; this.dob = dob; this.gender = gender; this.phone = phone;
 * this.education = education; this.courses = courses; this.stuCourse =
 * stuCourse; }
 * 
 * @Override public String toString() { return "StudentBean [sid=" + sid +
 * ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", phone=" + phone
 * + ", education=" + education + ", courses=" + courses + ", stuCourse=" +
 * stuCourse + "]"; }
 * 
 * 
 * }
 */
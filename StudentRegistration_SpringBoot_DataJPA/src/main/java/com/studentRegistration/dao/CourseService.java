package com.studentRegistration.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentRegistration.model.CourseBean;

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	public void insertCourse(CourseBean courseDto) {
		courseRepository.save(courseDto);
	
	}
	public List<CourseBean> selectAllCourse() {
		List<CourseBean>courseList=(List<CourseBean>)courseRepository.findAll();
				return courseList;
	}
	

}

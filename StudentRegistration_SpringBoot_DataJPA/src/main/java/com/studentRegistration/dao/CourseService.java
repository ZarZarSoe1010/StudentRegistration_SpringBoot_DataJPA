//package com.studentRegistration.dao;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import com.studentRegistration.dto.CourseDTO;
//
//@Service
//public class CourseService {
//	@Autowired
//	CourseRepository courseRepository;
//	
//	public int insertCourse(CourseDTO courseDto) {
//		int result=0;
//		courseRepository.save(courseDto);
//		return result;
//	}
//	public List<CourseDTO> selectAllCourse() {
//		List<CourseDTO>courseList=(List<CourseDTO>)courseRepository.findAll();
//				return courseList;
//	}
//	
//
//}

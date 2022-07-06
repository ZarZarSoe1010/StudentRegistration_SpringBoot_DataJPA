package com.studentRegistration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRegistration.model.CourseBean;


@Repository
public interface CourseRepository extends JpaRepository<CourseBean,String> {
	

}

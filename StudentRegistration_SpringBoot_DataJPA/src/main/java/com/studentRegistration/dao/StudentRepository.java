package com.studentRegistration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.studentRegistration.model.StudentBean;

@Repository
public interface StudentRepository extends JpaRepository<StudentBean, String> {
    StudentBean findBySid(String studentId);

    List<StudentBean> findDistinctBySidContainingOrNameContainingOrCourses_NameContaining(String stuId, String stuName,
            String courseName);

    // @Modifying
    // @Query(value="delete from student_course sc where sc.student_id = ?1",
    // nativeQuery = true)
    // void deleteStudent_Course(String id);
}

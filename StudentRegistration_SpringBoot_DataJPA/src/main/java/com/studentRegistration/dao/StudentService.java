package com.studentRegistration.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentRegistration.model.StudentBean;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository stuRepository;
    @Autowired
    CourseRepository courseRepository;

    public void insertStudent(StudentBean stuBean) {
        stuRepository.save(stuBean);
    }

    public void updateStudent(StudentBean stuBean) {
        stuRepository.save(stuBean);
    }

    public void deleteStudent(String stuId) {
        stuRepository.deleteById(stuId);
    }

    public List<StudentBean> selectAllStudent() {
        List<StudentBean> studentList = (List<StudentBean>)stuRepository.findAll();
        return studentList;
    }

    public StudentBean selectOneStudent(String studentId) {
        return stuRepository.findBySid(studentId);
    }

    public List<StudentBean> selectStudentListByIdOrNameOrCourse(String stuId, String stuName, String courseName) {
        String id = !stuId.isBlank() ? stuId : ("%/][");
        String name = !stuName.isBlank() ? stuName : ("%%");
        String course = !courseName.isBlank() ? courseName : ("%%");
        return stuRepository.findDistinctBySidContainingOrNameContainingOrCourses_NameContaining(id, name, course);

    }

}

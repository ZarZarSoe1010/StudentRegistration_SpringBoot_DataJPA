package com.studentRegistration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.studentRegistration.dao.StudentRepository;
import com.studentRegistration.dao.StudentService;
import com.studentRegistration.model.CourseBean;
import com.studentRegistration.model.StudentBean;

@SpringBootTest
public class StudentServiceTest {
    @Mock
    StudentRepository stuRepository;
    @InjectMocks
    StudentService stuService;

    @Test
    public void insertStudentTest() {
        StudentBean stuBean = getStudent();
        stuService.insertStudent(stuBean);
        verify(stuRepository, times(1)).save(stuBean);
    }

    @Test
    public void updateStudentTest() {
        StudentBean stuBean = getStudent();
        stuService.updateStudent(stuBean);
        verify(stuRepository, times(1)).save(stuBean);
    }

    @Test
    public void deleteStudentTest() {
        stuService.deleteStudent("STU001");
        verify(stuRepository, times(1)).deleteById("STU001");
    }

    @Test
    public void selectAllStudentTest() {
        List<StudentBean> list = getStudentList();
        when(stuRepository.findAll()).thenReturn(list);
        List<StudentBean> stuList = stuService.selectAllStudent();
        assertEquals(list.size(), stuList.size());
        verify(stuRepository, times(1)).findAll();

    }

    @Test
    public void selectOneStudentTest() {
        StudentBean stuBean = getStudent();
        when(stuRepository.findBySid("STU001")).thenReturn(stuBean);
        StudentBean selectedstu = stuService.selectOneStudent("STU001");
        assertEquals(stuBean.getName(), selectedstu.getName());
        assertEquals(stuBean.getDob(), selectedstu.getDob());
        assertEquals(stuBean.getGender(), selectedstu.getGender());
        assertEquals(stuBean.getPhone(), selectedstu.getPhone());
        assertEquals(stuBean.getEducation(), selectedstu.getEducation());
        assertEquals(stuBean.getCourses(), selectedstu.getCourses());
        verify(stuRepository, times(1)).findBySid("STU001");
    }

    @Test
    public void selectStudentListByIdOrNameOrCourseTest() {
        List<StudentBean> list = getStudentList();
        when(stuRepository
                .findDistinctBySidContainingOrNameContainingOrCourses_NameContaining("STU001", "zar", "java"))
                .thenReturn(list);
        List<StudentBean> selectList = stuService.selectStudentListByIdOrNameOrCourse("STU001", "zar", "java");
        assertEquals(list.size(), selectList.size());
    }

    @Test
    public void GetAllStuIfEmptyFilter() {
        List<StudentBean> stuList = getStudentList();
        when(stuRepository
                .findDistinctBySidContainingOrNameContainingOrCourses_NameContaining("%%", "%%", "%%"))
                .thenReturn(stuList);
        List<StudentBean> selectList = stuService.selectStudentListByIdOrNameOrCourse("", "", "");
        assertEquals(stuList.size(), selectList.size());
    }

    @Test
    public void GetEmptyStuListIfInvalidFilter() {
        List<StudentBean> stuList = getStudentList();

        when(stuRepository
                .findDistinctBySidContainingOrNameContainingOrCourses_NameContaining("STU001", "zar", "java"))
                .thenReturn(stuList);
        List<StudentBean> selectList = stuService.selectStudentListByIdOrNameOrCourse("STU005", "x", "b");
        assertEquals(0, selectList.size());

    }

    private List<CourseBean> getCourseList() {
        List<CourseBean> courseList = new ArrayList<CourseBean>();
        CourseBean course1 = new CourseBean("COU001", "Java");
        CourseBean course2 = new CourseBean("COU002", "C#");
        courseList.add(course1);
        courseList.add(course2);
        return courseList;
    }

    private StudentBean getStudent() {
        StudentBean stu1 = new StudentBean();
        stu1.setSid("STU001");
        stu1.setName("zar");
        stu1.setDob("10.10.20");
        stu1.setGender("female");
        stu1.setPhone("0987");
        stu1.setEducation("It");
        stu1.setCourses(getCourseList());
        return stu1;
    }

    private List<StudentBean> getStudentList() {
        List<StudentBean> list = new ArrayList<StudentBean>();
        StudentBean stu1 = new StudentBean();
        stu1.setSid("STU001");
        stu1.setName("zar");
        stu1.setDob("10.10.20");
        stu1.setGender("female");
        stu1.setPhone("0987");
        stu1.setEducation("It");
        stu1.setCourses(getCourseList());
        StudentBean stu2 = new StudentBean();
        stu1.setSid("STU002");
        stu1.setName("zar zar soe");
        stu1.setDob("10.10.20");
        stu1.setGender("female");
        stu1.setPhone("0987");
        stu1.setEducation("It");
        stu1.setCourses(getCourseList());
        list.add(stu1);
        list.add(stu2);
        return list;
    }
}

package com.studentRegistration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.studentRegistration.dao.StudentRepository;
import com.studentRegistration.dao.StudentService;
import com.studentRegistration.model.CourseBean;
import com.studentRegistration.model.StudentBean;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StudentService stuService;
    @MockBean
    StudentRepository stuRepository;

    @Test
    public void setupSearchStudentTest() throws Exception {
        this.mockMvc.perform(get("/setupSearchStudent"))
                .andExpect(status().isOk())
                .andExpect(view().name("STU003"));
    }

    @Test
    public void setupRegisterStudentTest() throws Exception {
        this.mockMvc.perform(get("/setupRegisterStudent"))
                .andExpect(status().isOk())
                .andExpect(view().name("STU001"))
                .andExpect(model().attributeExists("stuBean"));
    }

    @Test
    public void registerStudentTestOk() throws Exception {
        StudentBean stuBean = getStudent();
        this.mockMvc.perform(post("/registerStudent").flashAttr("stuBean", stuBean))
                .andExpect(status().isOk())
                .andExpect(view().name("STU001"))
                .andExpect(model().attributeExists("stuBean"));
    }

    @Test
    public void registerStudentTestFail() throws Exception {
        this.mockMvc.perform(post("/registerStudent"))
                .andExpect(status().isOk())
                .andExpect(view().name("STU001"))
                .andExpect(model().attributeExists("stuBean"));
    }

    @Test
    public void seeMoreTest() throws Exception {
        StudentBean stuBean = getStudent();
        when(stuService.selectOneStudent(stuBean.getSid())).thenReturn(stuBean);

        this.mockMvc.perform(get("/seeMore").param("selectedStudentId", stuBean.getSid()))
                .andExpect(status().isOk())
                .andExpect(view().name("STU002"))
                .andExpect(model().attributeExists("stuBean"));
    }

    @Test
    public void testUpdateStudentOk() throws Exception {
        StudentBean stuBean = getStudent();
        this.mockMvc.perform(post("/updateStudent").flashAttr("stuBean", stuBean))
                .andExpect(status().isOk())
                .andExpect(view().name("STU002"))
                .andExpect(model().attributeExists("stuBean"));
    }

    @Test
    public void testupdateStudentValidateFail() throws Exception {
        this.mockMvc.perform(post("/updateStudent"))
                .andExpect(status().isOk())
                .andExpect(view().name("STU002"))
                .andExpect(model().attributeExists("stuBean"));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        this.mockMvc.perform(get("/deleteStudent").param("selectedStudentId", "STU001"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/searchStudent?sid=&sname=&scourse="));
    }

    @Test
    public void SearchStudentTest() throws Exception {
        this.mockMvc.perform(get("/searchStudent")
                .param("sid", "STU001")
                .param("sname", "ZZ")
                .param("scourse", "java"))
                .andExpect(status().isOk())
                .andExpect(view().name("STU003"))
                .andExpect(model().attributeExists("stuList"));
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

}

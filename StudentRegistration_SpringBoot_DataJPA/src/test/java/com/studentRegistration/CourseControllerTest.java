package com.studentRegistration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.studentRegistration.dao.CourseRepository;
import com.studentRegistration.dao.CourseService;
import com.studentRegistration.model.CourseBean;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CourseService courseService;
    @MockBean
    CourseRepository courseRepo;

    @Test
    public void setupRegisterCourseTest()throws Exception{
        this.mockMvc.perform(get("/setupRegisterCourse"))
        .andExpect(status().isOk())
        .andExpect(view().name("BUD003"))
        .andExpect(model().attributeExists("courseBean"));
    }
    @Test
    public void registerCourseTestOk()throws Exception{
        CourseBean courseBean=new CourseBean("COU001","java");
        this.mockMvc.perform(post("/registerCourse"))
        .andExpect(status().isOk())
        .andExpect(view().name("BUD003"))
        .andExpect(model().attributeExists("courseBean"));
    }
    @Test
    public void registerCourseValidate()throws Exception{
        this.mockMvc.perform(post("/registerCourse"))
        .andExpect(status().isOk())
        .andExpect(view().name("BUD003"));
    }

    
}

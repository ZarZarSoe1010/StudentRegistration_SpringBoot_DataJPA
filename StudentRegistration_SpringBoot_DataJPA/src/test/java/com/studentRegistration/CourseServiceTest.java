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
import com.studentRegistration.dao.CourseRepository;
import com.studentRegistration.dao.CourseService;
import com.studentRegistration.model.CourseBean;

@SpringBootTest
public class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @InjectMocks
    CourseService courseService;

    @Test
    public void insertCourseTest() {
        CourseBean cBean = new CourseBean("COU001", "java");
        courseService.insertCourse(cBean);
        verify(courseRepository, times(1)).save(cBean);
    }

    @Test
    public void selectAllCourseTest() {
        List<CourseBean> list = new ArrayList<CourseBean>();
        CourseBean course1 = new CourseBean("COU001", "java");
        CourseBean course2 = new CourseBean("COU002", "java");
        CourseBean course3 = new CourseBean("COU003", "java");
        list.add(course1);
        list.add(course2);
        list.add(course3);
        when(courseRepository.findAll()).thenReturn(list);
        List<CourseBean> courseList = courseService.selectAllCourse();
        assertEquals(3, courseList.size());
        verify(courseRepository, times(1)).findAll();
    }

}

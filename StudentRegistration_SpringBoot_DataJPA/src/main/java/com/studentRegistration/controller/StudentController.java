package com.studentRegistration.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.studentRegistration.dao.CourseService;
import com.studentRegistration.dao.StudentService;
import com.studentRegistration.model.CourseBean;
import com.studentRegistration.model.StudentBean;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/setupSearchStudent", method = RequestMethod.GET)
	public String setupSearchStudent(ModelMap model) {
		return "STU003";
	}

	@RequestMapping(value = "/setupRegisterStudent", method = RequestMethod.GET)
	public ModelAndView setupRegisterStudent(ModelMap model) {
		List<CourseBean> courseList = courseService.selectAllCourse();
		model.addAttribute("courseList", courseList);

		List<StudentBean> stuList = studentService.selectAllStudent();
		StudentBean stuBean = new StudentBean();
		if (stuList == null) {
			stuList = new ArrayList<>();
		}
		if (stuList.size() == 0) {
			stuBean.setSid("STU001");
		} else {
			int tempId = Integer.parseInt(stuList.get(stuList.size() - 1).getSid().substring(3)) + 1;
			String stuId = String.format("STU%03d", tempId);
			stuBean.setSid(stuId);
		}
		return new ModelAndView("STU001", "stuBean", stuBean);
	}

	@RequestMapping(value = "/registerStudent", method = RequestMethod.POST)
	public ModelAndView registerStudent(@ModelAttribute("stuBean") @Validated StudentBean stuBean, BindingResult bs,
			ModelMap model) {
		if (bs.hasErrors()) {
			List<CourseBean> courseList = courseService.selectAllCourse();
			model.addAttribute("courseList", courseList);
			return new ModelAndView("STU001", "stuBean", stuBean);
		}
		studentService.insertStudent(stuBean);
		model.addAttribute("msg", "Register Successful");
		stuBean = new StudentBean();
		List<StudentBean> stuList = studentService.selectAllStudent();
		if (stuList == null) {
			stuList = new ArrayList<>();
		}
		if (stuList.size() == 0) {
			stuBean.setSid("STU001");
		} else {
			int tempId = Integer.parseInt(stuList.get(stuList.size() - 1).getSid().substring(3)) + 1;
			String stuId = String.format("STU%03d", tempId);
			stuBean.setSid(stuId);
		}
		List<CourseBean> courseList = courseService.selectAllCourse();
		model.addAttribute("courseList", courseList);
		return new ModelAndView("STU001", "stuBean", stuBean);
	}

	@RequestMapping(value = "/seeMore", method = RequestMethod.GET)
	public ModelAndView seeMore(@RequestParam("selectedStudentId") String id, ModelMap model) {
		StudentBean stuBean = studentService.selectOneStudent(id);
		// List<String> stuCourseIdList = studentService.selectCourseIdList(id);
		List<CourseBean> courseList = courseService.selectAllCourse();
		model.addAttribute("courseList", courseList);
		return new ModelAndView("STU002", "stuBean", stuBean);
	}

	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute("stuBean") @Validated StudentBean stuBean, BindingResult bs,
			ModelMap model) {
		List<CourseBean> courseList = courseService.selectAllCourse();
		model.addAttribute("courseList", courseList);
		if (bs.hasErrors()) {
			return new ModelAndView("STU002", "stuBean", stuBean);
		}
		studentService.updateStudent(stuBean);
		model.addAttribute("msg", " Update Successful!!!");
		return new ModelAndView("STU002", "stuBean", stuBean);
	}

	@RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
	public String deleteStudent(@RequestParam("selectedStudentId") String id, ModelMap model) {
		studentService.deleteStudent(id);
		return "redirect:/searchStudent?sid=&sname=&scourse=";
	}

	@RequestMapping(value = "/searchStudent", method = RequestMethod.GET)
	public ModelAndView searchStudent(@RequestParam("sid") String sid, @RequestParam("sname") String sname,
			@RequestParam("scourse") String scourse, ModelMap model) {
		List<StudentBean> stuBeanList = new ArrayList<StudentBean>();
		if (sid.isBlank() && sname.isBlank() && scourse.isBlank()) {
			stuBeanList = studentService.selectAllStudent();
		} else {
			stuBeanList = studentService.selectStudentListByIdOrNameOrCourse(sid, sname, scourse);
		}
		if (stuBeanList.size() == 0) {
			model.addAttribute("msg", "Student not found!!");
		}
		return new ModelAndView("STU003", "stuList", stuBeanList);

	}

}

//package com.studentRegistration.controller;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//import com.studentRegistration.dao.CourseService;
//import com.studentRegistration.dto.CourseDTO;
//import com.studentRegistration.model.CourseBean;
//
//
//
//@Controller
//public class CourseController {
//	@Autowired
//	private CourseService courseService;
//	@RequestMapping(value = "/setupRegisterCourse", method = RequestMethod.GET)
//	public ModelAndView setupRegisterCourse() {
//		CourseBean courseBean= new CourseBean();
//		List<CourseDTO> courseList =courseService.selectAllCourse();
//		if (courseList.size() == 0) {
//			courseBean.setCid("COU001");
//		} else {
//			int tempId = Integer.parseInt(courseList.get(courseList.size() - 1).getCid().substring(3)) + 1;
//			String courseId = String.format("COU%03d", tempId);
//			courseBean.setCid(courseId);
//		}
//		return new ModelAndView("BUD003","courseBean",courseBean);
//	}
//	@RequestMapping(value = "/registerCourse", method = RequestMethod.POST)
//	public String registerCourse(@ModelAttribute("courseBean") @Validated CourseBean courseBean, BindingResult bs,
//			ModelMap model) {
//		if (bs.hasErrors()) {
//			return "BUD003";
//		}
//			CourseDTO reqDto = new CourseDTO();
//			reqDto.setCid(courseBean.getCid());
//			reqDto.setName(courseBean.getName());	
//			int i=courseService.insertCourse(reqDto);
//			if(i>0) {
//			model.addAttribute("msg", "Register Succesful !!");
//			 courseBean=new CourseBean();
//			List<CourseDTO> courseList = courseService.selectAllCourse();
//			if (courseList.size() == 0) {
//				courseBean.setCid("COU001");
//			} else {
//				int tempId = Integer.parseInt(courseList.get(courseList.size() - 1).getCid().substring(3)) + 1;
//				String courseId = String.format("COU%03d", tempId);
//				courseBean.setCid(courseId);
//			}
//			}else {
//				model.addAttribute("msg","Register Failed!");
//			}
//			model.addAttribute("courseBean",courseBean);
//			return "BUD003";
//		}
//	
//
//}

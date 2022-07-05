/*
 * package com.studentRegistration.controller;
 * 
 * import java.util.ArrayList;
 * 
 * import java.util.List; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.ModelMap; import
 * org.springframework.validation.BindingResult; import
 * org.springframework.validation.annotation.Validated; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.servlet.ModelAndView; import
 * com.studentRegistration.dao.CourseService; import
 * com.studentRegistration.dao.StudentService; import
 * com.studentRegistration.dto.CourseDTO; import
 * com.studentRegistration.dto.StudentDTO; import
 * com.studentRegistration.model.CourseBean; import
 * com.studentRegistration.model.StudentBean;
 * 
 * @Controller public class StudentController {
 * 
 * @Autowired private StudentService studentService;
 * 
 * @Autowired private CourseService courseService;
 * 
 * @RequestMapping(value = "/setupSearchStudent", method = RequestMethod.GET)
 * public String setupSearchStudent(ModelMap model) { return "STU003"; }
 * 
 * @RequestMapping(value = "/setupRegisterStudent", method = RequestMethod.GET)
 * public ModelAndView setupRegisterStudent(ModelMap model) { List<CourseDTO>
 * courseListRes = courseService.selectAllCourse(); ArrayList<CourseBean>
 * courseListBean = new ArrayList<CourseBean>(); for (CourseDTO courseRes :
 * courseListRes) { CourseBean courseBean = new CourseBean();
 * courseBean.setCid(courseRes.getCid());
 * courseBean.setName(courseRes.getName()); courseListBean.add(courseBean); } //
 * model.addAttribute("courseList", courseListBean); List<StudentDTO> stuList =
 * studentService.selectAllStudent(); StudentBean stuBean = new StudentBean();
 * if (stuList == null) { stuList = new ArrayList<>(); } if (stuList.size() ==
 * 0) { stuBean.setSid("STU001"); } else { int tempId =
 * Integer.parseInt(stuList.get(stuList.size() - 1).getSid().substring(3)) + 1;
 * String stuId = String.format("STU%03d", tempId); stuBean.setSid(stuId);
 * stuBean.setCourses(courseListBean); } return new ModelAndView("STU001",
 * "stuBean", stuBean); }
 * 
 * @RequestMapping(value = "/registerStudent", method = RequestMethod.POST)
 * public ModelAndView registerStudent(@ModelAttribute("stuBean") @Validated
 * StudentBean stuBean, BindingResult bs, ModelMap model) { List<CourseDTO>
 * courseListRes = courseService.selectAllCourse(); ArrayList<CourseBean>
 * courseListBean = new ArrayList<CourseBean>(); for (CourseDTO courseRes :
 * courseListRes) { CourseBean courseBean = new CourseBean();
 * courseBean.setCid(courseRes.getCid());
 * courseBean.setName(courseRes.getName()); courseListBean.add(courseBean); } if
 * (bs.hasErrors()) { stuBean.setCourses(courseListBean); return new
 * ModelAndView("STU001","stuBean",stuBean); } StudentDTO dto = new
 * StudentDTO(); dto.setSid(stuBean.getId()); dto.setName(stuBean.getName());
 * dto.setDob(stuBean.getDob()); dto.setGender(stuBean.getGender());
 * dto.setPhone(stuBean.getPhone()); dto.setEducation(stuBean.getEducation());
 * int i = stuDao.insertStudent(dto); if (i > 0) { model.addAttribute("msg",
 * "Register Successful"); studentService.insertStudent_Course(stuBean.getId(),
 * stuBean.getStuCourse()); stuBean =new StudentBean(); List<StudentDTO> stuList
 * = studentService.selectAllStudent(); if (stuList == null) { stuList = new
 * ArrayList<>(); } if (stuList.size() == 0) { stuBean.setId("STU001"); } else {
 * int tempId = Integer.parseInt(stuList.get(stuList.size() -
 * 1).getSid().substring(3)) + 1; String stuId = String.format("STU%03d",
 * tempId); stuBean.setId(stuId); } } else { model.addAttribute("msg",
 * "Insert Fail!!"); } stuBean.setCourses(courseListBean); return new
 * ModelAndView("STU001","stuBean",stuBean); }
 * 
 * @RequestMapping(value = "/seeMore", method = RequestMethod.GET) public
 * ModelAndView seeMore(@RequestParam("selectedStudentId")String id, ModelMap
 * model) { StudentDTO studentInfo = studentService.selectOneStudent(id);
 * List<String> stuCourseIdList = studentService.selectCourseIdList(id);
 * List<CourseDTO> courseListRes = courseService.selectAllCourse();
 * ArrayList<CourseBean> courseListBean = new ArrayList<CourseBean>(); for
 * (CourseDTO courseRes : courseListRes) { CourseBean courseBean = new
 * CourseBean(); courseBean.setId(courseRes.getCid());
 * courseBean.setName(courseRes.getName()); courseListBean.add(courseBean); }
 * StudentBean stuBean = new StudentBean(); stuBean.setId(id);
 * stuBean.setName(studentInfo.getName()); stuBean.setDob(studentInfo.getDob());
 * stuBean.setGender(studentInfo.getGender());
 * stuBean.setPhone(studentInfo.getPhone());
 * stuBean.setEducation(studentInfo.getEducation());
 * stuBean.setCourses(courseListBean); stuBean.setStuCourse(stuCourseIdList);
 * //model.addAttribute("studentInfo", stuBean); //
 * model.addAttribute("courseList", courseListBean); return new
 * ModelAndView("STU002", "stuBean", stuBean); }
 * 
 * @RequestMapping(value = "/updateStudent", method = RequestMethod.POST) public
 * ModelAndView updateStudent(@ModelAttribute("stuBean") @Validated StudentBean
 * stuBean, BindingResult bs, ModelMap model) { List<CourseDTO> courseResList =
 * courseService.selectAllCourse(); ArrayList<CourseBean> courseBeanList = new
 * ArrayList<CourseBean>(); for (CourseDTO course : courseResList) { CourseBean
 * courseBean = new CourseBean(); courseBean.setId(course.getCid());
 * courseBean.setName(course.getName()); courseBeanList.add(courseBean); } if
 * (bs.hasErrors()) { stuBean.setCourses(courseBeanList); return new
 * ModelAndView ("STU002","stuBean",stuBean); } StudentDTO dto = new
 * StudentDTO(); dto.setSid(stuBean.getId()); dto.setName(stuBean.getName());
 * dto.setDob(stuBean.getDob()); dto.setGender(stuBean.getGender());
 * dto.setPhone(stuBean.getPhone()); dto.setEducation(stuBean.getEducation());
 * dto.setCourse(stuBean.getStuCourse()); int i = stuDao.updateStudent(dto); if
 * (i > 0) { model.addAttribute("msg", " Update Successful!!!");
 * stuDao.deleteStudent_Course(stuBean.getId());
 * stuDao.insertStudent_Course(stuBean.getId(), stuBean.getStuCourse()); } else
 * { model.addAttribute("msg", "Update Fail!!"); }
 * stuBean.setCourses(courseBeanList); return new ModelAndView
 * ("STU002","stuBean",stuBean); }
 * 
 * @RequestMapping(value = "/deleteStudent", method = RequestMethod.GET) public
 * String deleteStudent(@RequestParam("selectedStudentId")String id, ModelMap
 * model) { StudentDTO dto = new StudentDTO(); dto.setSid(id); int i =
 * stuDao.deleteStudent(id); if (i > 0) { model.addAttribute("msg",
 * "Delete Successful"); } else { model.addAttribute("msg", "Delete Fail"); }
 * return "STU003"; }
 * 
 * @RequestMapping(value = "/searchStudent", method = RequestMethod.GET) public
 * ModelAndView searchStudent(@RequestParam("sid") String
 * sid, @RequestParam("sname") String sname,
 * 
 * @RequestParam("scourse") String scourse, ModelMap model) { List<StudentDTO>
 * stuResList = new ArrayList<StudentDTO>(); ArrayList<StudentBean> stuBeanList
 * = new ArrayList<StudentBean>(); if (sid.isBlank() && sname.isBlank() &&
 * scourse.isBlank()) { stuResList = studentService.selectAllStudent(); } else {
 * stuResList = studentService.selectStudentListByIdOrNameOrCourse(sid, sname,
 * scourse); } if (stuResList.size() == 0) { model.addAttribute("msg",
 * "Student not found!!"); } else { for (StudentDTO stuRes : stuResList) {
 * List<String> courseNameList = stuDao.selectCourseNameList(stuRes.getSid());
 * StudentBean stuBean = new StudentBean(); stuBean.setId(stuRes.getSid());
 * stuBean.setName(stuRes.getName()); stuBean.setDob(stuRes.getDob());
 * stuBean.setGender(stuRes.getGender()); stuBean.setPhone(stuRes.getPhone());
 * stuBean.setEducation(stuRes.getEducation());
 * stuBean.setStuCourse(courseNameList); stuBeanList.add(stuBean); } }
 * //model.addAttribute("stuList", stuBeanList); // return "/STU003"; return new
 * ModelAndView("STU003","stuList",stuBeanList);
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 */
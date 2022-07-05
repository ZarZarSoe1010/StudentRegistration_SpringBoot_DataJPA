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
import com.studentRegistration.dao.UserService;
import com.studentRegistration.dto.UserDTO;
import com.studentRegistration.model.UserBean;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/userManagement", method = RequestMethod.GET)
	public String UserManagement(ModelMap model) {
		return "USR003";
	}

	@RequestMapping(value = "/setupRegisterUser", method = RequestMethod.GET)
	public ModelAndView setupRegisterUser() {
		return new ModelAndView("USR001", "userBean", new UserBean());
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult bs,
			ModelMap model) {
		if (bs.hasErrors()) {
			return "USR001";
		} else if (!userBean.getPassword().equals(userBean.getCpwd())) {
			model.addAttribute("msg", "Passwords do not match !!");
			model.addAttribute("userBean", userBean);
			return "USR001";
		}
		List<UserDTO> userList = userService.selectAllUser();
		if (userList.size() == 0) {
			userBean.setUid("USR001");
		} else {
			int tempId = Integer.parseInt(userList.get(userList.size() - 1).getUid().substring(3)) + 1;
			String userId = String.format("USR%03d", tempId);
			userBean.setUid(userId);
		}
		UserDTO reqDto = new UserDTO();
		reqDto.setUid(userBean.getUid());
		reqDto.setEmail(userBean.getEmail());
		reqDto.setName(userBean.getName());
		reqDto.setPassword(userBean.getPassword());
		reqDto.setCpwd(userBean.getCpwd());
		reqDto.setUserRole(userBean.getUserRole());
		int i = userService.insertUser(reqDto);
		if (i > 0) {
			model.addAttribute("msg", "Register Successful !!");
		} else {
			model.addAttribute("msg", "Register Failed!");
		}
		model.addAttribute("userBean", new UserBean());
		return "USR001";
	}

	@RequestMapping(value = "/setupUpdateUser", method = RequestMethod.GET)
	public ModelAndView setupUpdateUser(@RequestParam("selectedUserId")String id) {
		UserDTO reqDto = new UserDTO();
		reqDto.setUid(id);
		UserDTO resDto = userService.selectOneUser(id);
		UserBean userBean = new UserBean();
		userBean.setUid(resDto.getUid());
		userBean.setName(resDto.getName());
		userBean.setEmail(resDto.getEmail());
		userBean.setPassword(resDto.getPassword());
		userBean.setCpwd(resDto.getCpwd());
		userBean.setUserRole(resDto.getUserRole());
		return new ModelAndView("USR002", "userBean", userBean);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult bs,
			ModelMap model) {
		System.out.println(userBean);
		if (bs.hasErrors()) {
			return "USR002";
		}
		UserDTO reqDto = new UserDTO();
		reqDto.setUid(userBean.getUid());
		reqDto.setName(userBean.getName());
		reqDto.setEmail(userBean.getEmail());
		reqDto.setPassword(userBean.getPassword());
		reqDto.setCpwd(userBean.getCpwd());
		reqDto.setUserRole(userBean.getUserRole());
		int i = userService.updateUser(reqDto);
		if (!userBean.getPassword().equals(userBean.getCpwd())) {
			model.addAttribute("msg", "Passwords do not match !!");
			return "USR002";
		}
		if (i > 0) {
			model.addAttribute("msg", "Update Successful!!");
		} else {
			model.addAttribute("msg", "Update fail!!");
		}
		return "USR002";
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("selectedUserId")String id, ModelMap model) {
		UserDTO dto = new UserDTO();
		dto.setUid(id);
		int i = userService.deleteUser(id);
		if (i > 0) {
			model.addAttribute("msg", "Delete Successful");
		} else {
			model.addAttribute("msg", "Delete Fail");
		}
		return "USR003";
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam("id") String uid, @RequestParam("name") String uname, ModelMap model) {
		List<UserDTO> userResList = new ArrayList<UserDTO>();
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		if (uid.isBlank() && uname.isBlank()) {
			userResList = userService.selectAllUser();
		} else {
			userResList = userService.selectByFilter(uid,uname);
		}
		if (userResList.size() == 0) {
			model.addAttribute("msg", "User not found!!");
		} else {
			for (UserDTO userRes : userResList) {
				UserBean userBean = new UserBean();
				userBean.setUid(userRes.getUid());
				userBean.setName(userRes.getName());
				userBean.setEmail(userRes.getEmail());
				userBean.setPassword(userRes.getPassword());
				userBean.setCpwd(userRes.getCpwd());
				userBean.setUserRole(userRes.getUserRole());
				userBeanList.add(userBean);
			}
		}
		model.addAttribute("userList", userBeanList);
		return "USR003";
	}

	
	
	
	
}
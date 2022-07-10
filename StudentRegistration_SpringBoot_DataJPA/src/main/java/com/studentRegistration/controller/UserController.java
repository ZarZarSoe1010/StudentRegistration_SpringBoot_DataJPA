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
		List<UserBean> userList = userService.selectAllUser();
		if (userList.size() == 0) {
			userBean.setUid("USR001");
		} else {
			int tempId = Integer.parseInt(userList.get(userList.size() - 1).getUid().substring(3)) + 1;
			String userId = String.format("USR%03d", tempId);
			userBean.setUid(userId);
		}
		userService.insertUser(userBean);
		model.addAttribute("msg", "Register Successful !!");
		model.addAttribute("userBean", new UserBean());
		return "USR001";
	}

	@RequestMapping(value = "/setupUpdateUser", method = RequestMethod.GET)
	public ModelAndView setupUpdateUser(@RequestParam("selectedUserId") String id) {
		UserBean userBean = userService.selectOneUser(id);
		return new ModelAndView("USR002", "userBean", userBean);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userBean") @Validated UserBean userBean, BindingResult bs,
			ModelMap model) {
		if (bs.hasErrors()) {
			return "USR002";
		}
		userService.updateUser(userBean);
		if (!userBean.getPassword().equals(userBean.getCpwd())) {
			model.addAttribute("msg", "Passwords do not match !!");
			return "USR002";
		}
		model.addAttribute("msg", "Update Successful!!");
		return "USR002";
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("selectedUserId") String id, ModelMap model) {
		userService.deleteUser(id);

		model.addAttribute("msg", "Delete Successful");

		return "redirect:/searchUser?id=&name=";
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam("id") String uid, @RequestParam("name") String uname, ModelMap model) {
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		if (uid.isBlank() && uname.isBlank()) {
			userBeanList = userService.selectAllUser();
		} else {
			userBeanList = userService.selectByFilter(uid, uname);
		}
		if (userBeanList.size() == 0) {
			model.addAttribute("msg", "User not found!!");
		}

		model.addAttribute("userList", userBeanList);
		return "USR003";
	}

}
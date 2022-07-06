package com.studentRegistration.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.studentRegistration.dao.UserService;
import com.studentRegistration.model.UserBean;



@Controller
public class LoginLogoutController {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() { 
		return new ModelAndView("LGN001","userBean",new UserBean());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("stuBean") @Validated UserBean userBean, BindingResult bs,
			HttpSession session,ModelMap model) {		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String currentDate = formatter.format(date);		
		List<UserBean> userResList = userService.selectAllUser();
		for (UserBean userInfo : userResList) {
			if (userInfo.getUid().equals(userBean.getUid()) && userInfo.getPassword().equals(userBean.getPassword())) {
				session.setAttribute("userInfo", userInfo);
				session.setAttribute("date", currentDate);			
				return new ModelAndView("MNU001");			
			} 		
	}
		model.addAttribute("msg", "ID and password do not match");
		return new ModelAndView("LGN001","userBean",userBean);			
}
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(ModelMap model, HttpSession session) {
		session.removeAttribute("userInfo");
		session.invalidate();
		return new ModelAndView("LGN001","userBean",new UserBean());
	}	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String showWelcome() {
		return "MNU001";
	}
	

}

package com.studentRegistration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.studentRegistration.dao.UserRepository;
import com.studentRegistration.dao.UserService;
import com.studentRegistration.model.UserBean;

@SpringBootTest
@AutoConfigureMockMvc
public class UserController {
    @Autowired
	private MockMvc mockMvc;
    @MockBean 
    UserService userService;
    @MockBean
    UserRepository userRepository;
    //test userMangment Method
    @Test
	public void testUserManagement() throws Exception {
		this.mockMvc.perform(get("/userManagement"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR003"));
	}
    @Test
    public void testsetupRegisterUser()throws Exception{
        this.mockMvc.perform(get("/setupRegisterUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR001"))
		.andExpect(model().attributeExists("userBean"));
    }

    @Test
    public void testregisterUserOk()throws Exception{
        UserBean userBean = new UserBean("USR001", "zz", "zz@gmail", "pass", "pass", "Admin");
        this.mockMvc.perform(post("/registerUser").flashAttr("userBean",userBean))
        .andExpect(status().isOk())
		.andExpect(view().name("USR001"))	
        .andExpect(model().attributeExists("userBean"));

    }
    @Test
    public void testregisterUserValidate()throws Exception{
        this.mockMvc.perform(post("/registerUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR001"));	
    }

    @Test
    public void  testsetupUpdateUser()throws Exception{
        String uid="USR001";
        UserBean userBean = new UserBean("USR001", "zz", "zz@gmail", "pass", "pass", "User");
        when(userService.selectOneUser(uid)).thenReturn(userBean);

        this.mockMvc.perform(get("/setupUpdateUser").param("selectedUserId",uid))
        .andExpect(status().isOk())
		.andExpect(view().name("USR002"))	
       .andExpect(model().attributeExists("userBean"));
    }

    @Test
    public void testUpdateUserOk()throws Exception{
        String uid="USR001";
        UserBean userBean = new UserBean(uid, "zz", "zz@gmail", "pass", "pass", "User");
        this.mockMvc.perform(post("/updateUser").flashAttr("userBean", userBean))
		.andExpect(status().isOk())
		.andExpect(view().name("USR002"))
		.andExpect(model().attributeExists("userBean"));
    }

    @Test
    public void testupdateUserValidateFail()throws Exception{
        this.mockMvc.perform(post("/updateUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR002"));	
	}
    @Test
    public void testdeleteUser()throws Exception{
        this.mockMvc.perform(get("/deleteUser").param("selectedUserId","USR001"))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/searchUser?id=&name="));	
    }
    @Test
    public void testSearchUser()throws Exception{
        this.mockMvc.perform(get("/searchUser").param("id","USR001").param("name", "ZZ"))
        .andExpect(status().isOk())
        .andExpect(view().name("USR003"))
        .andExpect(model().attributeExists("userList"));
    }

    

    
}

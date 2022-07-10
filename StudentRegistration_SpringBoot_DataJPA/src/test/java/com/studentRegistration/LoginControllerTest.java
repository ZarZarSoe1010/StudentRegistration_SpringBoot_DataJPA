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

import com.studentRegistration.dao.UserService;
import com.studentRegistration.model.UserBean;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserService userService;

    @Test
    public void loginsetupTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("LGN001"))
                .andExpect(model().attributeExists("userBean"));
    }

    @Test
    public void loginTestOk() throws Exception {
        List<UserBean> userList = new ArrayList<UserBean>();
        UserBean user1 = new UserBean("USR001", "zar zar soe", "zz@gmail", "pass", "pass", "User");
        UserBean user2 = new UserBean("USR002", "zar", "zz@gmail", "pass", "pass", "User");
        UserBean user3 = new UserBean("USR003", "zar zar", "zz@gmail", "pass", "pass", "User");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        when(userService.selectAllUser()).thenReturn(userList);

        UserBean userBean = new UserBean("USR001", "zz", "zz@gmail", "pass", "pass", "User");
        this.mockMvc.perform(post("/login").flashAttr("userBean", userBean))
                .andExpect(status().isOk())
                .andExpect(view().name("MNU001"));
    }

    @Test
    public void loginTestFail() throws Exception {
        List<UserBean> userList = new ArrayList<UserBean>();
        UserBean user1 = new UserBean("USR001", "zar zar soe", "zz@gmail", "pass", "pass", "User");
        UserBean user2 = new UserBean("USR002", "zar", "zz@gmail", "pass", "pass", "User");
        UserBean user3 = new UserBean("USR003", "zar zar", "zz@gmail", "pass", "pass", "User");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        when(userService.selectAllUser()).thenReturn(userList);

        UserBean userBean = new UserBean("USR005", "zz", "zz@gmail", "pass", "pass", "User");
        this.mockMvc.perform(post("/login").flashAttr("userBean", userBean))
                .andExpect(status().isOk())
                .andExpect(view().name("LGN001"));
    }

    @Test
    public void logoutTest() throws Exception {
        this.mockMvc.perform(post("/logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("LGN001"));
    }

    // @Test
    // public void sessionTest()throws Exception{

    // }

}

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

import com.studentRegistration.dao.UserRepository;
import com.studentRegistration.dao.UserService;
import com.studentRegistration.model.UserBean;

@SpringBootTest
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    public void insertUserTest() {
        UserBean userBean = new UserBean("USR001", "zz", "zz@gamil", "pass", "pass", "User");
        userService.insertUser(userBean);
        verify(userRepository, times(1)).save(userBean);
    }

    @Test
    public void updateUsertest() {
        UserBean userBean = new UserBean("USR001", "zz", "zz@gamil", "pass", "pass", "User");
        userService.updateUser(userBean);
        verify(userRepository, times(1)).save(userBean);
    }

    @Test
    public void deleteUserTest() {
        userService.deleteUser("USR001");
        verify(userRepository, times(1)).deleteById("USR001");
    }

    @Test
    public void selectAllUserTest() {
        List<UserBean> list = new ArrayList<UserBean>();
        UserBean user1 = new UserBean("USR001", "zz", "zz@gmail", "pass", "pass", "User");
        UserBean user2 = new UserBean("USR002", "zz", "zz@gmail", "pass", "pass", "User");
        UserBean user3 = new UserBean("USR003", "zz", "zz@gmail", "pass", "pass", "User");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        when(userRepository.findAll()).thenReturn(list);
        List<UserBean> userList = userService.selectAllUser();
        assertEquals(list.size(), userList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void selectOneUserTest() {
        String uid="USR001";
        UserBean userBean = new UserBean(uid, "zz", "zz@gmail", "pass", "pass", "User");
        when(userRepository.findByUid(uid)).thenReturn(userBean);
        UserBean selectedUser = userService.selectOneUser(uid);
        assertEquals(userBean.getName(), selectedUser.getName());
        assertEquals("zz@gmail", selectedUser.getEmail());
        assertEquals("pass", selectedUser.getPassword());
        assertEquals("pass", selectedUser.getCpwd());
        assertEquals("User", selectedUser.getUserRole());
        verify(userRepository,times(1)).findByUid(uid);
    }

    @Test
    public void selectByFilterTest() {
        List<UserBean> userList = new ArrayList<UserBean>();
        UserBean user1 = new UserBean("USR001", "zar zar soe", "zz@gmail", "pass", "pass", "User");
        UserBean user2 = new UserBean("USR002", "zar", "zz@gmail", "pass", "pass", "User");
        UserBean user3 = new UserBean("USR003", "zar zar", "zz@gmail", "pass", "pass", "User");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        when(userRepository.findByUidOrName("USR001", "zar")).thenReturn(userList);
        List<UserBean> selectList = userService.selectByFilter("USR001", "zar");
        assertEquals(userList.size(), selectList.size());
    }

    @Test
    public void GetAllUserIfemptyfilter() {
        List<UserBean> userList = new ArrayList<UserBean>();
        UserBean user1 = new UserBean("USR001", "zar zar soe", "zz@gmail", "pass", "pass", "User");
        UserBean user2 = new UserBean("USR002", "zar", "zz@gmail", "pass", "pass", "User");
        UserBean user3 = new UserBean("USR003", "zar zar", "zz@gmail", "pass", "pass", "User");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        when(userRepository.findByUidOrName("","")).thenReturn(userList);
        
        List<UserBean> selectList = userService.selectByFilter("", "");
        assertEquals(userList.size(), selectList.size());
    }

    @Test
    public void GetEmptyUserListIfInvalidFilter() {
        List<UserBean> userList = new ArrayList<UserBean>();
        UserBean user1 = new UserBean("USR001", "zar zar soe", "zz@gmail", "pass", "pass", "User");
        UserBean user2 = new UserBean("USR002", "zar", "zz@gmail", "pass", "pass", "User");
        UserBean user3 = new UserBean("USR003", "zar zar", "zz@gmail", "pass", "pass", "User");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        when(userRepository.findByUidOrName("USR001","zar zar soe")).thenReturn(userList);

        List<UserBean> selectList = userService.selectByFilter("USR005", "xx");
        assertEquals(0, selectList.size());
    }


}

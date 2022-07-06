package com.studentRegistration.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentRegistration.model.UserBean;


@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public int insertUser(UserBean userBean) {
		int result=0;
		userRepository.save(userBean);
		return result;
	}
	public int updateUser(UserBean userBean) {
		int result=0;
		userRepository.save(userBean);
		return result;
	}
	public int deleteUser(String userId) {
		int result=0;
		userRepository.deleteById(userId);
		return result;
	}
	public List<UserBean>selectAllUser(){
		List<UserBean>userList=(List<UserBean>)userRepository.findAll();
		return userList;	
	}
	public UserBean selectOneUser(String userId) {
		return userRepository.findByUid(userId);	
	}
	public List<UserBean> selectByFilter(String userId,String userName){
		return userRepository.findByUidOrName(userId, userName);
	}

}

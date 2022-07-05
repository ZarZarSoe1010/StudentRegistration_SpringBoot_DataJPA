package com.studentRegistration.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentRegistration.dto.UserDTO;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public int insertUser(UserDTO userDto) {
		int result=0;
		userRepository.save(userDto);
		return result;
	}
	public int updateUser(UserDTO userDto) {
		int result=0;
		userRepository.save(userDto);
		return result;
	}
	public int deleteUser(String userId) {
		int result=0;
		userRepository.deleteById(userId);
		return result;
	}
	public List<UserDTO>selectAllUser(){
		List<UserDTO>userList=(List<UserDTO>)userRepository.findAll();
		return userList;	
	}
	public UserDTO selectOneUser(String userId) {
		return userRepository.findUserDTOByUid(userId);	
	}
	public List<UserDTO> selectByFilter(String userId,String userName){
		return userRepository.selectByFilter(userId,userName);
	}

}

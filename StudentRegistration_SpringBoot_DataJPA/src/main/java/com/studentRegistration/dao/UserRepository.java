package com.studentRegistration.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.studentRegistration.dto.UserDTO;

@Repository
public interface UserRepository  extends JpaRepository<UserDTO,String>{
	
	UserDTO findUserDTOByUid(String userId);
//		UserDTO findByUid(String id);
//		
		@Query(value="select * from user u where u.user_id like ? or u.user_name like ?",nativeQuery=true)
		List<UserDTO>selectByFilter(String userId,String userName);
}

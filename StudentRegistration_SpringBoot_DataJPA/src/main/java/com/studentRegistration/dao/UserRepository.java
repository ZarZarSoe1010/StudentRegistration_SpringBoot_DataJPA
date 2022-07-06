package com.studentRegistration.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.studentRegistration.model.UserBean;

@Repository
public interface UserRepository  extends JpaRepository<UserBean,String>{
	
		UserBean findByUid(String id);	

		//@Query(value="select * from user u where u.user_id like ?1 or u.user_name like ?1",nativeQuery=true)
		//List<UserBean>selectByFilter(String userId,String userName);
		List<UserBean> findByUidOrName(String userId, String name);
}


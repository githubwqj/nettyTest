package com.wqj.myannotation.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wqj.myannotation.annotation.NettyProvider;
import com.wqj.myannotation.common.service.IUserService;
import com.wqj.pojo.User;

@NettyProvider
public class UserServiceImpl  implements IUserService{

	public List<User> findAll(Integer id) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		User user1 = new User();
		user1.setId(1);
		user1.setAge(12);
		user1.setName("張三");
		user1.setSex("女");
		User user2 = new User();
		user2.setId(2);
		user2.setAge(15);
		user2.setName("李四");
		user2.setSex("女");
		User user3 = new User();
		user3.setId(3);
		user3.setAge(16);
		user3.setName("王五");
		user3.setSex("女");
		User user4 = new User();
		user4.setId(4);
		user4.setAge(18);
		user4.setName("趙六");
		user4.setSex("女");
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);
		if (id != null) {
			for (int i= list.size()-1;i>=0;i--) {
				if (list.get(i).getId().intValue() != id.intValue()) {
					list.remove(i);
				}
			}
		}
		return list;
	}

	public String hello(String str) {
		// TODO Auto-generated method stub
		return "你好"+str;
	}

	public List<User> findAlls(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

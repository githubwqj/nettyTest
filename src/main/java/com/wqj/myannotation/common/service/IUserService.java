package com.wqj.myannotation.common.service;

import java.util.List;

import com.wqj.myannotation.annotation.NettyCustomer;
import com.wqj.pojo.User;

@NettyCustomer
public interface IUserService {

	/**
	 * @Description: 查詢數據 author wqj
	 * @param id
	 * @return
	 */
	public List<User> findAll(Integer id);

	public String hello(String str);
	
	public List<User> findAlls(Integer id);
}

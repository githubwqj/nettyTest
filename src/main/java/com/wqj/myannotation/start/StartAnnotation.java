package com.wqj.myannotation.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: 启动注解的主类
 * @author wqj
 * @date 2018年3月14日 下午4:53:38 
 */
public class StartAnnotation {

	public static void main(String[] args) {
			new	ClassPathXmlApplicationContext("config/StartSpringContext.xml");
	}
}

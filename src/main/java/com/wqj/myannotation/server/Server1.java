package com.wqj.myannotation.server;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.wqj.myannotation.annotation.NettyCustomer;
import com.wqj.myannotation.annotation.NettyProvider;

@Component
public class Server1 implements ApplicationContextAware {

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		// TODO Auto-generated method stub
		Map<String, Object> customerMap = ctx.getBeansWithAnnotation(NettyCustomer.class);
		for (Entry<String, Object> entryset : customerMap.entrySet()) {
			System.out.println("消费者:" + entryset.getKey() + ":" + entryset.getValue());
//			Method method=entryset.getClass()
		}

		Map<String, Object> providerMap = ctx.getBeansWithAnnotation(NettyProvider.class);
		for (Entry<String, Object> entryset : providerMap.entrySet()) {
			System.out.println("服务者:" + entryset.getKey() + ":" + entryset.getValue());
		}

	}

}

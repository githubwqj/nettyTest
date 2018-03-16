package com.wqj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.wqj.mynetty.step3.client.ClientHandler3;
import com.wqj.pojo.NettyRequset;
import com.wqj.zk.discover.ServiceDiscovery;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @Description: 客户端使用代理模式调用服务
 * @author wqj
 * @date 2018年3月16日 下午2:59:01 
 */
public class NettyClientProxy {
	private String serverAddress;

	private ServiceDiscovery serviceDiscovery;
	public NettyClientProxy(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<?> interfaceClass) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass} , new InvocationHandler() {

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				NettyRequset nettyRequset=new NettyRequset();
				nettyRequset.setClassName(method.getDeclaringClass().getName());
				nettyRequset.setMethodName(method.getName());
				nettyRequset.setObjects(args);
				nettyRequset.setTypes(method.getParameterTypes());
				return null;
			}
			
			
		});
	}
	
	
	
	
	
	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	


}

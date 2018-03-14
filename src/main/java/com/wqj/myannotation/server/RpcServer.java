package com.wqj.myannotation.server;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wqj.myannotation.annotation.NettyCustomer;
import com.wqj.myannotation.annotation.NettyProvider;
import com.wqj.mynetty.step2.server.ServerHandler2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class RpcServer implements ApplicationContextAware,InitializingBean{

	private String serverAddres;
	
//	private String
	
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
		EventLoopGroup workLoopGroup = new NioEventLoopGroup();
		try {
			// server端引导类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossLoopGroup, workLoopGroup)// 多线程处理
					.channel(NioServerSocketChannel.class)// 指定通道类型为nioscoket类型
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel channel) throws Exception {
							// TODO Auto-generated method stub
							//添加对象解码器 负责对序列化POJO对象进行解码 设置对象序列化最大长度为1M 防止内存溢出
							//设置线程安全的WeakReferenceMap对类加载器进行缓存 支持多线程并发访问  防止内存溢出 
							channel.pipeline().addLast(new ObjectDecoder(1024*1024,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
							//添加对象编码器 在服务器对外发送消息的时候自动将实现序列化的POJO对象编码
							channel.pipeline().addLast(new ObjectEncoder());
							channel.pipeline().addLast(new ServerHandler2());
						}
					})
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			// 最后绑定服务器等待直接绑定完成,调用sync()方法阻塞知道服务器完成绑定,然后等待通道关闭,sync是同步锁
			ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
			System.out.println("开始监听端口" + channelFuture.channel());
			channelFuture.channel().closeFuture().sync();
			System.out.println("已关闭通道:" + channelFuture.channel());
		} finally {
			bossLoopGroup.shutdownGracefully().sync();
			workLoopGroup.shutdownGracefully().sync();
		}

	}

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		// TODO Auto-generated method stub
		Map<String, Object> customerMap = ctx.getBeansWithAnnotation(NettyProvider.class);
		for (Entry<String, Object> entryset : customerMap.entrySet()) {
			System.out.println("消费者:" + entryset.getKey() + ":" + entryset.getValue());
			try {
				Method method = entryset.getValue().getClass().getMethod("findAll", new Class[] { Integer.class });
				Object invoke = method.invoke(entryset.getValue(), 1);
				System.out.println(invoke);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getServerAddres() {
		return serverAddres;
	}

	public void setServerAddres(String serverAddres) {
		this.serverAddres = serverAddres;
	}

}

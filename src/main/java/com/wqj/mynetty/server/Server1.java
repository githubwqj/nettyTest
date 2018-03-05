package com.wqj.mynetty.server;

import java.net.InetSocketAddress;

import org.apache.log4j.chainsaw.Main;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Server1 {

	private final int port;
	
	public Server1(int port) {
		this.port = port;
	}
	
	
	public void startServer() throws Exception{
		EventLoopGroup eventLoopGroup = null;
		try {
			//server端引导类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			eventLoopGroup = new NioEventLoopGroup();
			serverBootstrap.group(eventLoopGroup)//多线程处理
			.channel(NioServerSocketChannel.class)//指定通道类型为nioscoket类型
			.localAddress("localhost", port)
			.childHandler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel channel) throws Exception {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		//最后绑定服务器等待直接绑定完成,调用sync()方法阻塞知道服务器完成绑定,然后等待通道关闭,sync是同步锁	
		ChannelFuture channelFuture = serverBootstrap.bind().sync();
		System.out.println("开始监听端口"+channelFuture.channel());
		channelFuture.channel().close().sync();
		}finally {
			eventLoopGroup.shutdownGracefully().sync();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new Server1(2000).startServer();
	}
}

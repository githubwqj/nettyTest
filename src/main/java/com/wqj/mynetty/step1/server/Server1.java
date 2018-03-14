package com.wqj.mynetty.step1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server1 {

	private final int port;

	public Server1(int port) {
		this.port = port;
	}

	public void startServer() throws Exception {
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
							channel.pipeline().addLast(new ServerHandler1());
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

			// 最后绑定服务器等待直接绑定完成,调用sync()方法阻塞知道服务器完成绑定,然后等待通道关闭,sync是同步锁
			ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
			System.out.println("开始监听端口" + channelFuture.channel());
			channelFuture.channel().closeFuture().sync();
			System.out.println("已关闭通道:" + channelFuture.channel());
		} finally {
			bossLoopGroup.shutdownGracefully().sync();
			workLoopGroup.shutdownGracefully().sync();
		}

	}

	public static void main(String[] args) throws Exception {

		new Server1(8080).startServer();
	}
}

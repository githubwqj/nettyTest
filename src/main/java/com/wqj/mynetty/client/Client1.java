package com.wqj.mynetty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client1 {

	private final String host;
	private final int port;
	
	public Client1(String host,int port) {
		this.host = host;
		this.port = port;
	}
	
	public void startConnect() throws Exception{
		EventLoopGroup nioEventLoopGroup=null;
		try {
		Bootstrap bootstrap=new Bootstrap();
		nioEventLoopGroup =new NioEventLoopGroup();
		bootstrap.group(nioEventLoopGroup)//多线程处理
				.channel(NioSocketChannel.class)//指定通道类型为nioscoket类型
				.option(ChannelOption.SO_KEEPALIVE, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						// TODO Auto-generated method stub
						socketChannel.pipeline().addLast(new ClientHandler1());
					}
				});
		//连接服务器
		ChannelFuture channelFuture=bootstrap.connect(host,port).sync();
		//等到其拿到结果 才将其关闭
		channelFuture.channel().closeFuture().sync();
		//最终执行,关闭
		}  finally {
			// 流 需要层层关闭
			nioEventLoopGroup.shutdownGracefully().sync();
	}
	}
	
	public static void main(String[] args) throws Exception {
		new Client1("localhost",8080).startConnect();
	}
}

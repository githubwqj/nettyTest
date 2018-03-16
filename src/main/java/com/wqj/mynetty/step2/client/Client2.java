package com.wqj.mynetty.step2.client;

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

public class Client2 {

	private final String host;
	private final int port;

	public Client2(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void startConnect() throws Exception {
		EventLoopGroup nioEventLoopGroup = null;
		try {
			Bootstrap bootstrap = new Bootstrap();
			nioEventLoopGroup = new NioEventLoopGroup();
			bootstrap.group(nioEventLoopGroup)// 多线程处理
					.channel(NioSocketChannel.class)// 指定通道类型为nioscoket类型
					.option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							// TODO Auto-generated method stub
							//添加对象解码器 负责对序列化POJO对象进行解码 设置对象序列化最大长度为1M 防止内存溢出
							//设置线程安全的WeakReferenceMap对类加载器进行缓存 支持多线程并发访问  防止内存溢出 
							socketChannel.pipeline().addLast(new ObjectDecoder(1024*1024,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
							//添加对象编码器 在服务器对外发送消息的时候自动将实现序列化的POJO对象编码
							socketChannel.pipeline().addLast(new ObjectEncoder());
							socketChannel.pipeline().addLast(new ClientHandler2());
						}
					});
			// 连接服务器
			ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
			// 等到其拿到结果 才将其关闭
			channelFuture.channel().closeFuture().sync();
			// 最终执行,关闭
		} finally {
			// 流 需要层层关闭
			nioEventLoopGroup.shutdownGracefully().sync();
		}
	}

}

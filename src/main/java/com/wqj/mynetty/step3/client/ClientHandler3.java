package com.wqj.mynetty.step3.client;

import com.wqj.pojo.Student;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler3 extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接上服务器,准备发送数据1");
		// Send the message to Server
		Student student =new Student();
		student.setAge(18);
		student.setName("张三");
		System.out.println("连接上服务器,准备发送数据2");
		ctx.writeAndFlush(student);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		System.out.println("接收到服务端消息:"+msg);
		System.out.println(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		ctx.close();
	}
}

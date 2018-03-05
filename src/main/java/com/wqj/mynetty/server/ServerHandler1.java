package com.wqj.mynetty.server;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class ServerHandler1 extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进行数据读取");
		ByteBuf buf=(ByteBuf) msg;
		byte[] req=new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body=new String(req,"UTF-8");
		System.out.println("客户端接受的数据:"+body);
		
		//向客户端写数据
		System.out.println("Server向客户端发送数据");
		Date currentTime=new Date(System.currentTimeMillis());
		ByteBuf respone= Unpooled.copiedBuffer(currentTime.toString().getBytes());
		ctx.write(respone);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("数据读取完成");
		super.channelReadComplete(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("发生异常");
		super.exceptionCaught(ctx, cause);
	}
	
	
}

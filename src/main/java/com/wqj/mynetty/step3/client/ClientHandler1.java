package com.wqj.mynetty.step3.client;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler1 extends ChannelInboundHandlerAdapter{

	 @Override
	    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	        // Send the message to Server
	        ctx.writeAndFlush("firstMessage".getBytes());
	    }

	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
	        ByteBuf buf = (ByteBuf) msg;
	        byte[] req = new byte[buf.readableBytes()];
	        buf.readBytes(req);
	        try {
	            String body = new String(req, "UTF-8");
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
	        // Close the connection when an exception is raised.
	        ctx.close();
	    }
}

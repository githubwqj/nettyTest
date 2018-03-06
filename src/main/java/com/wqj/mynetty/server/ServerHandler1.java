package com.wqj.mynetty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ServerHandler1 extends ChannelInboundHandlerAdapter{

//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		// TODO Auto-generated method stub
////		super.channelRead(ctx, msg);
//		((ByteBuf)msg).release();
//	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
//		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		 final ByteBuf time = ctx.alloc().buffer(4); // (2)
	        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
	        
	        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
	        f.addListener(new ChannelFutureListener() {
				
				public void operationComplete(ChannelFuture future) throws Exception {
					// TODO Auto-generated method stub
					assert f == future;
					ctx.close();
				}
			});
	}

	
	
}

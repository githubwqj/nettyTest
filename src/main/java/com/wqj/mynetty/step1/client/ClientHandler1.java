package com.wqj.mynetty.step1.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler1 extends ChannelInboundHandlerAdapter {

	/* 此方法在客户端在链接到服务器时会被调用 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// Send the message to Server
		System.out.println("客户端发送消息去服务端");
//		final ByteBuf time = ctx.alloc().buffer(4);
//		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
		ByteBuf req =	Unpooled.copiedBuffer("客户端消息".getBytes());
		ctx.write(req);
		ctx.flush();
	}

	/* 此方法在客户端在接收到服务器消息时会被调用 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		System.out.println("客户端收到服务端消息");
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		try {
			String body = new String(req, "UTF-8");
			System.out.println("接受到的消息:" + body);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ctx.close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		ctx.close();
	}
}

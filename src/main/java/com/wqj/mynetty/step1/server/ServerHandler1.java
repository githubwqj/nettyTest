/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.wqj.mynetty.step1.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class ServerHandler1 extends ChannelInboundHandlerAdapter {

	/* 接收到数据时调用 */
	 @Override
	 public void channelRead(ChannelHandlerContext ctx, Object msg) {
	 System.out.println("接受到客户端发来的消息");
	 ByteBuf bf =(ByteBuf) msg;
	 byte[] req = new byte[bf.readableBytes()];
	 bf.readBytes(req);
	 String body = new String(req);
	 System.out.println(body);
	 ByteBuf resp = Unpooled.copiedBuffer("服务端消息".getBytes());
	 ctx.writeAndFlush(resp);
	 ctx.close();
	 }


	/* 用起来代替channelRead */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
//		final ByteBuf time = ctx.alloc().buffer(4);
//		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
		System.out.println("有客户端连接服务器");
//		final ChannelFuture f = ctx.writeAndFlush(time);
//		System.out.println("已将信息发出去");
//		f.addListener(new ChannelFutureListener() {
//			public void operationComplete(ChannelFuture future) {
//				assert f == future;
//				ctx.close();
//			}
//		});
		// 或者定义一个简单的监听器
		// f.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}

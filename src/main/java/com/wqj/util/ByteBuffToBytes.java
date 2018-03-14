package com.wqj.util;

import io.netty.buffer.ByteBuf;

public class ByteBuffToBytes {
	  /**
     * @Description: 将netty自帶的ByteBuf转为bytes
     * author wqj
     * @param datas
     * @return
     */
    public byte[] read(ByteBuf datas) {
        byte[] bytes = new byte[datas.readableBytes()];
        datas.readBytes(bytes);
        return bytes;
    }
}

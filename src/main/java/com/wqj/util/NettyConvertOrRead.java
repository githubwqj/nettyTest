package com.wqj.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.netty.buffer.ByteBuf;

/**
 * @Description: netty中类型转换和读取流
 * @author wqj
 * @date 2018年3月14日 上午11:32:08 
 */
public class NettyConvertOrRead {
	
	
	 /**
	 * @Description: 字节流转为object
	 * author wqj
	 * @param bytes
	 * @return
	 */
	public static Object byteToObject(byte[] bytes) {
	        Object obj = null;
	        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
	        ObjectInputStream oi = null;
	        try {
	            oi = new ObjectInputStream(bi);
	            obj = oi.readObject();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                bi.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                oi.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return obj;
	    }

	    /**
	     * @Description: Object转为字节流
	     * author wqj
	     * @param obj
	     * @return
	     */
	    public static byte[] objectToByte(Object obj) {
	        byte[] bytes = null;
	        ByteArrayOutputStream bo = new ByteArrayOutputStream();
	        ObjectOutputStream oo = null;
	        try {
	            oo = new ObjectOutputStream(bo);
	            oo.writeObject(obj);
	            bytes = bo.toByteArray();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                bo.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                oo.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return bytes;
	    }
}

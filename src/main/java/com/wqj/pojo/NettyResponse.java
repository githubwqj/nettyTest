package com.wqj.pojo;

import java.io.Serializable;

import com.wqj.myenum.RPCStatus;

/**
 * @Description: TODO
 * @author wqj
 * @date 2018年3月15日 下午4:28:35 
 */
public class NettyResponse implements Serializable{

	private Object response; //返回值
	
	private RPCStatus rpcStatus; //状态值
	
	

	public RPCStatus getRpcStatus() {
		return rpcStatus;
	}

	public void setRpcStatus(RPCStatus rpcStatus) {
		this.rpcStatus = rpcStatus;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
	
}

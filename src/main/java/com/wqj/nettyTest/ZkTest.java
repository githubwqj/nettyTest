package com.wqj.nettyTest;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class ZkTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZkClient client=new ZkClient("192.168.8.144:2181",20);
		//创建节点
		if(client.exists("/wuqingjie")) {
//			client.createPersistent("/wuqingjie");
			client.create("/wuqingjie/test", "test",CreateMode.EPHEMERAL);
			client.create("/wuqingjie/test2", "test2",CreateMode.EPHEMERAL);
			client.create("/wuqingjie/test3", "test3",CreateMode.EPHEMERAL);
		}
		
		//创建子节点
//		client.createPersistent("/test/1","1");
		
		while (true) {
			
			
		}
	}

}

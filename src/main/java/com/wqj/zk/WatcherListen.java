package com.wqj.zk;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

public class WatcherListen {
	public static void main(String[] args) {
		final ZkClient client=new ZkClient("192.168.8.144:2181");
		client.subscribeChildChanges("/wuqingjie", new IZkChildListener() {
			
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				// TODO Auto-generated method stub
				System.out.println(client.readData(parentPath));
				System.out.println(parentPath);
				System.out.println(currentChilds);
			}
		});
		
		
		while(true) {
			
		}
	}
}

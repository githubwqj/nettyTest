package com.wqj.mynetty.server;

import java.util.LinkedList;
import java.util.List;

public class Test {
    
    
   public static void main(String[] args) {
    	List<Object> list1=new LinkedList<Object>();
    	list1.add("qwe");
    	list1.add("qwe");
    	
    	List<Object> list2=new LinkedList<Object>();
    	list2.add("qwe");
    	list2.add(new String("qwe"));
    	System.out.println(list1.get(0)==list2.get(0));
    	System.out.println("--------------------");
    	System.out.println(list1.get(1)==list2.get(1));
    }
    
}

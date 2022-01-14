package com.bjpowernode.crm.utils;

import java.util.UUID;

/**
 * 获取UUID
 */
public class UUIDUtil {
	
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replaceAll("-","");
		
	}
	
}

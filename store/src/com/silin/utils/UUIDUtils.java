package com.silin.utils;

import java.util.UUID;

public class UUIDUtils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getUUID64(){
		return getUUID() + getUUID();
	}
}

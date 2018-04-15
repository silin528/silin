package com.silin.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MyBeanUtils {
	 public static <T> T populate(Class<T> beanClass,Map<String, String[]> properties) {  
	        try {  
	            T bean = beanClass.newInstance();  
	            BeanUtils.populate(bean, properties);
	            
	            return bean;  
	        } catch (Exception e) {  
	        	throw new RuntimeException(e);
	        }  
	       
	    }  
		
	
}

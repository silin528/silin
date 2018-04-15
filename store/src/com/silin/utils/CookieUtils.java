package com.silin.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
	public static Cookie getCookie(Cookie[] allCookie,String cookieName){
		if(cookieName == null){
			return null;
		}
		if(allCookie != null){
			for(Cookie c : allCookie){
				if(cookieName.equals(c.getName())){
					return c;
				}
			}
		}
		return null;
	}

	public static Cookie findCookie(Cookie[] cookies, String string) {
		// TODO Auto-generated method stub
		return null;
	}
}

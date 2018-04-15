package com.silin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.User;
import com.silin.service.UserService;
import com.silin.utils.CookieUtils;


public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
//		//如果是登录页直接放行
//		String servletPath = request.getServletPath();
//		if(servletPath.startsWith("serlvet/UserServlet")){
//			arg2.doFilter(request, response);
//			return;
//		}
//		//用户登录信息
//		User loginUser = (User)request.getSession().getAttribute("loginUser");
//		//如果已经登录，放行，不需要自动登录
//		if(loginUser != null){
//			arg2.doFilter(request, response);
//			return;
//		}
//		
//		//获取自动登录cookie信息
//		Cookie userCookie = CookieUtils.findCookie(request.getCookies() ,"autoLoginCookie");
//		//判断自动登录cookie是否存在，如果没有cookie，不需要自动
//		if(userCookie == null){
//			arg2.doFilter(request, response);
//			return;
//		}
//		
//		//通过用户cookie中记录，查询用户
//		String[] u = userCookie.getValue().split("@");
//		String username = u[0];
//		String password = u[1];
////		User user = new User(username,password);
//		try{
//			UserService userService = new UserService();
////			loginUser = userService.login(user);
//			if(loginUser == null){
//				arg2.doFilter(request, response);
//				return;
//			}
//			request.getSession().setAttribute("loginUser", loginUser);
//			arg2.doFilter(request, response);
//		}catch(Exception e){
//			System.out.println("自动登录，请忽略");
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}


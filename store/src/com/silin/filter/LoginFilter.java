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
		
//		//����ǵ�¼ҳֱ�ӷ���
//		String servletPath = request.getServletPath();
//		if(servletPath.startsWith("serlvet/UserServlet")){
//			arg2.doFilter(request, response);
//			return;
//		}
//		//�û���¼��Ϣ
//		User loginUser = (User)request.getSession().getAttribute("loginUser");
//		//����Ѿ���¼�����У�����Ҫ�Զ���¼
//		if(loginUser != null){
//			arg2.doFilter(request, response);
//			return;
//		}
//		
//		//��ȡ�Զ���¼cookie��Ϣ
//		Cookie userCookie = CookieUtils.findCookie(request.getCookies() ,"autoLoginCookie");
//		//�ж��Զ���¼cookie�Ƿ���ڣ����û��cookie������Ҫ�Զ�
//		if(userCookie == null){
//			arg2.doFilter(request, response);
//			return;
//		}
//		
//		//ͨ���û�cookie�м�¼����ѯ�û�
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
//			System.out.println("�Զ���¼�������");
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}


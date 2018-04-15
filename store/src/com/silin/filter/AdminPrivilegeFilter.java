package com.silin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.User;

public class AdminPrivilegeFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//1.强制类型转换
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		//2.判断是否具有权限
		User user = (User)request.getSession().getAttribute("user");
		if(user != null && "admin".equals(user.getUserName())){
			//放行
			arg2.doFilter(request, response);
			response.sendRedirect(request.getContextPath() + "/admin/product/addProduct.jsp");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/error/privilege.jsp");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

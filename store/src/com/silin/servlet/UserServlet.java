package com.silin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.User;
import com.silin.service.UserService;
import com.silin.utils.MailUtils;
import com.silin.utils.MyBeanUtils;
import com.silin.utils.UUIDUtils;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if("RegisterServlet".equals(methodName)){
			RegisterServlet(request, response);
		}else if("LogoutServlet".equals(methodName)){
			System.out.println("123");
			LogoutServlet(request,response);
		}else if("LoginServlet".equals(methodName)){
			LoginServlet(request,response);
		}
	}
	
	//注册模块
	public void RegisterServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取数据并封装
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		user.setUid(UUIDUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);//未激活=0
		String activeCode = UUIDUtils.getUUID();
		user.setCode(activeCode);//激活码
		
		//将user传递给Service层
		UserService service = new UserService();
		boolean isRegisterSuccess = service.register(user);
		//是否注册成功
		if(isRegisterSuccess){
			//发送激活邮件
			String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
					+ "<a href='http://localhost:8080/store/servlet/ActiveServlet?activeCode="+activeCode+"'>"
							+ "http://localhost:8080/store/servlet/ActiveServlet?activeCode="+activeCode+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
				
			//跳转到注册成功页面
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			//跳转到失败的提示页面
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
	}
	
	//没有被用到，异步验证用户名是否被注册
	public void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//接收文本框的值
		String username = request.getParameter("username");
		//调用业务层查询
		UserService userService = new UserService();
		User existUser = userService.findByUsername(username);
		//判断
		if(existUser == null){
			//没有用户名
			response.getWriter().print(1);
		}else{
			//用户名被使用
			response.getWriter().print(2);
		}
	}

	
//登录
	//登录
	public void LoginServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取账号和密码的参数
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		//调用service完成登录
		UserService service = new UserService();
		try{
			User loginUser = service.login(user);
			//登录成功，
			if(loginUser != null){
				//判断是否是后台用户如果是跳转到后台
//				System.out.println("name:" + loginUser.getUserName());
				//自动登录
//				if("null".equals(request.getParameter("autoLogin"))){
//					Cookie autoLoginCookie = new Cookie("autoLoginCookie","");
//					autoLoginCookie.setPath("/");
////					autoLoginCookie.setMaxAge(0);
//					response.addCookie(autoLoginCookie);
//				}else{
//					
////					//如果勾选发送cookie
//					Cookie autoLoginCookie = new Cookie("autoLoginCookie",loginUser.getUserName() + "@" + user.getPassword());
//					autoLoginCookie.setPath("/");
//					autoLoginCookie.setMaxAge(60*60*24);
//					response.addCookie(autoLoginCookie);
//				}
//				//记住用户密码
//				String remember = request.getParameter("remembe");
//				if("1".equals(remember)){
//					Cookie remembermeCookie = new Cookie("remembermeCookie", user.getUserName());
//					remembermeCookie.setPath("/");
//					remembermeCookie.setMaxAge(60*60*24);
//					response.addCookie(remembermeCookie);
//				}
				//将用户存储到session中
				request.getSession().setAttribute("loginUser",loginUser);
				//获取用户角色，其中用户的角色分为普通用户与超级用户
//				String role  = loginUser.getRole();
//				System.out.println("role:"+loginUser.getRole());
//				if("1".equals(role)){
//					System.out.println("loginservlet" + loginUser.getRole());
//					response.sendRedirect(request.getContextPath() + "/admin/product/MyJsp.jsp");
//					return;
//				}
				if("admin".equals(loginUser.getUserName())){
					response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
				}else{
					response.sendRedirect(request.getContextPath() + "/servlet/IndexServlet");
				}
			}else{
			//登录失败
				request.setAttribute("msg","用户名或密码不正确");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("register_message",e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}
	//记住密码
	//自动登录
	//注销登录
	public void LogoutServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.将session用户状态信息移除
		request.getSession().removeAttribute("loginUser");
		//2.重定向到页面
		response.sendRedirect(request.getContextPath() + "/servlet/UserServlet?method=LoginServlet");
	}

}

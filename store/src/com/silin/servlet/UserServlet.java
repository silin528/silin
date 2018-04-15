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
	
	//ע��ģ��
	public void RegisterServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ���ݲ���װ
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		user.setUid(UUIDUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);//δ����=0
		String activeCode = UUIDUtils.getUUID();
		user.setCode(activeCode);//������
		
		//��user���ݸ�Service��
		UserService service = new UserService();
		boolean isRegisterSuccess = service.register(user);
		//�Ƿ�ע��ɹ�
		if(isRegisterSuccess){
			//���ͼ����ʼ�
			String emailMsg = "��ϲ��ע��ɹ���������������ӽ��м����˻�"
					+ "<a href='http://localhost:8080/store/servlet/ActiveServlet?activeCode="+activeCode+"'>"
							+ "http://localhost:8080/store/servlet/ActiveServlet?activeCode="+activeCode+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
				
			//��ת��ע��ɹ�ҳ��
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			//��ת��ʧ�ܵ���ʾҳ��
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
	}
	
	//û�б��õ����첽��֤�û����Ƿ�ע��
	public void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		//�����ı����ֵ
		String username = request.getParameter("username");
		//����ҵ����ѯ
		UserService userService = new UserService();
		User existUser = userService.findByUsername(username);
		//�ж�
		if(existUser == null){
			//û���û���
			response.getWriter().print(1);
		}else{
			//�û�����ʹ��
			response.getWriter().print(2);
		}
	}

	
//��¼
	//��¼
	public void LoginServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ�˺ź�����Ĳ���
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		//����service��ɵ�¼
		UserService service = new UserService();
		try{
			User loginUser = service.login(user);
			//��¼�ɹ���
			if(loginUser != null){
				//�ж��Ƿ��Ǻ�̨�û��������ת����̨
//				System.out.println("name:" + loginUser.getUserName());
				//�Զ���¼
//				if("null".equals(request.getParameter("autoLogin"))){
//					Cookie autoLoginCookie = new Cookie("autoLoginCookie","");
//					autoLoginCookie.setPath("/");
////					autoLoginCookie.setMaxAge(0);
//					response.addCookie(autoLoginCookie);
//				}else{
//					
////					//�����ѡ����cookie
//					Cookie autoLoginCookie = new Cookie("autoLoginCookie",loginUser.getUserName() + "@" + user.getPassword());
//					autoLoginCookie.setPath("/");
//					autoLoginCookie.setMaxAge(60*60*24);
//					response.addCookie(autoLoginCookie);
//				}
//				//��ס�û�����
//				String remember = request.getParameter("remembe");
//				if("1".equals(remember)){
//					Cookie remembermeCookie = new Cookie("remembermeCookie", user.getUserName());
//					remembermeCookie.setPath("/");
//					remembermeCookie.setMaxAge(60*60*24);
//					response.addCookie(remembermeCookie);
//				}
				//���û��洢��session��
				request.getSession().setAttribute("loginUser",loginUser);
				//��ȡ�û���ɫ�������û��Ľ�ɫ��Ϊ��ͨ�û��볬���û�
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
			//��¼ʧ��
				request.setAttribute("msg","�û��������벻��ȷ");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("register_message",e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}
	//��ס����
	//�Զ���¼
	//ע����¼
	public void LogoutServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.��session�û�״̬��Ϣ�Ƴ�
		request.getSession().removeAttribute("loginUser");
		//2.�ض���ҳ��
		response.sendRedirect(request.getContextPath() + "/servlet/UserServlet?method=LoginServlet");
	}

}

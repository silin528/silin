package com.silin.servlet.back;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.service.AdminService;

public class DelCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ��ѡҪɾ���ķ�����
		String cid = request.getParameter("cid");
		//����service�㣬ʵ��delCategoryServlet����
		AdminService service= new AdminService();
		try {
			service.delCategoryServlet(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
	}

}

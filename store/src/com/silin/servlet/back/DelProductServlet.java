package com.silin.servlet.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.service.AdminService;

public class DelProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��ȡ��ѡҪɾ���ķ�����
		String pid = request.getParameter("pid");
		//����service�㣬ʵ��delCategoryServlet����
		AdminService service= new AdminService();
		try {
			service.delProductServlet(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
	}

}

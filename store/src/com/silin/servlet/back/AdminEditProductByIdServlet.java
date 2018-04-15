package com.silin.servlet.back;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.Product;
import com.silin.service.AdminService;

public class AdminEditProductByIdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//1.���ղ���
		String pid = request.getParameter("pid");
		System.out.println("hao"+pid);
		//2.����ҵ���
		AdminService service = new AdminService();
		try {
			Product adminEditProductById = service.adminEditProductById(pid);
			System.out.println(adminEditProductById.getPname());
			request.setAttribute("adminEditProductById", adminEditProductById);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2/n");
		//3.��ʾ�༭��
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
	}

}

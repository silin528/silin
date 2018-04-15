package com.silin.servlet.back;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.Product;
import com.silin.service.AdminService;

public class AdminProductListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminService service = new AdminService ();
		List<Product> adminProductList = null;
		try {
			adminProductList = service.findAdminProcductList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//准备好商品列表，发送给商品列表界面
		request.setAttribute("adminProductList", adminProductList);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}

}

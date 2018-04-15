package com.silin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.Product;
import com.silin.service.ProductService;

public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		String methodName = request.getParameter("method");
		if("ProductByIdServlet".equals(methodName)){
			ProductByIdServlet(request,response);
		}
	}
	//������Ʒ�������Ʒid����ת���������
	public void ProductByIdServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.���ղ���
		String pid = request.getParameter("pid");
		//2.֪ͨservice���в�ѯ
		ProductService productService = new ProductService();
		try {
			Product product = productService.findById(pid);
			//3.1����ѯ������������
			request.setAttribute("product", product);
			//3.2��תҳ��
			request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

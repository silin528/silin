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
	//接收商品点击的商品id并跳转到详情界面
	public void ProductByIdServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String pid = request.getParameter("pid");
		//2.通知service进行查询
		ProductService productService = new ProductService();
		try {
			Product product = productService.findById(pid);
			//3.1将查询结果存放作用域
			request.setAttribute("product", product);
			//3.2跳转页面
			request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

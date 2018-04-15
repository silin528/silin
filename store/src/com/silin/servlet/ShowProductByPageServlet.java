package com.silin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.Product;
import com.silin.service.ProductService;

public class ShowProductByPageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//定义当前页码，默认为1
		int currentPage=1;
		String _currentPage = request.getParameter("currentPage");
		if(_currentPage != null){
			currentPage = Integer.parseInt(_currentPage);
		}
		//定义每页显示条数，默认为4；
		int currentCount=4;
		String _currentCount = request.getParameter("currentCount");
		if(_currentCount != null){
			currentCount = Integer.parseInt(_currentCount);
		}
		//获取查询的分类
		String category = "全部商品";
		String _category = request.getParameter("category");
		//当分类category参数值不为null，将获得赋给category的变量
		if(_category  != null){
			category=_category;
		}
		//调用service，完成获取当前分页数据
		ProductService service = new ProductService();
		
		try {
			Product bean = service.findProductByPage(currentPage,currentCount,category);
			request.setAttribute("bean",bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将数据存储到request范围，跳转到porduct_list.jsp页面展示
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
	}

}

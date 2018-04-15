package com.silin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.Category;
import com.silin.domain.Product;
import com.silin.service.CategoryService;
import com.silin.service.ProductService;

public class IndexServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//热门商品
		try{
			ProductService productService = new ProductService();
			List<Product>hotList = productService.findByHot();
			//最新商品
			List<Product>newList = productService.findByNew();

			request.setAttribute("hotList",hotList);
			request.setAttribute("newList",newList);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		 
	}
//	public String execute(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException, SQLException {
//		 CategoryService  categoryService = new CategoryService();
//		 List<Category> allCategory;
//		try {
//			allCategory = categoryService.findAll();
//			request.setAttribute("allCategory",allCategory);
//			request.getRequestDispatcher("/menu.jsp").forward(request, response);
//		
//				return "/index.jsp";
//	}

}

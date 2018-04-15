package com.silin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.silin.domain.Category;
import com.silin.domain.PageBean;
import com.silin.service.CategoryService;
import com.silin.service.ProductService;

public class CategoryServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		
		String methodName = request.getParameter("method");
		if("CategoryListServlet".equals(methodName)){
			CategoryListServlet(request,response);
		}else if("ProductListByCidServlet".equals(methodName)){
			ProductListByCidServlet(request,response);
		}
	}
	//header的分类信息
	public void CategoryListServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService productService = new ProductService();
		//分类数据
		List<Category> categoryList;
		try {
			categoryList = productService.findAllCategory();
			request.setAttribute("categoryList",categoryList);
			Gson gson = new Gson();
			String json = gson.toJson(categoryList);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//header的分类信息选中调用
	public void ProductListByCidServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取acid
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		System.out.println("currentPage=" + currentPageStr);
		if(currentPageStr == null){
			currentPageStr = "1";
		}
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 10;
		ProductService productService  = new ProductService ();
		try {
			PageBean pageBean = productService.findProductListByCid(cid,currentPage,currentCount);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("cid", cid);
			request.getRequestDispatcher("/product_list.jsp").forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}

}

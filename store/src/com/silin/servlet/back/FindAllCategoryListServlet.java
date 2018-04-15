package com.silin.servlet.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.silin.domain.Category;
import com.silin.service.AdminService;

public class FindAllCategoryListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//提供一个List<Category>转成json字符串
				AdminService service = new AdminService();
				List<Category> findCategoryList = null;
				try {
					findCategoryList = service.findAllCategory();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("findCategoryList", findCategoryList);
				request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
	}

}

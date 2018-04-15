package com.silin.servlet.back;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.Category;
import com.silin.domain.User;
import com.silin.service.AdminService;
import com.silin.utils.MyBeanUtils;
import com.silin.utils.UUIDUtils;

public class AdminAddCategoryServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = MyBeanUtils.populate(Category.class, request.getParameterMap());
		category.setCid(UUIDUtils.getUUID());
		AdminService service = new AdminService();
		try {
			service.addCategory(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

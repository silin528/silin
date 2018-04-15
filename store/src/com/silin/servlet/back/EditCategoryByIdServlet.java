package com.silin.servlet.back;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.Category;
import com.silin.service.AdminService;
import com.silin.utils.MyBeanUtils;

public class EditCategoryByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String cid = request.getParameter("cid");
		//2.调用业务层
		AdminService service = new AdminService();
		try {
			Category editCategoryById = service.editCategoryById(cid);
			System.out.println("eidt"+ editCategoryById.getCid() + editCategoryById.getCname());
			request.setAttribute("editCategoryById", editCategoryById);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//3.显示编辑表单
		request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
	}

}

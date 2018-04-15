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

public class EditCategoryServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���ղ���
		Category category = MyBeanUtils.populate(Category.class, request.getParameterMap());
		System.out.println("EditCategoryServlet1" + category.getCid() +category.getCname());
		//����ҵ���
		AdminService service = new AdminService();
		try {
			service.editCategory(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("EditCategoryServlet");
		//��ʾ�༭��
		response.sendRedirect(request.getContextPath() + "/servlet/AdminProductListServlet");
	}

}

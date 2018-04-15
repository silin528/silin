package com.silin.servlet.back;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silin.domain.Product;
import com.silin.service.AdminService;
import com.silin.utils.MyBeanUtils;

public class AdminEditProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收参数
		String pro = request.getParameter("pid");
		System.out.println(pro);
		Product product = MyBeanUtils.populate(Product.class, request.getParameterMap());
		System.out.println("dasd"+product.getPname());
		//调用业务层
		AdminService service = new AdminService();
		try {
			service.AdminEditProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1/n");
		//显示编辑表单
		response.sendRedirect(request.getContextPath() + "/servlet/FindAllCategoryServlet");
		
	}

}

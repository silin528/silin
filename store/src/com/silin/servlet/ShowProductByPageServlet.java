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
		//���嵱ǰҳ�룬Ĭ��Ϊ1
		int currentPage=1;
		String _currentPage = request.getParameter("currentPage");
		if(_currentPage != null){
			currentPage = Integer.parseInt(_currentPage);
		}
		//����ÿҳ��ʾ������Ĭ��Ϊ4��
		int currentCount=4;
		String _currentCount = request.getParameter("currentCount");
		if(_currentCount != null){
			currentCount = Integer.parseInt(_currentCount);
		}
		//��ȡ��ѯ�ķ���
		String category = "ȫ����Ʒ";
		String _category = request.getParameter("category");
		//������category����ֵ��Ϊnull������ø���category�ı���
		if(_category  != null){
			category=_category;
		}
		//����service����ɻ�ȡ��ǰ��ҳ����
		ProductService service = new ProductService();
		
		try {
			Product bean = service.findProductByPage(currentPage,currentCount,category);
			request.setAttribute("bean",bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����ݴ洢��request��Χ����ת��porduct_list.jspҳ��չʾ
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
	}

}

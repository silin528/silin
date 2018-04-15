package com.silin.service;

import java.sql.SQLException;
import java.util.List;

import com.silin.dao.AdminDao;
import com.silin.domain.Category;
import com.silin.domain.Order;
import com.silin.domain.Product;

public class AdminService {

	public List<Category> findAllCategory() throws SQLException {
		AdminDao dao = new AdminDao();
		return dao.findAllCategory();
	}

	public void saveProduct(Product product) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.saveProduct(product);
		
	}

	public List<Product> findAdminProcductList() throws SQLException {
		AdminDao dao = new AdminDao();
		return dao.findAdminProcductList();
	}

	public void delCategoryServlet(String cid) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.delCategoryServlet(cid);
		
	}

//	public void editCategory(Category category) throws SQLException {
//		AdminDao dao = new AdminDao();
//		dao.editCategory(category);
//	}
	//ͨ��id����������Ϣ
	public Category editCategoryById(String cid) throws SQLException {
		AdminDao dao = new AdminDao();
		return dao.editCategoryById(cid);
		 
	}
	//�༭������
	public void editCategory(Category category) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.editCategory(category);
	}

	public List<Order> findAllOrders() throws SQLException {
		AdminDao dao = new AdminDao();
		List<Order> ordersList = dao.findAllOrders();
		return ordersList;
	}
	//��ӷ���
	public void addCategory(Category category) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.addCategory(category);
		
	}
	//����pid,��ɾ��
	public void delProductServlet(String pid) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.delProductServlet(pid);
		
	}
	//������Ʒ�༭
	public Product adminEditProductById(String pid) throws SQLException {
		AdminDao dao = new AdminDao();
		return dao.adminEditProductById(pid);
	}
	//������Ʒ�༭
	public void AdminEditProduct(Product product) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.adminEditProduct(product);
	}

}

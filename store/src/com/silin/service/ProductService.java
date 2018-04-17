package com.silin.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.silin.dao.ProductDao;
import com.silin.domain.Category;
import com.silin.domain.Order;
import com.silin.domain.PageBean;
import com.silin.domain.Product;
import com.silin.utils.DataSourceUtils;

public class ProductService {

	public Product findProductById(String pid) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();
		return productDao.findProductById(pid);
	}

	public Product findProductByPage(int currentPage, int currentCount,String category) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();
		return productDao.findProductByPage(currentPage,currentCount, category);
	}


	public List<Product> findAllProduct() throws SQLException {
		//û�и���ҵ��
		//��������dao��
		ProductDao dao = new ProductDao();
		List<Product> productList = dao.findAllProduct();
		return productList;
	}

	public List<Product> listAll() throws SQLException {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDao();
		return dao.listAll();
		
	}

	public void addProduct(Product p) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDao();
		dao.addProduct(p);
		
	}

	public void editProduct(Product p) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDao();
		dao.editProduct(p);
	}

	public void deleteProduct(String pid) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDao();
		dao.deleteProduct(pid);
		
	}

	//����������
	public List<Product> findByHot() throws SQLException {
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();
		List<Product> hotProductList = productDao.findByHot();
		return hotProductList;
	}
	public List<Product> findByNew() throws SQLException {
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();
		List<Product> newProductList = productDao.findByNew();
		return newProductList;
	}
	
	//��Ʒ����
	public Product findById(String pid) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();
		Product product = productDao.findById(pid);
		return product;
	}
	//��������
	public List<Category> findAllCategory() throws SQLException {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDao();
		List<Category>categoryList =dao.findAllCategory(); 
		return categoryList;
	}
	//�����б�
	public PageBean findProductListByCid(String cid,int currentPage,int currentCount) throws SQLException {
		ProductDao dao = new ProductDao();
		//��װһ��PageBean ����web��
		PageBean<Product> pageBean = new PageBean<Product>();
		//1,��װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		//2,��װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		//3,��װ������
		int totalCount = dao.getCount(cid);
		pageBean.setTotalcount(totalCount);
		//4,��װ��ҳ��
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		//��ʾ��ǰҳ������
		//select * from product where cid =? ,limit?,?
		int index = (currentPage-1)*currentCount;
		List<Product> list = dao.findProductByPage(cid, index, currentCount);
		pageBean.setList(list);
		return pageBean ;
	}
	
	//�ύ���� �������������붩���������һ��������ݿ�
	public void submitOrder(Order order) {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDao();
		try {
			//��������
			DataSourceUtils.startTransaction();
			//����dao�洢order�����ݷ���
			dao.addOrders(order);
			//����dao�洢����orderItem������
			dao.addOrderItem(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//���ָ���û��Ķ�������
	public List<Order> findAllOrders(String uid) {
	ProductDao dao = new ProductDao();
	List<Order> orderList = null;
	try {
		orderList = dao.findAllOrders(uid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return orderList;
	}

	public List<Map<String,Object>> findAllOrderItemByOid(String oid) {
		ProductDao dao = new ProductDao();
		List<Map<String,Object>> orderItemList = null;
		System.out.println("Oid");
		try {
			orderItemList = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItemList;
	}
	//������
	public Product searchName(String pname) throws SQLException {
		ProductDao dao = new ProductDao();
		Product product = dao.searchName(pname);
		System.out.println("456");
		return product; 
	}

	


}

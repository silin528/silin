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
		//没有复杂业务
		//传递请求到dao层
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

	//热门与最新
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
	
	//商品详情
	public Product findById(String pid) throws SQLException {
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();
		Product product = productDao.findById(pid);
		return product;
	}
	//分类数据
	public List<Category> findAllCategory() throws SQLException {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDao();
		List<Category>categoryList =dao.findAllCategory(); 
		return categoryList;
	}
	//分类列表
	public PageBean findProductListByCid(String cid,int currentPage,int currentCount) throws SQLException {
		ProductDao dao = new ProductDao();
		//封装一个PageBean 返回web层
		PageBean<Product> pageBean = new PageBean<Product>();
		//1,封装当前页
		pageBean.setCurrentPage(currentPage);
		//2,封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		//3,封装总条数
		int totalCount = dao.getCount(cid);
		pageBean.setTotalcount(totalCount);
		//4,封装总页数
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		//显示当前页的数据
		//select * from product where cid =? ,limit?,?
		int index = (currentPage-1)*currentCount;
		List<Product> list = dao.findProductByPage(cid, index, currentCount);
		pageBean.setList(list);
		return pageBean ;
	}
	
	//提交订单 将订单的数据与订单项的数据一起存在数据库
	public void submitOrder(Order order) {
		// TODO Auto-generated method stub
		ProductDao dao = new ProductDao();
		try {
			//开启事务
			DataSourceUtils.startTransaction();
			//调用dao存储order表数据方法
			dao.addOrders(order);
			//调用dao存储方法orderItem表数据
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
	//获得指定用户的订单集合
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
	//搜索栏
	public Product searchName(String pname) throws SQLException {
		ProductDao dao = new ProductDao();
		Product product = dao.searchName(pname);
		System.out.println("456");
		return product; 
	}

	


}

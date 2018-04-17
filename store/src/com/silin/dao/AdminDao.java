package com.silin.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.silin.domain.Category;
import com.silin.domain.Order;
import com.silin.domain.Product;
import com.silin.utils.DataSourceUtils;

public class AdminDao {

	public List<Category> findAllCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
		
	}
//保存商品
	public void saveProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into products values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql,product.getPid(),product.getPname(),product.getPnum(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCategory().getCid());
		
	}

	public List<Product> findAdminProcductList() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from products";
		List<Product> findAdminProcductList = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return findAdminProcductList;
	}

	public void delCategoryServlet(String cid) throws SQLException {
		//将商品的外键设置为null
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update products set cid=null where cid=?";
		runner.update(sql, cid);
		//删除分类
		sql = "delete from category where cid=?";
		runner.update(sql,cid);
	}

//	public void editCategory(Category category) throws SQLException {
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql = "update category set cname=? where cid=?";
//		runner.update(sql,category.getCname(),category.getCid());
//	}

	public Category editCategoryById(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from category where cid=?";
		Category category = runner.query(sql, new BeanHandler<Category>(Category.class),cid);
		return category;
	}
	//编辑导航栏
	public void editCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname=? where cid=?";
		runner.update(sql, category.getCname(),category.getCid());
		
	}

	public List<Order> findAllOrders() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders";
		return runner.query(sql, new BeanListHandler<Order>(Order.class));
	}

	public int addCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?,?)";
		return runner.update(sql, category.getCid(),category.getCname());
		
	}

	public void delProductServlet(String pid) throws SQLException {
		//将商品的外键设置为null
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orderitem set pid=null where pid=?";
		runner.update(sql, pid);
		//删除分类
		sql = "delete from products where pid=?";
		runner.update(sql,pid);
	}
	//后台商品编辑
	public Product adminEditProductById(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from products where pid=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}
	//后台商品编辑
	public void adminEditProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update products set pname=? pnum=? shop_price=? pimage=? is_hot=? pdesc=? where pid=?";
		runner.update(sql, product.getPname(),product.getpNum(),product.getShop_price(),product.getPimage(),product.getIs_hot(),product.getPdesc(),product.getPid());
		
	}

}

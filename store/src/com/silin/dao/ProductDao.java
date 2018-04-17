package com.silin.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.silin.domain.Category;
import com.silin.domain.Order;
import com.silin.domain.OrderItem;
import com.silin.domain.Product;
import com.silin.utils.DataSourceUtils;

public class ProductDao {

	public Product findProductById(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from products where pid=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

	public Product findProductByPage(int currentPage, int currentCount,String category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from products where id=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class),currentPage,currentCount, category);
		return product;
	}

	public List<Product> findAllProduct() throws SQLException {
		//操作数据库
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from products";
		List<Product> productList = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return productList;
	}

	public List<Product> listAll() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from products";
		List<Product> productList = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return productList;
	}

	public void addProduct(Product p) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into products values(?,?,?,?,?,?,?)";
		runner.update(sql,p.getPid(),p.getPname(),p.getShop_price(),p.getpNum(),p.getPimage(),p.getPdesc());
	}

	public void editProduct(Product p) throws SQLException {
		List<Object> obj = new ArrayList<Object>();
		obj.add(p.getPname());
		obj.add(p.getShop_price());
		obj.add(p.getpNum());
		obj.add(p.getPdesc());
		String sql = "update product set pname=?, shop_price=?, pNum=?, pdesc=?";
		if(p.getPimage() != null && p.getPimage().trim().length()>0){
			sql+=",pimage()";
		}
		sql+="where id=?";
		obj.add(p.getPid());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, obj.toArray());
	}

	public void deleteProduct(String pid) throws SQLException {
		String sql = "delete from product where pid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,pid);
	}

	//热门与最新
	public List<Product> findByHot() throws SQLException {
		String sql = "select * from products where is_hot=? limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql,new BeanListHandler<Product>(Product.class),1,0,5);
		
	}

	public List<Product> findByNew() throws SQLException {
		String sql = "select * from products order by pdate desc limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Product>list = runner.query(sql,new BeanListHandler<Product>(Product.class),0,10);
		return list;
	}
	//商品详情
	public Product findById(String pid) throws SQLException {
		String sql = "select * from products where pid = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Product product = runner.query(sql,new BeanHandler<Product>(Product.class),pid);
		return product;
	}
	//分类数据
	public List<Category> findAllCategory() throws SQLException {
		String sql = "select * from category";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Category> category = runner.query(sql,new BeanListHandler<Category>(Category.class));
		return category;
	}
	//分类商品页数
	public int getCount(String cid) throws SQLException {
		String sql = "select count(*) from products where cid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Long query = (Long) runner.query(sql, new ScalarHandler(),cid);
		return query.intValue();
	}

	public List<Product> findProductByPage(String cid, int index,
			int currentCount) throws SQLException {
		String sql = "select * from products where cid=? limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Product> list = runner.query(sql,new BeanListHandler<Product>(Product.class),cid,index,currentCount);
		return  list;
	}
	//向orders表插入数据
		public void addOrders(Order order) throws SQLException {
			QueryRunner runner = new QueryRunner();
			String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
			Connection conn = DataSourceUtils.getConnection();
			runner.update(conn,sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
					order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
		}
		//向orderitem表插入数据
		public void addOrderItem(Order order) throws SQLException {
			QueryRunner runner = new QueryRunner();
			String sql = "insert into orderitem values(?,?,?,?,?)";
			Connection conn = DataSourceUtils.getConnection();
			List<OrderItem> orderItems = order.getOrderItems();
			for(OrderItem item : orderItems){
				runner.update(conn,sql,item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid());
			}
		}

		public List<Order> findAllOrders(String uid) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from orders where uid=?";
			return runner.query(sql, new BeanListHandler<Order>(Order.class),uid);
		}

		public List<Map<String,Object>> findAllOrderItemByOid(String oid) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from orderItem i ,products p  where i.pid=p.pid and i.oid=?";
			List<Map<String,Object>> mapList = runner.query(sql, new MapListHandler(),oid);
			return mapList;
		}

		public Product searchName(String pname) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from products where pname like '%"+pname+"%' ";
			Product product = runner.query(sql, new BeanHandler<Product>(Product.class));
			return product;
		}
	
	


	
	

}

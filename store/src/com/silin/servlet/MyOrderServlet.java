package com.silin.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.silin.domain.Order;
import com.silin.domain.OrderItem;
import com.silin.domain.Product;
import com.silin.domain.User;
import com.silin.service.ProductService;
//获取订单
public class MyOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断用户是否登录,不登录不执行下面代码
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("loginUser");
				if(user == null){
					//没有登录
					response.sendRedirect(request.getContextPath() + "/login.jsp");
					return;
				}
				ProductService service = new ProductService();
				//查询该用户所有信息（单表）
				//集合中的每一个Order对象是不完整的缺少List<OrderItem>
				List<Order> orderList = service.findAllOrders(user.getUid());
				//循环所有的订单 为每个订单填充订单项集合信息
				if(orderList!=null){
					for(Order order : orderList){
						//获得每一个订单的Oid
						String oid = order.getOid();
						//查询该订单所有的订单项----mapList封装的是多个订单项和该订单项中的商品的信息
						List<Map<String,Object>> mapList = service.findAllOrderItemByOid(oid);
						//将MapList转换成List<OrderItem> orderItems
						for(Map<String,Object> map : mapList){
							System.out.println("map :" +map);
							try {
								//从map中取出count subtotal 封装到OrderItem中
								OrderItem item = new OrderItem();
								//item.setCount(Integer.parseInt(map.get("count").toString()));
								BeanUtils.populate(item, map);
								//从map中取出pimage pname shop_price 封装到Product中
								Product product = new Product();
								BeanUtils.populate(product, map);
								//将product封装到OrderItem
								item.setProduct(product);
								//将orderitem封装到order中的orderItemList中
								order.getOrderItems().add(item);
							} catch (IllegalAccessException | InvocationTargetException e) {
								e.printStackTrace();
							}
						}
						
					}
				}
				//orderList封装完整
				request.setAttribute("orderList", orderList);
				request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}

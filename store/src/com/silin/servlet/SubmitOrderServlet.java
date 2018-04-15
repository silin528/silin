package com.silin.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.silin.domain.Cart;
import com.silin.domain.CartItem;
import com.silin.domain.Order;
import com.silin.domain.OrderItem;
import com.silin.domain.User;
import com.silin.service.ProductService;
import com.silin.utils.UUIDUtils;

public class SubmitOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//判断用户是否登录,不登录不执行下面代码
		User user = (User) session.getAttribute("loginUser");
		if(user == null){
			//没有登录
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		
		//封装好一个Order对象，传递给service层
		Order order = new Order();
		String oid = UUIDUtils.getUUID();
		order.setOid(oid);
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTime;
		try {
			dateTime = time.parse(time.format(new Date()));
			order.setOrdertime(dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		//获得session购物车
		Cart cart = (Cart) session.getAttribute("cart");
		//判断是否为空，为空就提示用户重新选取
		if(cart != null){
			double total = cart.getTotal();
			order.setTotal(total);
		}
		order.setState(0);
		order.setAddress(null);
		order.setName(null);
		order.setTelephone(null);
		order.setUser(user);
		//获得购物车的购物项的集合
		Map<String,CartItem> cartItems = cart.getCartItems();
		for(Map.Entry<String,CartItem> entry : cartItems.entrySet()){
			//取出每一个购物项
			CartItem cartItem = entry.getValue();
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getUUID());
			orderItem.setCount(cartItem.getBuyNum());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			//将该订单添加到订单项集合中
			order.getOrderItems().add(orderItem);
		}
		//order对象封装完毕
		ProductService service = new ProductService();
		service.submitOrder(order);
		
		session.setAttribute("order", order);
		//页面跳转
		response.sendRedirect(request.getContextPath() + "/order_info.jsp");	
		
	}

}

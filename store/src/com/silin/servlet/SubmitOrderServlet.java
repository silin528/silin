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
		//�ж��û��Ƿ��¼,����¼��ִ���������
		User user = (User) session.getAttribute("loginUser");
		if(user == null){
			//û�е�¼
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		
		//��װ��һ��Order���󣬴��ݸ�service��
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
		
		//���session���ﳵ
		Cart cart = (Cart) session.getAttribute("cart");
		//�ж��Ƿ�Ϊ�գ�Ϊ�վ���ʾ�û�����ѡȡ
		if(cart != null){
			double total = cart.getTotal();
			order.setTotal(total);
		}
		order.setState(0);
		order.setAddress(null);
		order.setName(null);
		order.setTelephone(null);
		order.setUser(user);
		//��ù��ﳵ�Ĺ�����ļ���
		Map<String,CartItem> cartItems = cart.getCartItems();
		for(Map.Entry<String,CartItem> entry : cartItems.entrySet()){
			//ȡ��ÿһ��������
			CartItem cartItem = entry.getValue();
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getUUID());
			orderItem.setCount(cartItem.getBuyNum());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			//���ö�����ӵ����������
			order.getOrderItems().add(orderItem);
		}
		//order�����װ���
		ProductService service = new ProductService();
		service.submitOrder(order);
		
		session.setAttribute("order", order);
		//ҳ����ת
		response.sendRedirect(request.getContextPath() + "/order_info.jsp");	
		
	}

}

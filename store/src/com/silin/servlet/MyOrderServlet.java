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
//��ȡ����
public class MyOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�ж��û��Ƿ��¼,����¼��ִ���������
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("loginUser");
				if(user == null){
					//û�е�¼
					response.sendRedirect(request.getContextPath() + "/login.jsp");
					return;
				}
				ProductService service = new ProductService();
				//��ѯ���û�������Ϣ������
				//�����е�ÿһ��Order�����ǲ�������ȱ��List<OrderItem>
				List<Order> orderList = service.findAllOrders(user.getUid());
				//ѭ�����еĶ��� Ϊÿ��������䶩�������Ϣ
				if(orderList!=null){
					for(Order order : orderList){
						//���ÿһ��������Oid
						String oid = order.getOid();
						//��ѯ�ö������еĶ�����----mapList��װ���Ƕ��������͸ö������е���Ʒ����Ϣ
						List<Map<String,Object>> mapList = service.findAllOrderItemByOid(oid);
						//��MapListת����List<OrderItem> orderItems
						for(Map<String,Object> map : mapList){
							System.out.println("map :" +map);
							try {
								//��map��ȡ��count subtotal ��װ��OrderItem��
								OrderItem item = new OrderItem();
								//item.setCount(Integer.parseInt(map.get("count").toString()));
								BeanUtils.populate(item, map);
								//��map��ȡ��pimage pname shop_price ��װ��Product��
								Product product = new Product();
								BeanUtils.populate(product, map);
								//��product��װ��OrderItem
								item.setProduct(product);
								//��orderitem��װ��order�е�orderItemList��
								order.getOrderItems().add(item);
							} catch (IllegalAccessException | InvocationTargetException e) {
								e.printStackTrace();
							}
						}
						
					}
				}
				//orderList��װ����
				request.setAttribute("orderList", orderList);
				request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}

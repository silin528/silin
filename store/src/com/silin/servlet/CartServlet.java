package com.silin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.silin.domain.Cart;
import com.silin.domain.CartItem;
import com.silin.domain.Product;
import com.silin.service.ProductService;

public class CartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if("AddCartServlet".equals(methodName)){
			AddCartServlet(request,response);
		}else if("DelProCartFormServlet".equals(methodName)){
			DelProCartFormServlet(request,response);
			
		}else if("ClearCartServlet".equals(methodName)){
			ClearCartServlet(request,response);
		}
	}
	
	//��ӹ��ﳵ
	public void AddCartServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����Ʒ��ӵ����ﳵ
		HttpSession session = request.getSession();
		
		//1.�õ���Ʒid
		String pid = request.getParameter("pid");
		//��ȡ����Ʒ�Ĺ�������
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		//����service�㷽��,����id������Ʒ
		ProductService service = new ProductService();
		try{
			Product product = service.findProductById(pid);
			//����С��
			double subtotal = product.getShop_price() * buyNum;
			//��װCartItem
			CartItem item = new CartItem();
			item.setProduct(product);
			item.setBuyNum(buyNum);
			item.setSubtotal(subtotal);
			
			//��ȡ���ﳵ----�ж��Ƿ����session�Ѿ����ﳵ
			Cart cart = (Cart) session.getAttribute("cart");
			if(cart == null){
				cart = new Cart();
			}
			//��������ŵ�����---key��pid
			//���жϹ��ﳵ�Ƿ��Ѿ������˹�����----�ж�Key�Ƿ����
			//������ﳵ���Ѿ����ڸ���Ʒ---�������������ԭ�е�����������Ӳ���
			Map<String,CartItem> cartItems = cart.getCartItems();
			
			double newsubtotal = 0.0;
			
			if(cartItems.containsKey(pid)){
				//ȡ��ԭ�е���Ʒ����
				CartItem cartItem = cartItems.get(pid);
				//�޸�����
				int oldBuyNum = cartItem.getBuyNum();
				oldBuyNum += buyNum;
				cartItem.setBuyNum(oldBuyNum);
				cart.setCartItems(cartItems);
				//�޸�С��
				//ԭ�ȸ���Ʒ��С��
				double oldsubtotal = cartItem.getSubtotal();
				//�������ƷС��
				newsubtotal = buyNum*product.getShop_price();
				cartItem.setSubtotal(oldsubtotal + newsubtotal);
			}else{
			//�������û�и���Ʒ
				cart.getCartItems().put(product.getPid(),item);	
				newsubtotal = buyNum*product.getShop_price();
			}
		
			//�����ܼ�
			double total = cart.getTotal() + newsubtotal;
			cart.setTotal(total);
			
			//�����ٴη���session
			session.setAttribute("cart", cart);
			//ֱ����ת�����ﳵҳ��
			response.sendRedirect(request.getContextPath() +"/cart.jsp");
				
		}catch(IOException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ɾ�����ﳵ����
	public void DelProCartFormServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

				//��ȡҪɾ����item��pid;
				String pid = request.getParameter("pid");
				//ɾ��session�еĹ��ﳵ�еĹ�������е�item
				HttpSession session = request.getSession();
				Cart cart = (Cart) session.getAttribute("cart");
				if(cart != null){
					Map<String, CartItem> cartItems = cart.getCartItems();
					cartItems.remove(pid);
					cart.setCartItems(cartItems);
				}
				session.setAttribute("cart", cart);
				//��ת��cart.jsp
				response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}
	//��չ��ﳵ
	public void ClearCartServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		//��ת��cart.jsp
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}
}

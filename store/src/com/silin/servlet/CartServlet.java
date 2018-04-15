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
	
	//添加购物车
	public void AddCartServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//将商品添加到购物车
		HttpSession session = request.getSession();
		
		//1.得到商品id
		String pid = request.getParameter("pid");
		//获取该商品的购买数量
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		//调用service层方法,根据id查找商品
		ProductService service = new ProductService();
		try{
			Product product = service.findProductById(pid);
			//计算小计
			double subtotal = product.getShop_price() * buyNum;
			//封装CartItem
			CartItem item = new CartItem();
			item.setProduct(product);
			item.setBuyNum(buyNum);
			item.setSubtotal(subtotal);
			
			//获取购物车----判断是否存在session已经购物车
			Cart cart = (Cart) session.getAttribute("cart");
			if(cart == null){
				cart = new Cart();
			}
			//将购物项放到车中---key是pid
			//先判断购物车是否已经包含此购物项----判断Key是否存在
			//如果购物车中已经存在该商品---将现买的数量与原有的数量进行相加操作
			Map<String,CartItem> cartItems = cart.getCartItems();
			
			double newsubtotal = 0.0;
			
			if(cartItems.containsKey(pid)){
				//取出原有的商品数量
				CartItem cartItem = cartItems.get(pid);
				//修改树量
				int oldBuyNum = cartItem.getBuyNum();
				oldBuyNum += buyNum;
				cartItem.setBuyNum(oldBuyNum);
				cart.setCartItems(cartItems);
				//修改小计
				//原先该商品的小计
				double oldsubtotal = cartItem.getSubtotal();
				//新买的商品小计
				newsubtotal = buyNum*product.getShop_price();
				cartItem.setSubtotal(oldsubtotal + newsubtotal);
			}else{
			//如果车中没有该商品
				cart.getCartItems().put(product.getPid(),item);	
				newsubtotal = buyNum*product.getShop_price();
			}
		
			//计算总计
			double total = cart.getTotal() + newsubtotal;
			cart.setTotal(total);
			
			//将车再次访问session
			session.setAttribute("cart", cart);
			//直接跳转到购物车页面
			response.sendRedirect(request.getContextPath() +"/cart.jsp");
				
		}catch(IOException e){
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除购物车单项
	public void DelProCartFormServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

				//获取要删除的item的pid;
				String pid = request.getParameter("pid");
				//删除session中的购物车中的购物项集合中的item
				HttpSession session = request.getSession();
				Cart cart = (Cart) session.getAttribute("cart");
				if(cart != null){
					Map<String, CartItem> cartItems = cart.getCartItems();
					cartItems.remove(pid);
					cart.setCartItems(cartItems);
				}
				session.setAttribute("cart", cart);
				//跳转回cart.jsp
				response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}
	//清空购物车
	public void ClearCartServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		//跳转回cart.jsp
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}
}

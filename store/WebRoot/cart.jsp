<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
		
		
		<script type="text/javascript">
			function delProFromCart(pid){
				if(confirm("您是否要删除该项？")){
					location.href="${pageContext.request.contextPath}/servlet/CartServlet?method=DelProCartFormServlet&pid="+pid;
				}
			}
			
			function clearCart(){
				if(confirm("您是否要清空购物车？")){
					location.href="${pageContext.request.contextPath }/servlet/CartServlet?method=ClearCartServlet";
				}
			}
		
		</script>
		
	</head>

	<body>
		<!-- 引入header.jsp -->
		<jsp:include page="/header.jsp"></jsp:include>

		<!-- 判断购物车中是否有商品 -->
		<c:if test="${!empty cart.cartItems }">
			
			<div class="container-fluid">
				<div class="">
					<div style=" margin-top:80px;">
						<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
						<table class="table table-bordered">
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
									<th>操作</th>
								</tr>
								
								<c:forEach items="${cart.cartItems }" var="entry">
								
									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath }/${entry.value.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank">${entry.value.product.pname}</a>
										</td>
										<td width="20%">
											￥${entry.value.product.shop_price}
										</td>
										<td width="10%">
											${entry.value.buyNum }
										</td>
										<td width="15%">
											<span class="subtotal">￥${entry.value.subtotal }</span>
										</td>
										<td>
											<a href="javascript:void(0);" onclick="delProFromCart('${entry.value.product.pid}')" class="delete">删除</a>
										</td>
									</tr>
								</c:forEach>
						</table>
					</div>
				</div>
	
				<div >
					<div class="text-left">
						<em style="color:#ff6600;">
					享有优惠&nbsp;&nbsp;</em> 赠送积分: 
				<em style="color:#ff6600;">${cart.total }</em>&nbsp; <br>商品金额: <strong style="color:#ff6600;">￥${cart.total }元</strong>
					</div>
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<a href="javascript:void(0);" onclick="clearCart()" id="clear" class="clear">清空购物车</a>
						<a href="${pageContext.request.contextPath }/servlet/SubmitOrderServlet">
							<input type="button" width="100" value="提交订单" name="submit" border="0" style="background: url('${pageContext.request.contextPath }/image/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
							height:35px;width:100px;color:white;">
						</a>
					</div>
				</div>
	
			</div>
		</c:if>
		<c:if test="${empty cart.cartItems }">
		
		<div class="col-md-4"></div>
			<div class="col-md-4" style="margin-top:200px;">
				<img alt="" src="${pageContext.request.contextPath }/image/cart-empty.png">
				<p><a href="${pageContext.request.contextPath}">返回首页</a></p>
			</div>
			
		<div class="col-md-4"></div>
		
		</c:if>

		<!-- 引入footer.jsp -->
		<jsp:include page="/footer.jsp"></jsp:include>

	</body>

</html>
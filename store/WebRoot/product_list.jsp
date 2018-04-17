<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>商品列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	
   
   
  </head>
  
  <body>
  <jsp:include page="header.jsp"></jsp:include>
  
  	<div class="container">
  	<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}">首页</a></li>
			</ol>
		</div>
		<section class="section" id="partners" style="margin-top:60px;">
        <div class="container">
            <header class="section-header" style="text-align: center;max-width: 70%;margin: 0 auto;">
                <h2>Product List</h2>
                <hr>
                <p class="lead">Commodity Classification Recommendation.</p>
            </header>
            <div class="partner" style="height:560px;overflow:hidden;">
            	<c:forEach var="pro" items="${pageBean.list }">
						<div class="col-md-2 col-sm-3 col-xs-4 box" style="text-align:center;height:220px;padding:10px 0px;border-bottom:1px solid #888888;margin-top:40px;margin-left:35px;">
							<a href="${pageContext.request.contextPath}/servlet/ProductServlet?method=ProductByIdServlet&pid=${pro.pid}"> 
								<img src="${pageContext.request.contextPath }/${pro.pimage}" width="130" height="150" style="display: inline-block;">
							</a>
							<p><a href="${pageContext.request.contextPath}/servlet/ProductServlet?method=ProductByIdServlet&pid=${pro.pid}" style='color: green'>${pro.pname }</a></p>
							<p><font color="#E4393C" style="font-size:16px">&yen;${pro.shop_price }</font></p>
						</div>
				</c:forEach>	
            </div>
        </div>
    </section>
 	</div>
 	
 	<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-top: 50px;" class="text-center" >
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
		
			<!-- 上一页 -->
			<c:if test="${pageBean.currentPage==1 }">
				<li class="disabled">
					<a href="javascript:void(0);" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			<c:if test="${pageBean.currentPage!=1 }">
				<li>
					<a href="${pageContext.request.contextPath}/servlet/CategoryServlet?method=ProductListByCidServlet&cid=${cid}&currentPage=${pageBean.currentPage-1 }" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if> 
		
			<!-- 显示每一页 -->
			
			<c:forEach begin="1" end="${pageBean.totalPage }" var="page">
				<!-- 判断是否是当前页 -->
				<c:if test="${page==pageBean.currentPage }">
					<li class="active"><a href="javascript:void(0);">${page }</a></li>
				</c:if>
				<c:if test="${page!=pageBean.currentPage }">
					<li><a href="${pageContext.request.contextPath}/servlet/CategoryServlet?method=ProductListByCidServlet&cid=${cid}&currentPage=${page }">${page }</a></li>
				</c:if>
			</c:forEach>
			
			
			<!-- 下一页 -->
			 <c:if test="${pageBean.currentPage==pageBean.totalPage }">
				<li class="disabled">
					<a href="javascript:void(0);" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			<c:if test="${pageBean.currentPage!=pageBean.totalPage }">
				<li>
					<a href="${pageContext.request.contextPath}/servlet/CategoryServlet?method=ProductListByCidServlet&cid=${cid}&currentPage=${pageBean.currentPage+1 }" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			
		</ul>
	</div>
	<!-- 分页结束 -->
 	<jsp:include page="/footer.jsp"></jsp:include><br>
  </body>
</html>

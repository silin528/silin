<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>    
 <script type="text/javascript">
	function search(){
		//获得购买的商品的数量
		var buyNum = $("#buyNum").val();
		location.href="${pageContext.request.contextPath}/servlet/ProductServlet?method=search&search="+buyNum;
		};


</script>
  </head>
  
  <body>
    <div class="container-fluid">
        <!-- 导入网页上部 -->
      	<jsp:include page="/header.jsp"></jsp:include>
        <!-- 导入网页菜单 -->
       <!--<%--@include file="/menu.jsp" --%><br>-->
       <!-- 搜索框 -->
	<div class="container-fluid jumbotron" style="height:400px;margin-top:40px;margin-bottom:40px;;background:url(${pageContext.request.contextPath}/image/topback.jpg) no-repeat;width:100%; 
    background-size:100% 120%;">
		<div class="row">
			<div class="col-md-12">
				<h1 style="color:#000"><strong>NET</strong></strong><img src="${pageContext.request.contextPath}/image/back.jfif" alt="BootCDN logo" class="hidden"></h1>
				<p class="text-right">科技推动网络发展<br>
					<span class="package-amount">欢迎来到<strong>silin</strong>官网</span>
				</p>
				<div class="search-wraper" >
					<div class="form-group">
						<form action="${pageContext.request.contextPath}/servlet/ProductServlet" method="get">
						 	<input type="hidden" name="method" value="searchServlet">
							<input name="search" style="border: 3px #888888 solid" class="form-control search clearable" placeholder="请输入搜索……" autocomplete="off" autofocus="" tabindex="0" autocorrect="off" autocapitalize="off" spellcheck="false">
							<input value="搜索" type="submit">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
      
	<div class="container-fluid row">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			<!-- 轮播图的中的小点 -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>
			<!-- 轮播图的轮播图片 -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="${pageContext.request.contextPath }/image/index/1.jpg">
					<div class="carousel-caption">
						<!-- 轮播图上的文字 -->
					</div>
				</div>
				<div class="item">
					<img src="${pageContext.request.contextPath }/image/index/2.jpg">
					<div class="carousel-caption col-md-12">
						<!-- 轮播图上的文字 -->
						<h3 style="color:red" class="text-left">渲染不同色彩，照亮世界</h3>
					</div>
				</div>
				<div class="item">
					<img src="${pageContext.request.contextPath }/image/index/4.jpg">
					<div class="carousel-caption">
						<!-- 轮播图上的文字 -->
					</div>
				</div>
			</div>

			<!-- 上一张 下一张按钮 -->
			<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<!--商品展示 -->
	<main>
	<section class="section" id="partners" style="margin-top:60px;">
        <div class="container">
            <header class="section-header" style="text-align: center;max-width: 70%;margin: 0 auto;">
                <h2>New Arrival</h2>
                <hr>
                <p class="lead">New Arrivals stay tuned.</p>
            </header>
            <div class="partner" style="height:560px;overflow:hidden;">
            	<c:forEach items="${newList}" var="newPro">
						<div class="col-md-2 col-sm-3 col-xs-4" style="text-align:center;height:220px;padding:10px 0px;border-bottom:1px solid #888888;margin-top:40px;margin-left:35px;">
							<a href="${pageContext.request.contextPath}/servlet/ProductServlet?method=ProductByIdServlet&pid=${newPro.pid}"> 
								<img src="${pageContext.request.contextPath }/${newPro.pimage }" width="130" height="150" style="display: inline-block;">
							</a>
							<p><a href="${pageContext.request.contextPath}/servlet/ProductServlet?method=ProductByIdServlet&pid=${newPro.pid}" style='color:#666'>${newPro.pname }</a></p>
							<p><font color="#E4393C" style="font-size:16px">&yen;${newPro.shop_price }</font></p>
						</div>
				</c:forEach>	
            </div>
        </div>
    </section>
    
    <section>
    <!-- 广告条 -->
      <div class="container-fluid" style="margin-top:50px;">
        <img src="${pageContext.request.contextPath }/products/hao/ad.jpg" width="100%" />
      </div>
    </section>
    
    <section class="section" id="partners" style="margin-top:60px;">
        <div class="container">
            <header class="section-header" style="text-align: center;max-width: 70%;margin: 0 auto;">
                <h2>Hot Products</h2>
                <hr>
                <p class="lead">Popular Recommend.</p>
            </header>
            <div class="partner"  style="height:280px;overflow:hidden;">
            	<c:forEach items="${hotList}" var="hotPro">
						<div class="col-md-2 col-sm-3 col-xs-4" style="text-align:center;height:220px;padding:10px 0px;border-bottom:1px solid #888888;margin-top:40px;margin-left:35px;">
							<a href="${pageContext.request.contextPath}/servlet/ProductServlet?method=ProductByIdServlet&pid=${hotPro.pid}"> 
								<img src="${pageContext.request.contextPath }/${hotPro.pimage }" width="130" height="150" style="display: inline-block;">
							</a>
							<p><a href="${pageContext.request.contextPath}/servlet/ProductServlet?method=ProductByIdServlet&pid=${newPro.pid}" style='color:#666'>${hotPro.pname }</a></p>
							<p><font color="#E4393C" style="font-size:16px">&yen;${hotPro.shop_price }</font></p>
						</div>

					</c:forEach>
            </div>
            <hr>
        </div>
    </section>
	</main>
  <jsp:include page="/footer.jsp"></jsp:include><br>
  
  
</html>

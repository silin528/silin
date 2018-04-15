<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    
    <!-- 引入表单校验jquery插件 -->
	<script type="text/javascript" src="js/jquery.validate.min.js" ></script>
	
	
  </head>
  
  <body>
	<nav class="navbar navbar-inverse container-fluid navbar-fixed-top " >
            <div>
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a id="_image2" itemprop="image" class="navbar-brand" title="engati" href="${pageContext.request.contextPath}">
                        <img src="#" class="header-engt-img" title="silin" alt="silin">
                    </a>
                </div>
                <!-- /.navbar-header -->
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right" >
                        <div class="pull-left">
                            <a href="${pageContext.request.contextPath}/cart.jsp" style="color:#FFA500;border-left:1px solid #fff;" title="Schedule Demo" class="btn  sign-up-btn demo-login-btn nav-transparent-btn">购物车</a>
                        </div>
                        <c:if test="${empty loginUser }">
	                        <div class="pull-left">
	                            <a  style="color:#FFA500" href="${pageContext.request.contextPath}/register.jsp" title="Register" class="btn demo-login-btn sign-up-btn nav-transparent-btn">注册</a>
	                        </div>
	                        <div class="pull-left header-login-btn">
	                            <a  style="color:#FFA500" href="${pageContext.request.contextPath}/servlet/UserServlet?method=LoginServlet" title="Login" class="btn demo-login-btn sign-up-btn red-button">登录</a>
	                        </div>
                    	</c:if>
                    	<c:if test="${!empty loginUser }">
							 <div class="pull-left header-login-btn">
	                            <a  style="color:#FFA500;" href="#" title="Login" class="btn demo-login-btn sign-up-btn red-button">欢迎：${loginUser.userName } </a>
	                        </div>
	                        <div class="pull-left header-login-btn">
	                            <a  style="color:#FFA500;" href="${pageContext.request.contextPath}/servlet/UserServlet?method=LogoutServlet" method="post" title="Login" class="btn demo-login-btn sign-up-btn red-button">退出 </a>
	                        </div>
                    	</c:if>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" id="categoryUl">
						<%-- <c:forEach items="${categoryList }" var="category">
							<li><a href="#" style="color:#000; font-size:16px;border-left: 1px solid #eee">${category.cname }</a></li>
						</c:forEach>--%>
					</ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
       </nav>
	
	<script type="text/javascript">
		//header.jsp加载完毕后启动服务端获取得所有category数据
		$(function(){
		var content = "";
			$.post(
				"${pageContext.request.contextPath}/servlet/CategoryServlet?method=CategoryListServlet",
				function(data){
					//[{"cid":"xxx","cname":"xxxx",},{},{}]
					//动态创建<li><a href="#" style="color:#000; font-size:16px;border-left: 1px solid #eee">${category.cname }</a></li>
					for(var i=0; i<data.length;i++){
						content +="<li><a href='${pageContext.request.contextPath}/servlet/CategoryServlet?method=ProductListByCidServlet&cid=" + data[i].cid+"'>" + data[i].cname + "</a></li>";
					}
					//将拼接好的li放置ul中
					$("#categoryUl").html(content);
				},"json"
			)
		});
	</script>
	
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
  </head>
  
  <body>
  <%@include file="/header.jsp" %>
  <div class="col-md-4 col-sm-4 col-xs-2"></div>
	<div class="col-md-4 col-sm-4 col-xs-4" style="margin-top:10%;margin-botton:100px;">
				<div
					style="background:url(${pageContext.request.contextPath}/image/index/3.jpg);margin-botton:30px;;margin-left:auto;margin-botton:30px;margin-right:30%;width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; ">
					<font>会员登录</font>USER LOGIN
					<div>&nbsp;</div>
					<form class="form-horizontal" action="${pageContext.request.contextPath}/servlet/UserServlet?method=LoginServlet" method="post">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-xs-6 col-sm-8">
								<input type="text" class="form-control" id="username"  name="userName"
									placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label" >密码</label>
							<div class="col-xs-6 col-sm-8 ">
								<input type="password" class="form-control"  name="password"
									placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-2 col-sm-10" >
								<input type="checkbox" width="100" name="autoLogin" />自动登录
								<input type="checkbox" style="margin-left:30px" width="100" name="remembe" />记住密码
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-xs-offset-2 col-sm-10" >
								<input type="submit" width="100" value="登录" name="submit"
									style=" height: 35px; width: 100px; color: white;background:url(${pageContext.request.contextPath}/image/login.gif) no-repeat 0px 0px;">
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-4 col-sm-4 col-xs-2"></div><br><br><br>
			<div class="navbar-fixed-bottom">
			<jsp:include page="/footer.jsp"></jsp:include><br>
			</div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

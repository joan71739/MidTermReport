<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, dao.*, servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style.css">
<title>查詢店家</title>
</head>
<body>
<header>

		<h2>查詢店家</h2>


		<a class="title" href="Store.jsp">店家管理</a> 
		<a class="title" href="LoginStore.jsp">店家登入</a> 
		
		</header>
		
		
		
		
		<div class="btnlo">

		
			<form action=".\NewSServlet" method="post" class="btn">
				<input type="submit" name="new" value="新增店家">
			</form>
			<br>
			<form action=".\UpdateStoreItem" method="post" class="btn">
				<input type="submit" name="querybtn" value="查詢店家">
			</form>
			<br>
			<form action=".\UpdateStoreItem" method="post" class="btn">
				<input type="submit" name="updatebtn" value="修改店家">
			</form>
			<br>
			<form action=".\UpdateStoreItem" method="post" class="btn">
				<input type="submit" name="deletebtn" value="刪除店家">
			</form>
			
			
			</div>
		



<div class="container">		

		<%
		String lostuserid = (String) session.getAttribute("lostuserid");
		String company =(String) session.getAttribute("company");
		session.setAttribute("lostuserid", lostuserid);
	
		%>

		<form action=".\UpdateStoreItem" method="post">
			<fieldset>
				<legend>使用企業統編查詢</legend>
				<div class="form">
					<label for="company" class="formt1">企業統編：</label> <%=company %>
				</div>

				<div class="sub">
					<input type="submit" name="query" value="送出"> 
				</div>
			</fieldset>
<br>
<br>
<br>
			<fieldset>
				<legend>使用店家帳號查詢</legend>
				<div class="form">
					<label for="stuserid" class="formt1">店家帳號：</label> <%=lostuserid %>
				</div>

				<div class="sub">
					<input type="submit" name="usquery" value="送出"> 
				</div>
			</fieldset>
		</form>
	</div>
	
	


</body>
</html>
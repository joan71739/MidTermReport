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
<title>新增店家</title>
</head>
<body>
<header>

		<h2>新增店家</h2>


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
		session.setAttribute("lostuserid", lostuserid);
		%>

		<form action=".\NewSServlet" method="post">
			<fieldset>
				<legend>新增店家</legend>
				<div class="form">
					<label for="stuserid" class="formt1">店家帳號：</label> <%=lostuserid %>
					
				</div>

				<div class="form">
					<label for="title" class="formt1">店家名稱：</label> 
					<input type="text" id="title" name="title" maxlength="20" required>
				</div>

				<div class="form">
					<label for="manager" class="formt1">店長：</label> <input type="text"
						id="manager" name="manager">
				</div>

				<div class="form">
					<label for="stadd" class="formt1">店家地址：</label> 
					<input type="text" id="stadd" name="stadd">
				</div>

				<div class="form">
					<label for="tel" class="formt1">聯絡電話：</label> 
					<input type="text" id="tel" name="tel">
				</div>

				<div class="form">
					<label for="intro" class="formt1">店家介紹：</label>
					<textarea id="intro" name="intro" rows="10" cols="30"></textarea>
				</div>

				<div class="sub">
					<input type="submit" name="submit" value="送出"> 
					<input type="reset" name="reset" value="清除">
				</div>
				
				</fieldset>
			</form>
			

			
	</div>


</body>
</html>
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


		<h3>
			企業統編：<%
		String company = (String) session.getAttribute("company");
		%><%=company%></h3>

		<%
		// Scriptlet 1: Display the items 
		ArrayList<StoreBean> SS = (ArrayList<StoreBean>) session.getAttribute("reg_array");
		for (StoreBean item : SS) {
		%>




		<form>
			<fieldset>
				<lable id="lb">店家ID：<%=item.getStoreid()%></label> <br>
				<lable id="lb">店家加入時間：<%=item.getStartdate()%></label> <br>
				<lable id="lb">店家帳號：<%=item.getStuserid()%></label> <br>
				<lable id="lb">店家名稱：<%=item.getTitle()%></label> <br>
				<lable id="lb">店長：<%=item.getManager()%></label> <br>
				<lable id="lb">店家地址：<%=item.getStadd()%></label> <br>
				<lable id="lb">聯絡電話：<%=item.getTel()%></label> <br>
				<lable id="lb">店家介紹：<%=item.getIntro()%></label> <br>
			</fieldset>
		</form>

		<%
		} // for

		String lostuserid = (String) session.getAttribute("lostuserid");
		session.setAttribute("lostuserid", lostuserid);
		

	
		%>









	</div>
</body>
</html>
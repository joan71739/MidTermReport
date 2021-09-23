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
<title>刪除店家</title>
</head>
<body>
	<jsp:useBean id="reg_store" class="dao.StoreBean" scope="session" />

	<header>

		<h2>刪除店家</h2>


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

		StoreBean storebean = (StoreBean) session.getAttribute("reg_store");
		if (storebean.getStoreid() != null) {
		%>


		<form action=".\UpdateStoreItem" method="post">
			<fieldset>
				<lable>店家帳號：${reg_store.stuserid} </label> <br>
				<lable>店家名稱：${reg_store.title}</label> <br>
				<lable>店長：${reg_store.manager}</label> <br>
				<lable>店家地址：${reg_store.stadd}</label> <br>
				<lable>聯絡電話：${reg_store.tel}</label> <br>
				<lable>店家介紹：${reg_store.intro}</label> <br>
				<div class="sub">
					<input type="submit" name="CFdelete" value="確認刪除">


				</div>
			</fieldset>
		</form>

		<%
		} else {
		%>
	
				<h3>您尚未新增此帳號的店家資料</h2>
				


		<%
		}
		%>










	</div>
</body>
</html>
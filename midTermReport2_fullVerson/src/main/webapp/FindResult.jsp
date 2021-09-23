<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="empJavaBean.EmpJavaBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find Result</title>
<style>
* {
	padding: 0;
	margin: 0;
}

body {
	background-color: #FBFBEA;
}

table {
	background-color: white;
	width: 400px;
	border-collapse: collapse;
	text-align: center;
}

div {
	background-color: #D7FFF0;
	border: 2px solid black;
	border-radius: 20px;
	width: 800px;
	padding: 10px;
	margin: 20px auto;
}
</style>
</head>
<body>
<div>
<h2 align="center">員工資料</h2>
<table border='1' align="center">
	<tr>
		<td>id</td>
		<td>姓名</td>
		<td>職稱</td>
		<td>入職日期</td>
		<td>薪水</td>
	</tr>
<% EmpJavaBean empJavaBean =(EmpJavaBean)request.getAttribute("resultmember"); %>
	<tr>
	<td><%=empJavaBean.getId() %></td>
	<td><%=empJavaBean.getName() %></td>
	<td><%=empJavaBean.getJob() %></td>
	<td><%=empJavaBean.getHiredate() %></td>
	<td><%=empJavaBean.getSal() %></td>
	</tr>
	<p align="center" style="margin:10px 0px 10px 0px">
	<a href="./LookUpServlet">回主畫面</a>
	</p>
</div>
</body>
</html>
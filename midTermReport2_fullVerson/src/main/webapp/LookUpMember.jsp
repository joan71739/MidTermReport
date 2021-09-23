<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="empJavaBean.EmpJavaBean"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LookUp Member</title>
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
a{
	margin:0px 10px 0px 10px ;
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
			<%
			List<empJavaBean.EmpJavaBean> memberArrayList = (List<empJavaBean.EmpJavaBean>) request.getAttribute("all");
			%>
			<%
			for (empJavaBean.EmpJavaBean e : memberArrayList) {
			%>
			<tr>
				<td><%=e.getId()%></td>
				<td><%=e.getName()%></td>
				<td><%=e.getJob()%></td>
				<td><%=e.getHiredate()%></td>
				<td><%=e.getSal()%></td>
			</tr>
			<%
			}
			%>
			<p align="center" style="margin:10px 0px 10px 0px">
			<a href="./InsertEmp.jsp">新增員工</a>
			<a href="./IndexEmp.jsp">查詢單一員工</a>
			<a href="./UpdateEmp.jsp">修改員工</a>
			<a href="./DeleteEmp.jsp">刪除員工</a>
			</p>

	</div>
</body>
</html>
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
<title>修改店家</title>
</head>
<body>
	<jsp:useBean id="reg_store" class="dao.StoreBean" scope="session" />

		<header>

		<h2>修改店家</h2>


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
		StoreBean storebean = (StoreBean) session.getAttribute("reg_store"); 
		String lostuserid = (String) session.getAttribute("lostuserid");
		session.setAttribute("lostuserid", lostuserid);
	
		if( storebean.getStuserid() != null){ 
		%>

			

		<form action=".\UpdateStoreItem" method="post">
			<fieldset>
				
								
				<lable>店家帳號：<%=lostuserid %></label> <br>

				<hr>


				<div class="form">
					<label for="title" class="formt1">店家名稱：</label> <input type="text"
						id="title" name="title" maxlength="20" value=${reg_store.title
						} required>
				</div>

				<div class="form">
					<label for="manager" class="formt1">店長：</label> <input type="text"
						id="manager" name="manager" value=${reg_store.manager}>
				</div>

				<div class="form">
					<label for="stadd" class="formt1">店家地址：</label> <input type="text"
						id="stadd" name="stadd" value=${reg_store.stadd}>
				</div>

				<div class="form">
					<label for="tel" class="formt1">聯絡電話：</label> <input type="text"
						id="tel" name="tel" value=${reg_store.tel}>
				</div>

				<div class="form">
					<label for="intro" class="formt1">店家介紹：</label>
					<textarea id="intro" name="intro" rows="10" cols="30">${reg_store.intro}</textarea>
				</div>
			
				<input class="btn" type="submit" name="confirm" value="確認">
			
			
			
			
			</fieldset>
		</form>


<%} else { %>


				<h3>您尚未新增此帳號的店家資料,請至"新增店家"</h3>



<%} %>






				

				
	</div>
</body>
</html>
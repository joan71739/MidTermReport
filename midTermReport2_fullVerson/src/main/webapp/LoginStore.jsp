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
<title>店家登入</title>
</head>

<body>



<header>

		<h2>店家登入</h2>


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

		<form action=".\LoginStore" method="post">
			<fieldset>
				<legend>店家登入</legend>
				<div class="form">
					<label for="stuserid" class="formt1">店家帳號：</label> 
					<input type="text" id="stuserid" name="stuserid" size="20" required>
					<span id="stsp"></span><br />
					
				</div>
				<div class="form">
					<label for="password" class="formt1">店家密碼：</label> 
					<input type="password" id="psid" name="password" size="20" required>
					<span id="pwdsp"></span><br />
				</div>

				<div class="sub">
					<input type="submit" name="login" value="送出"> 
					<input type="reset" name="reset" value="清除">
				</div>
				
		<%
		
		String lostuserid = (String) session.getAttribute("lostuserid");
		session.setAttribute("lostuserid", lostuserid);
	
		%>
				



			</fieldset>
		</form>
	</div>


<script>

document.getElementById("psid").onblur = pwdblur;
document.getElementById("stuserid").onblur = stidblur;




function stidblur(){
	
	let theSTObj = document.getElementById("stuserid");
    let theSTObjVal = theSTObj.value;
    let sp = document.getElementById("stsp");


    if (theSTObjVal == "") {
    	/* 2021/7/14 冠維 名字衝突 error 改名 error1 */
       sp.innerHTML = "<img src='images/error1.png'><i style=\"color:red; font-size:14px;\">不可為空白</i>"
    }else {
    	sp.innerHTML = "<img src='images/ok.png'><i style=\"color:green; font-size:14px;\">格式正確</i>"
    }
    
}
    

function pwdblur() {
    let thePwdObj = document.getElementById("psid");
    let thePwdObjVal = thePwdObj.value;
    let sp = document.getElementById("pwdsp");


    if (thePwdObjVal == "") {
    	/* 2021/7/14 冠維 名字衝突 error 改名 error1 */
       sp.innerHTML = "<img src='images/error1.png'><i style=\"color:red; font-size:14px;\">不可為空白</i>"
    }else {
    	sp.innerHTML = "<img src='images/ok.png'><i style=\"color:green; font-size:14px;\">格式正確</i>"
    }
}


</script>
</body>
</html>
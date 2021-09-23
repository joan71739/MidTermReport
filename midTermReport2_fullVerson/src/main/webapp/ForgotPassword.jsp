<%@page import="login.MemberDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="login.SQLMethod"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ForgotPassword</title>
<!-- 2021/7/14 冠維 加入 jQuery CSS 樣式(配合 jQuery UI)-->
<link rel="stylesheet" href="jquery-ui-1.12.1/jquery-ui.css">
<style>
fieldset {
	width: 700px;
	border: 2px solid black;
	border-radius: 10px;
	margin-top: 30px;
	font-family: 標楷體;
	box-shadow: 0 0 20px rgba(73, 48, 48, 0.5), 0 1px 2px rgba(0, 0, 0, 0.3);
}

legend {
	margin: 5px 10px;
	font-size: 30px;
//	padding: 0 10px 10px;
}

.d1 {
	display: flex;
	position: relative;
	justify-content: center;
}

.dd1 {
	margin-bottom: 20px;
	display: flex;
	width: 100%;
}

.dd1 div {
	width: 400px;
}
.dd2 {
        margin-bottom: 20px;
      }
.spa {
	font-size: 10px;
	color: gray;
}

.btn {
	width: 100%;
	position: relative;
	display: flex;
	justify-content: center;
}

.btn button {
	margin: 20px 10px 10px 10px;
}

</style>
</head>
<body>

	<div class="d1">
	
		<section>
			<form id="nForm" action="./LoginForm" method="post">
			  <input type="hidden" id="cla" name="type" value="form-a" />
				<fieldset>
				<%
				String sel_A ="selected";
				String sel_B ="";
				String type_A="";
				String type_B="hidden";
				String sty_A="";
				String sty_B="style =\"display:none\"";
				String sty_C="";
				String formN= (String) request.getSession().getAttribute("Type");
				if(formN != null){
					if(formN.equals("A")){
						sel_A ="selected";
						sel_B ="";
						type_A="";
						type_B="hidden";
						sty_A="";
						sty_B="style =\"display:none\"";
					}
					if(formN.equals("B")){
						sel_A ="";
						sel_B ="selected";
						type_A="hidden";
						type_B="";
						sty_A="style =\"display:none\"";
						sty_B="";
					}
					if(formN.equals("C")){
						type_A="hidden";
						type_B="hidden";
						sty_A="style =\"display:none\"";
						sty_B="style =\"display:none\"";
						sty_C="style =\"display:none\"";
					}
				}
				
				%>
					<legend>找回密碼</legend>
					<div class="dd1">
					
					<select id="sel" <%=sty_C %>>
    						<option value="memberA" <%=sel_A %>>一般會員</option>
    						<option value="memberVendorB" <%=sel_B %>>企業會員</option>
  					</select>
					
					</div>
					<div class="dd1">
						<div>
							<label for="userID">帳號：</label>
							
							<input type="text" id="userID" name="userID" value="" style="width: 100px" />
							
						</div>
						<div>
                			<%
                			String asErr = (String) request.getSession().getAttribute("adm");
            				if(asErr != null){
            				%>
            					<h1 style="color: red;">帳號錯誤</h1>
            				<%
            				session.invalidate();
            				}
                			%>
						</div>
					</div>
					<div class="dd1">
						<div>
							<label for="birthday" <%=sty_A %>>出生年月日：</label>
							
							<input type="<%=type_A %>" id="birthday" name="birthday" value="" style="width: 100px" autocomplete="off"/>
							
							<label for="principal" <%=sty_B %>>負責人：</label>
                			
                			<input type="<%=type_B %>" name="principal" value="" style="width: 100px" />
							
							<%
                			String senEmail = (String) request.getSession().getAttribute("Type");
            				if(senEmail != null){
            					if(senEmail.equals("C")){
            				%>
            					<h1 style="color: red;">請至信箱收信</h1>
            				<%
            					session.invalidate();
            					}
            				}
                			%>
						</div>
						<div>
							<label for="vat" <%=sty_B %>>統一編號：</label>
                			
                			<input type="<%=type_B %>" id="vat" name="vat" value="" style="width: 100px" />
							
						</div>
					</div>
					
					<div class="dd2">
              			<div>
                			<!-- <label for="email" <%=sty_A %>>E-mail：</label> -->
                			<label for="email">E-mail：</label>
			                
			                	<!-- <input type="<%=type_A %>" name="email" value="" style="width: 300px" autocomplete="off" /> -->
			                	<input type="text" name="email" value="" style="width: 300px" autocomplete="off" />
                			
			              </div>
			          </div>
			           
						<div class="btn">
						<button type="submit" id="sub1" name="sub" value="forgChack">送出</button>
						<button type="submit" name="sub" value="cancel">取消</button>

					</div>
				</fieldset>
			</form>
		</section>
	</div>
	
	
	<script src="jquery-3.6.0.js"></script>
	<script src="jquery-ui-1.12.1/jquery-ui.js"></script>
	<script>
	$(function () {
		//日期選擇
		$("#birthday").datepicker({
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
		     changeYear: true
		     });
	})
		//選擇類別
		document.addEventListener("DOMContentLoaded", function () {
	        document.getElementById("sel").addEventListener("change",ss);
	        document.getElementById("sub1").addEventListener("click",addFormN);
	        //document.getElementById("sub2").addEventListener("click",addFormN);
	      });
	      //
	      function ss(){
	      let se=document.getElementById("sel").value
	        if(se=="memberA"){
	          //隱藏碼
	          document.getElementById("cla").value="form-a"
	          document.getElementById("nForm").submit();
	        }
	        if(se=="memberVendorB"){
	          document.getElementById("cla").value="form-b"
	          document.getElementById("nForm").submit();
	        }
	      }
		//送出編碼
		//document.getElementById("sub1").click = addFormN;
		function addFormN() {
			let se=document.getElementById("sel").value
	        if(se=="memberA"){
	          //隱藏碼
	          document.getElementById("cla").value="form-a"
	        }
	        if(se=="memberVendorB"){
	          document.getElementById("cla").value="form-b"
	        }
		}
	</script>

</body>
</html>
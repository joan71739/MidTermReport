<%@page import="login.SQLMethod"%>
<%@page import="login.DAOFactory"%>
<%@page import="java.sql.*"%>
<%@page import="login.MemberDB"%>
<%--
	import="com.sun.org.glassfish.external.statistics.annotations.Reset" 
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ModifyMember</title>
<style>
fieldset {
        width: 500px;
        border: 2px solid black;
        border-radius: 10px;
        margin-top: 30px;
        font-family: 標楷體;
        box-shadow: 0 0 20px rgba(73, 48, 48, 0.5), 0 1px 2px rgba(0, 0, 0, 0.3);
      }
      legend {
        margin: 5px 10px;
        font-size: 30px;
        padding: 0 10px 10px;
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
        width: 300px;
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
.deldiv{
	position: relative;
	justify-content: center;
    display: flex;
	height: 30px;
}

</style>
</head>
<body>
	<div class="d1">
		<section>
			<form id="nForm" action="./ModifyMember" method="post">
			  <input type="hidden" name="type" value="form-a_mo" />
			  <%
			  String userID = (String) request.getSession().getAttribute("UserID");
			  String idClass = (String) request.getSession().getAttribute("ClassMember");
			  boolean ffa = false;
			  if(userID == null){
					response.sendRedirect("./LoginPage.jsp");
					return;
				}
				//是否為一般會員
				if(!idClass.equals("a")){
					if(idClass.equals("fa")){
						ffa = true;
					}else{
						response.sendRedirect("./homePage.html");
						return;
					}
				}
			  String deleteID = (String) request.getSession().getAttribute("Delete");
				if(deleteID != null){
					if(deleteID.equals("delete")){
					%>
					<fieldset>
						<legend>會員刪除</legend>
						<div class="dd1">
							<div>
								
							</div>
							<div>
								
							</div>
						</div>
						<div class="dd1" style="width: 100%;">
							<div class="deldiv" style="width: 600px;">
								<input type="hidden" name="userID" value="<%=userID %>" />
								<h1 style="padding: 0; margin: 0; margin-right: 30px;">是否確認刪除會員?</h1>
								
								<button type="submit" name="sub" value="deleteCk" >確認刪除會員</button>
								<button type="submit" name="sub" value="cancelCk" style="margin-left: 20px;">取消</button>
							</div>

						</div>
						<div class="dd1">
							<div>
								
							</div>
							<div>
								
							</div>
						</div>
						<div>
							<div>
								
							</div>
						</div>
						<div class="btn">
							
						</div>
						
						
					<%	
					
					}
				}
				if(deleteID == null || "".equals(deleteID)) {
					 SQLMethod sqlmethod = new SQLMethod();
						sqlmethod.createConn();
						ResultSet res1 = sqlmethod.searchMember(userID);
						ResultSet res2 = sqlmethod.searchMemberData(userID);
						String pwsString = res1.getString(2);// 密碼
						String nameString = res2.getString(2);
						String birthdayString = res2.getString(3);
						String emailString = res2.getString(4);
						sqlmethod.closeConn();
				  %>
					<fieldset>
						<legend>會員資料</legend>
							<%
			              	if(ffa){
			              	%>
			              	<h1 style="color: red">請先修改密碼</h1>
			              	<%	
			              	}
			              	%>
						<div class="dd1">
							<div>
								<%
								out.println("<label>帳號："+userID+"</label>" );
	                			%>
							</div>
							<div></div>
						</div>
						<div class="dd1">
							<div>
								<%out.println("<label>密碼：**********</label>" );
	               				%>
							</div>
							<div>
								
							</div>
						</div>
						<div class="dd1">
							<div>
								<label>姓名：<%=nameString%></label>
							</div>
							<div>
								<label>出生年月日：<%=birthdayString %></label>
							</div>
						</div>
						<div>
							<div>
								<label>E-mail：<%=emailString %></label>
							</div>
						</div>
						<div class="btn">
							<button type="submit" name="sub" value="modify">修改</button>
							<button type="submit" name="sub" value="delete">刪除帳號</button>
							<button type="submit" name="sub" value="cancel">取消</button>
						</div>
				<%
				}//else
				%>
				</fieldset>
			</form>
		</section>
	</div>

</body>
</html>

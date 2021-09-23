<%@page import="java.sql.ResultSet"%>
<%@page import="login.SQLMethod"%>
<%@page import="login.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ModifyMemberVendor</title>
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
        margin: 10px 10px 10px 10px;
      }
      #pwsck{
        color:red;
      }
    </style>
  </head>
  <body>
    <div class="d1">
      <section>
        <form id="nForm" action="./ModifyMember" method="post">
         <input type="hidden" name="type" value="form-b" />
          <fieldset>
          <%
         
          String userID = (String) request.getSession().getAttribute("UserID");
          String rePws = (String) request.getSession().getAttribute("Type");
          		
          		SQLMethod sqlmethod = new SQLMethod();
				sqlmethod.createConn();
				ResultSet res1 = sqlmethod.searchMember(userID);
				ResultSet res2 = sqlmethod.searchMemberDataVendor(userID);
				String pwsString = res1.getString(2);// 密碼
				String vatString = res1.getString(4);//統一編號
				String vendorString = res2.getString(2);
				String principalString = res2.getString(3);
				String addressString = res2.getString(4);
				String emailString = res2.getString(5);
				sqlmethod.closeConn();
			%>
            <legend>企業會員資料修改</legend>
            	<%
              	if("C".equals(rePws)){
              		pwsString = "";
              	%>
              	<h1 style="color: red">請先修改密碼</h1>
              	<%	
              	}
              	%>
            <div class="dd1">
              <div>

                <label for="vendor">企業名稱：</label>
                <%String vendor = vendorString;
                		if(vendor != null){
                %>
                	<input type="text" id="vendor" name="vendor" value="<%=vendor%>" style="width: 100px" />
                <%
                		}else{
                			out.println("<input type=\"text\" id=\"vendor\" name=\"vendor\" style=\"width: 100px\" />");
                		}
                %>
                <!-- <input type="text" id="vendor" name="vendor" style="width: 80px" /> -->
              </div>
              <div>
                
                <%
                out.println("<label>帳號："+userID+"</label>");
                %>
				<input type="hidden" name="userID" value="<%=userID %>" />
              </div>
            </div>
            <div class="dd1">
              <div>
              	
                <label for="password">密碼：</label>
                <%String password = pwsString;
                		if(password != null){
                %>
                	<input type="password" id="pws" name="pws" value="<%=password%>" style="width: 100px" />
                <%
                		}else{
                			out.println("<input type=\"password\" id=\"pws\" name=\"pws\" style=\"width: 100px\" />");
                		}
                %>
                <!-- <input type="password" id="password" name="password" style="width: 100px" /> -->
                <span id="pwsW" class="spa">至少6個字且必須包含英數字</span>
              </div>
              <div>
                <label for="passwordck">密碼確認：</label>
                <%String passwordck = pwsString;
                		if(passwordck != null){
                %>
                	<input type="password" id="passwordck" name="passwordck" value="<%=passwordck%>" style="width: 100px" />
                <%
                		}else{
                			out.println("<input type=\"password\" id=\"passwordck\" name=\"passwordck\" style=\"width: 100px\" />");
                		}
                %>
                <!-- <input type="password" id="passwordck" name="passwordck" style="width: 100px" /> -->
                <span id="pwsck" class="spa"></span>
              </div>
            </div>
            <div class="dd1">
              <div>
                <label for="principal">負責人：</label>
                <%
                String principal = principalString;
                		if(principal != null){
                %>
                	<input type="text" id="principal" name="principal" value="<%=principal%>" style="width: 100px" />
                <%
                		}else{
                			out.println("<input type=\"text\" id=\"principal\" name=\"principal\" style=\"width: 100px\" />");
                		}
                %>
                <!-- <input type="text" id="principal" name="principal" style="width: 100px" /> -->
              </div>
              <div>
                <label for="vat">統一編號：</label>
                <%String vat = vatString;
                		if(vat != null){
                %>
                	<input type="text" id="vat" name="vat" value="<%=vat%>" style="width: 100px" />
                <%
                		}else{
                			out.println("<input type=\"text\" id=\"vat\" name=\"vat\" style=\"width: 100px\" />");
                		}
                %>
                <!-- <input type="text" id="vat" name="vat" style="width: 100px" /> -->
              </div>
            </div>

            <div class="dd2">
              <div>
                <label for="address">住址：</label>
                <%String address = addressString;
                		if(address != null){
                %>
                	<input type="text" id="address" name="address" value="<%=address%>" style="width: 300px" autocomplete="off" />
                <%
                		}else{
                			out.println("<input type=\"text\" id=\"address\" name=\"address\" style=\"width: 300px\" autocomplete=\"off\"/>");
                		}
                %>
                <!-- <input type="text" id="address" name="address" style="width: 300px" /> -->
              </div>
            </div>
            <div>
              <div class="dd2">
              
                <label for="email">E-mail：</label>
                <%String email = emailString;
                		if(email != null){
                %>
                	<input type="text" id="email" name="email" value="<%=email%>" style="width: 300px" autocomplete="off" />
                <%
                		}else{
                			out.println("<input type=\"text\" id=\"email\" name=\"email\" style=\"width: 300px\" autocomplete=\"off\"/>");
                		}
                %>
                <!-- <input type="text" id="email" name="email" style="width: 300px" /> -->
              </div>
            </div>
            <div class="btn">
              <button type="submit" name="sub" value="modifyVendorSubmit">確認修改</button>
              <button type="submit" name="sub" value="cancelVendor">取消</button>
             
            </div>
          </fieldset>
        </form>
      </section>
    </div>

	<script>
   document.getElementById("pws").onblur = pwsCk;
          function pwsCk() {
            let thePwsObjVal = document.getElementById("pws").value;
            let sp = document.getElementById("pwsW");
            let thp1 = false,
              thp2 = false
              // thp3 = false;
            //let spText = /^[!@#$%^&*]$/;
            let pwsL = thePwsObjVal.length;
            if (thePwsObjVal == "") {
              sp.innerHTML = "<img src='./images/error.png'>不可為空白";
              document.getElementById("pwsW").style.color = "red";
            } else if (pwsL < 6) {
              sp.innerHTML =
                "<img src='./images/error.png'>至少6個字且必須包含英數字";
              document.getElementById("pwsW").style.color = "red";
            } else if (pwsL >= 6) {
              for (let i = 0; i < pwsL; i++) {
                let ch = thePwsObjVal.charAt(i).toUpperCase();
                if (ch >= "A" && ch <= "Z") {
                  thp1 = true;
                }
                if (ch >= "0" && ch <= "9") {
                  thp2 = true;
                }
              }
              
              if (thp1 && thp2) {
                sp.innerHTML = "<img src='./images/correct.png'>正確";
                document.getElementById("pwsW").style.color = "green";
              } 
             
            }
          }
          
		document.getElementById("passwordck").onblur = checkPws;
			function checkPws() {
	            let thePwsCkObjVal = document.getElementById("passwordck").value;
	            let sp = document.getElementById("pwsck");
	            let pw = document.getElementById("pws").value
	            let thp1 = false,
	              thp2 = false
	              // thp3 = false;
	            //let spText = /^[!@#$%^&*]$/;
	            let pwsL = thePwsCkObjVal.length;
	            if (thePwsCkObjVal == "") {
	              sp.innerHTML = "<img src='./images/error.png'>不可為空白";
	              document.getElementById("pwsck").style.color = "red";
	            }else if(pw !== thePwsCkObjVal){
	            	sp.innerHTML = "<img src='./images/error.png'>兩次密碼不符"
	            	document.getElementById("pwsck").style.color = "red";
	            }else if (pwsL < 6) {
	              sp.innerHTML =
	                "<img src='./images/error.png'>至少6個字且必須包含英數字";
	              document.getElementById("pwsck").style.color = "red";
	            } else if (pwsL >= 6) {
	              for (let i = 0; i < pwsL; i++) {
	                let ch = thePwsCkObjVal.charAt(i).toUpperCase();
	                if (ch >= "A" && ch <= "Z") {
	                  thp1 = true;
	                }
	                if (ch >= "0" && ch <= "9") {
	                  thp2 = true;
	                }
	              }
	              if (thp1 && thp2 && (pw === thePwsCkObjVal)) {
	                sp.innerHTML = "<img src='./images/correct.png'>正確";
	                document.getElementById("pwsck").style.color = "green";
	              } 
	            }
		}
		document.getElementById("userID").onblur = checkUserID;

		function checkUserID() {
			document.getElementById("nForm").submit();
			//nForm.submit();
		}
    </script>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>LoginPage</title>
    <link rel="stylesheet" href="cs.css" />
    <style>

      button{
        width: 80px;
        margin: 5px 10px;
      }
	    .err{
    	    font-size: 20px;
            margin-top: 10px;
            color: red;
	        }
        .lop{
			font-size: 20px;
			margin: 80px 0px 30px 0px;
		}
    </style>

</head>
<body>
	<!-- <section> -->
    <div class="di1">
      <div class="login">
      <% 
       String lon = (String)request.getSession().getAttribute("Longin");
         if(lon == null){%>
        <h1>登入</h1> 
        <%}else {%>
        <h1>登出</h1> 
        <%
        }
        %>
        <form action="./LoginForm" method="post">
          <p>
          <%
          if(lon == null){
			
				Cookie cke = null;
		    	  Cookie[] cookies = null;
		    	  String userRe = null;
	   			  String usID = null;

		    	  cookies = request.getCookies();
		    	  if(cookies != null){
		    		  for( int i = 0; i<cookies.length; i++){
		    			  cke = cookies[i];
		    	    	  String cc = cke.getName();
		    	    	  	if(cc.equals("UserID")){
		    	          		usID = cke.getValue();
		    	    	  	}
		    	    	  	cc = cke.getName();
		    	    	  	if(cc.equals("remember_me")){
		    	          		userRe = cke.getValue();
		    	    	  	}
		    		  }
		    	  }
		    	  
		          if(usID != null){
		          %>
		        	  <input type="text" name="userID" value="<%=usID%>" placeholder="Username" />
		          <%
		          } else {
		        	  out.println("<input type=\"text\" name=\"userID\" value=\"\" placeholder=\"Username\" />");
		          }
		          %>
		            
		          </p>
		          <p>
		            <input
		              type="password" name="password" value="" placeholder="Password" />
		          </p>
				  
		          <p class="remember_me">
		            <label>
		            <%
				   	if(userRe != null && !"".equals(userRe)){
				   	%>
					   <input type="checkbox" name="remember_me" value="remember_me" checked="checked"/>記住我
					<%
				   	}else {
				   		out.println("<input type=\"checkbox\" name=\"remember_me\" value=\"remember_me\" />記住我");
				   	}
				  	%>
		            </label>
		          </p>

		          <p class="new_member"><a href="NewMemberAll.jsp">註冊會員</a>
		            </p>
		          <p class="submit">
		            <!--  <input type="submit" name="commit" value="登入" />-->
		            <!-- <input type="submit" value="登入" /> -->
		            <button type="submit" name="sub" value="login">登入</button>
		            <button type="submit" name="sub" value="cancel">取消</button>
		            
		          </p>
		          <div class="login-help">
		            <p>Forgot your password? <a href="ForgotPassword.jsp">Click here to reset it</a>.</p>

		            <%         
		            String useID = (String) request.getSession().getAttribute("UserID");
		            String massag = "";
		            String fal = "t";
		            fal = (String) request.getSession().getAttribute("fal");
		            if(fal == null){
		            	fal = "t";
		            }
		            if(!(useID == null) && !fal.equals("t"))
		            {
		            	massag = "<p class="+"err"+">登入失敗</p>";
		    			session.invalidate();
		            }
					%>
					<%= massag %>
			<%
             }//if
             if(lon != null){
            	 String lonID= (String) request.getSession().getAttribute("UserID");
            %>
            	<p class="lop"><%=lonID %> 是否登出?</p>
 				<button type="submit" name="sub" value="longout">登出</button>
 				<button type="submit" name="sub" value="cancel">取消</button>
             <%
             }
             %>
          
          </div>
        </form>
      </div>
    </div>
    <!-- </section> -->
	
</body>
</html>
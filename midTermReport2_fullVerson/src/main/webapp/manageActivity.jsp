<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List" import="activityJavaBean.ActivityJavaBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>manageActivity Page</title>
<style>
	*{
		padding: 0;
		margin: 0;
		list-style: none;
	}
	.container{
		width:1200px;
		margin: auto;
		background-color: lightgreen;
	}
	.container>h3{
		padding: 20px 0;
		text-align: center;
		font-size: 40px;
	}
	.container .activityArea{
		width: 1096px;
		margin: auto;
		display: flex;
		flex-direction:row;
		flex-wrap:wrap;
		justify-content: flex-start;
		align-content: flex-start;
		/* align-items: center; */
	}

	.container .activityBox{
		width: 500px;
		height:250px;
		margin: 20px;
		/* flex-grow: 1; */
		/* flex-basis: ; */
		/* flex-shrink: ; */
		/* align-items: ; */
		border: 4px solid rgba(0,0,0,0);
		border-radius: 5px;
		background-color: lightgoldenrodyellow;
		transition: border .5s;
	}
	.container .activityBox a{
		display: block;
		width: 100%;
		height: 100%;
		text-decoration:none;
		color: #000;
	}
	.container .activityBox h2{
		font-size: 27px;
	}
	.container .activityBox h4{
		padding: 80px 0 ;
		font-size: 30px;
		line-height:30px;
		text-align:center;
	}
	.container .activityBox:last-child{
		justify-content: flex-start;
		border: 4px dashed rgba(0,0,0,0);
	}
	.container .activityBox:last-child a{
		display: block;
		width: 100%;
		height: 100%;
		text-align: center;
		text-decoration:none;
	}
	.container .activityBox:last-child .addImg{
		display: inline-block;
		width: 140px;
		margin-top: 40px;
	}
	.container .activityBox:last-child h3{
		font-size: 30px;
		color:rgb(102, 211, 102)
	}
	.container .activityBox:hover{
		cursor:pointer;
		border: 4px solid rgba(0,0,0,1);
	}
	.container .activityBox:last-child:hover{
		cursor:pointer;
		border: 4px dashed rgba(0,0,0,1);
	}
</style>
</head>
<body>
	<% int activityIndex = 1; %>
	<div class="container">
		<h3>目前活動</h3>
		<ul class="activityArea">
		
			<%
			List<ActivityJavaBean> activityArrayList = (List<ActivityJavaBean>)request.getAttribute("allActivity");
			%>
			<%
			for(ActivityJavaBean item : activityArrayList) {
				String activityDetailString = "\"./activityDetailServlet?ID="+ item.getID() +"\"";
			%>	
		
			<li class="activityBox">
				<a href=<%=activityDetailString %>>
					<h2>活動<%= activityIndex %>:</h2>
					<%--
					<div>ID:<%= item.getID() %></div>
					 --%>
					<h4><%=item.getActivityTopic() %></h4>
					<%--
					<div>開始時間:<%=item.getStartTime() %></div>
					<div>結束時間:<%=item.getEndTime() %></div>
					<div>活動內容:<%=item.getActivityContent() %></div>
					<div>活動類型:<%=item.getActivityType() %></div>
					 --%>
					 
				</a>
			</li>
			
			<%
				activityIndex++;
			}
			%>
			
			<li class="activityBox">
				<a href="./setActivity.jsp">
					<img src="./images/add.png" class="addImg" alt="addImg">
					<h3>增加活動</h3>
				</a>
			</li>
			
		</ul>
	</div>
</body>
</html>
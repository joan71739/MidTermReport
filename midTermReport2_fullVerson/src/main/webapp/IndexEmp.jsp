<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Emp</title>
<style>
        * {
            padding: 0;
            margin: 0;
        }
        body{
            background-color: #FBFBEA;
        }
        
        div{
            background-color: #D7FFF0;
            border: 2px solid black;
            border-radius: 20px;
            width: 800px;
            padding: 10px;
            margin: 20px auto;
        }
        h2{
            text-align: center;
        }
        p{
        	margin:10px 0px 10px 0px
        }
        .button{
            text-align: center;
        }
</style>
</head>
<body>
<div>
<h2>查詢員工資料</h2>
	<form action="./IndexServlet" method="post">
	<p>輸入欲查詢Id:		
	<input type="text" name="id" size="4"></p>
	
	<p class="button">
			<input type="submit" name="submit" value="查詢" style="width:40px;height:30px;">
	</p>
	</form>
	<p align="center" style="margin:10px 0px 10px 0px">
	<a href="./LookUpServlet">回主畫面</a>
	</p>
</div>

</body>
</html>
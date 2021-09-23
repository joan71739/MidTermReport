<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Emp</title>
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
        p{
        	margin:10px 0px 10px 0px
        }
        h2{
            text-align: center;
        }
        .button{
            text-align: center;
        }
</style>
</head>
<body>
<div>
<h2>刪除員工資料</h2>
	<form action="./DeleteServlet" method="post">
	<p>輸入欲刪除Id:		
	<input type="text" name="id" size="4"></p>
		
	
	<p class="button">
			<input type="submit" name="submit" value="刪除" style="width:40px;height:30px;">
	</p>
	<p align="center" style="margin:10px 0px 10px 0px">
	<a href="./LookUpServlet">回主畫面</a>
	</p>
	</form>
</div>
</body>
</html>
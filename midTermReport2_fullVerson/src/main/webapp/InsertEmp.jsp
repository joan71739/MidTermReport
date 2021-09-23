<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Emp</title>
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
        .example{
            font-size: 14px;
            color: gray;
        }
        .button{
            text-align: center;
        }
</style>
</head>
<body>
<div>
    <h2>新增員工資料</h2>
    <form action="./MemberServlet" method="post">
    
        <p>姓名:</p>
        <p><input type="text" name="name" size="8"></p>

        <p>職稱:</p>
        <p>
        <select name="job">
            <option>store manager</option>
            <option>manager</option>
            <option>clerk </option>
            <option>part-time worker</option>    
        </select>
        </p>

        <p>入職日期:</p>
        <p class="example"><input type="date" name="hiredate">範例:20200101</p>

        <p>薪水:</p>
        <p><input type="text" name="sal" size="8">新台幣</p>
    

    <p class="button"><input type="submit" name="submit" value="送出" style="width:40px;height:30px;"></p>
    </form>
    <p align="center" style="margin:10px 0px 10px 0px">
	<a href="./LookUpServlet">回主畫面</a>
	</p>
</div>
</body>
</html>
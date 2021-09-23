<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.util.List" import="discuss.discussJavaBean" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="testcss2.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
</head>

<body>
    <header>討論區</header>
    <div class="wrapper">
        <div class="content">
            <form action="./discussServlet" method="post">
                <div class="div_left">
                    <a href="memberPage.html">會員專區</a><br><br>
                    <a href="homePage.html">首頁</a><br>
                    <a href="memberPage.html">會員基本資料</a><br>
                    <a href="#">優惠活動</a><br>
                    <a href="#">訂單查詢</a><br>
                </div>
                <%List<discussJavaBean> discussJavaBeansList = (List<discussJavaBean>)request.getAttribute("alldiscuss"); %>
                <%for(discussJavaBean item:discussJavaBeansList){%>
                <div class="div_mid">
                <span class="takediscuss_span">店家名稱:<%= item.getShopName()%></span><br>
                <span class="takediscuss_span">飲料名:<%= item.getDrink()%></span><br>
                <span class="takediscuss_span">評論:<%=item.getDrinkDiscuss() %></span><br>
                <span class="takediscuss_span"><%=item.getDrinkstars()%></span><br>
                
                </div>
                <%
                }%>
                
                
                <div>
                </div>
                <%-- 
                <div class="div_right">
                 RANK
                	<div class="div_right_top">
                    
                	</div>
                <hr>
                	<div class="div_right_bottom">
                    123
                	</div>
                </div> 
                --%>

        </div>
        </form>
    </div>
    </div>
    <div class="copyRight">&copy 2021/07/09，第三組</div>
    <div class="footdiv animate__animated animate__bounce animate__delay-2s">kuzi </div>
    <select>
    
    </select>
</body>

</html>
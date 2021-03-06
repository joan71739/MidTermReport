<%@page import="JavaBeen.orderBeen"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="JavaBeen.ShoppingCarBeen"%>
<%@page import="JavaBeen.prouctBeen"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>enterprise page</title>
<style>
* {
	padding: 0;
	margin: 0;
	list-style: none;
}

header {
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 10;
}

header .header_top {
	position: relative;
	height: 80px;
	background-color: rgb(31, 131, 31);
	z-index: 10;
	box-shadow: 0px 10px 20px rgba(0, 0, 0, .2);
}

header .selectButton {
	height: 60px;
	width: 60px;
	padding: 10px;
	margin-left: 20px;
	float: left;
	cursor: pointer;
}

header .selectButton img {
	float: left;
	height: 100%;
}

header .selectButton img:hover {
	-webkit-filter: invert(1);
}

header .headerButton {
	display: block;
	height: 30px;
	margin: 25px 40px 25px 0;
	border: none;
	font-size: 30px;
	line-height: 30px;
	color: #fff;
	transition: color .3s;
	float: right;
	cursor: pointer;
	text-decoration: none;
}

header .signUp {
	border-left: 3px solid #fff;
	padding-left: 40px;
}

header .headerButton:hover {
	color: rgb(255, 255, 64);
}

header .headerButton.shopCar {
	background-color: rgb(151, 255, 151);
	border-radius: 3px;
	height: 26px;
	margin: 15px 40px 15px 0;
	font-size: 25px;
	line-height: 25px;
	padding: 12px 18px;
	color: rgb(23, 102, 23);
}

header .headerButton.shopCar:hover {
	background-color: rgba(151, 255, 151, 0.863);
}

header .header_select {
	position: absolute;
	width: 100%;
	top: 80px;
	text-align: center;
	font-size: 30px;
	z-index: 9;
	top: -250px;
	transition: top 1s;
	box-shadow: 5px 5px 10px rgba(0, 0, 0, .4);
}

header .header_select.show {
	top: 80px;
}

header .header_select li {
	border-bottom: 1px solid rgb(31, 131, 31);
	background-color: rgb(175, 255, 175);
}

header .header_select li a {
	display: block;
	padding: 20px 0;
	width: 100%;
	height: 100%;
	text-decoration: none;
}

header .header_select li a:hover {
	background-color: rgb(124, 250, 124);
}

.container {
	height: auto;
	margin-top: 50px;
	padding: 50px 0;
	width: 100%;
	background-color: rgb(248, 255, 182);
}

.container>h1 {
	font-size: 3em;
	text-align: center;
}

.selectdate {
	margin: 0 33%;
	margin-top: 10px;
	margin-bottom: 10px;
}

.order {
	border: 2px solid blue;
	border-radius: 20px;
	width: 800px;
	margin: 1% 20%;
}

.order>li {
	border: 1px solid black;
	border-radius: 20px;
	margin: 10px;
	width: 250px;
	padding: 5px;
}

.footer {
	background-color: rgb(3, 26, 102);
}

.footer .copyRight {
	color: white;
	text-align: center;
	padding: 20px 0;
}

.clearFloat {
	clear: both
}
</style>
</head>

<body>
	<header>
		<div class="header_top">
			<div class="selectButton" id="selectButton">
				<img src="images/list.png">
			</div>
			<a class="headerButton signUp" href="#">??????</a> <a
				class="headerButton" href="#">??????</a> <a class="headerButton shopCar"
				href="#">?????????</a>
			<div class="clearFloat"></div>
		</div>
		<ul class="header_select" id="header_select">
			<li><a href="homePage.html">??????</a></li>
			<li><a href="memberPage.html">????????????</a></li>
			<li><a href="enterprise.html">????????????</a></li>
			<li><a href="store.html">????????????</a></li>
		</ul>
	</header>
	<div class="container">
		<h1>????????????</h1>
		<hr>
		<form name="selectdate" action="OrderControllerServlet"
			class="selectdate">
			<input type="hidden" name="todo" value="add"> <input
				type="hidden" name="begin" id="begin" value=""> <input
				type="hidden" name="end" id="end" value=""> ?????????<input
				type="date" id="begindata"> ?????????<input type="date"
				id="enddata"> <input type="submit" value="??????">
		</form>

		<%
			orderBeen ord = (orderBeen) request.getAttribute("order");
		if (ord != null && ord.size() > 0) {
			for (int i = 0; i < ord.size(); i++) {
		%>

		<ul class="order">
			<li>????????????:<%=ord.getOrdID(i)%></li>
			<li>??????:</li>
			<li>??????:<%=ord.getShopDate(i)%></li>
			<li>???????????????:<%=ord.getTotal(i)%></li>
		</ul>

		<%
			}
		}
		%>

	</div>
	<div class="footer">
		<div class="copyRight">&copy 2021/07/09????????????</div>
	</div>

    <script src="library/jquery-3.6.0.js"></script>
	<script>
		$(function() {
			$('#selectButton').on('click', function() {
				$('#header_select').toggleClass('show');
			})

		})

		document.getElementById("begindata").addEventListener('change',
				function() {
					console.log(document.getElementById("begin").value);
					let a = document.getElementById("begindata").value
					document.getElementById("begin").value = a;
					console.log(document.getElementById("begin").value);
				})
		document.getElementById("enddata").addEventListener('change',
				function() {
					console.log(document.getElementById("end").value);
					let a = document.getElementById("enddata").value
					document.getElementById("end").value = a;
					console.log(document.getElementById("end").value);
				})
	</script>
</body>

</html>
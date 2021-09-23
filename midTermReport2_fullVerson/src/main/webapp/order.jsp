<%@page import="java.util.List"%>
<%@page import="JavaBeen.ShoppingCarBeen"%>
<%@page import="JavaBeen.prouctBeen"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
	height: 100vh;
	margin-top: 50px;
	padding: 50px 0;
	width: 100%;
	background-color: rgb(248, 255, 182);
}

.container>h1 {
	font-size: 3em;
	text-align: center;
}

#add {
	text-align: center;
	font-size: 1.5em;
}

.prouct {
	border: 3px solid blue;
	border-radius: 20px;
	text-align: center;
	width: 400px;
	margin: 10px auto
}

.remove {
	float: right;
	margin: 0px 10px;
}

.prouctword {
	display: block;
	position: relative;
	top: 0;
	left: 0px;
	size: 10px;
}

.iceandsugar {
	display: block;
	position: relative;
	top: 0;
	left: 0px;
	size: 10px;
}

.priceword {
	display: inline;
	color: red;
}

.checkout {
	text-align: center;
	/* width: 400px; */
	margin: 10px auto
}

.totalword {
	display: inline;
	color: red;
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
			<a class="headerButton signUp" href="#">註冊</a> <a
				class="headerButton" href="#">登入</a> <a class="headerButton shopCar"
				href="#">購物車</a>
			<div class="clearFloat"></div>
		</div>
		<ul class="header_select" id="header_select">
			<li><a href="homePage.html">首頁</a></li>
			<li><a href="memberPage.html">會員帳號</a></li>
			<li><a href="enterprise.html">企業帳號</a></li>
			<li><a href="store.html">店家帳號</a></li>
		</ul>
	</header>
	<div class="container">
		<h1>購物車</h1>
		<hr>
		<form name="AddForm" action="shopping" method="post" id="add">
			<input type="hidden" name="todo" value="add"> <input
				type="hidden" name="newqty" value="1" id="newqty"> <label
				for="prouct">選擇商品:</label> <select name="prouct" id="prouct">
				<%
					for (int i = 0; i < prouctBeen.size(); i++) {
					out.print("<option value='" + i + "'>");
					out.print(prouctBeen.getProName(i) + "---" + prouctBeen.getProPrice(i));
					out.print("</option>");
				}
				%>

			</select> <label for="ice">冰塊:</label> <select name="ice" id="ice">
				<option value="全冰">全冰</option>
				<option value="少冰">少冰</option>
				<option value=" 去冰">去冰</option>
			</select> <label for="sugar">甜度:</label><select name="sugar" id="sugar">
				<option value="全糖">全糖</option>
				<option value="少糖">少糖</option>
				<option value="半糖">半糖</option>
				<option value="微糖">微糖</option>
				<option value="無糖r">無糖</option>
			</select> <label for="qty">數量:</label><input type="text" name="qty" id="qty"
				size="10" value="1"> <input type="submit" value="加入購物車"
				id="sumbit">
		</form>
		<%
			List<ShoppingCarBeen> car = (List<ShoppingCarBeen>) session.getAttribute("car");
		if (car != null && car.size() > 0) {
			for (int i = 0; i < car.size(); i++) {
				ShoppingCarBeen caritem = car.get(i);
		%>
		<div class="prouct">
			<form name="removeForm" action="shopping" method="post">
				<input type="hidden" name="todo" value="remove"> <input
					type="hidden" name="cartIndex" value="<%=i%>">
				<!--   <img src="#" alt=""> -->
				<label class="prouctword"><%=caritem.getProName()%></label> <label
					class="iceandsugar"><%=caritem.getSugar()%> <%=caritem.getIce()%>
				</label> <label>單價:</label>
				<h6 class="priceword" id="priceword">
					NT$<%=caritem.getPrice()%></h6>
				<label class="qtyword" >數量: </label> <input type='number' name='qty'
					value='<%=caritem.getQty()%>' class='qty' min="1" size="2"
					id='proqty' /> <input type="submit" class="remove" value="X">
			</form>
		</div>

		<%
			}
		int total = 0;
		for (int i = 0; i < car.size(); i++) {
		ShoppingCarBeen caritem = car.get(i);
		total += caritem.getPrice() * caritem.getQty();
		}
		%>

		<div class="checkout">
			<form name="checkoutForm" action="shopping" method="POST">
				<input type="hidden" name="todo" value="checkout">
				<h5 class="totalword">
					<em id="total">小計NT$<%=total%></em>
				</h5>
				<input type="submit" value="結帳">
			</form>
		</div>
		<%
			}
		%>
	</div>
	<div class="footer">
		<div class="copyRight">&copy 2021/07/09，第三組</div>
	</div>

	<script src="library/jquery-3.6.0.js"></script>
	<script>
		$(function() {
			$('#selectButton').on('click', function() {
				$('#header_select').toggleClass('show');
			})

		})


	</script>
</body>

</html>
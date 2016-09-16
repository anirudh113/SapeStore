<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page
	import="java.util.*,java.io.*,com.sapestore.hibernate.entity.BookCategory,com.sapestore.controller.ProductController"%>

<!DOCTYPE html>
<html class="no-js">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@ include file="../header.jsp"%>
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/menu.css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script type="text/javascript">

function validateInput(){
	var x = document.getElementById("orderId").value;
	var pattern = /[0-9]/; 
	var alpha = /[a-z]/; 
	if(x.length<=0)
	{
		  	document.getElementById("empty").innerHTML = "Please enter an orderID";		
		
	}

	else if(!(pattern.test(x)) || alpha.test(x)){

		document.getElementById("empty").innerHTML = "Incorrect format";

		}
	 else
	{
			 //document.getElementById("empty").innerHTML = "";
			 document.orderTrack.submit();
	}  
}


</script>

<style type="text/css">

.status{
color : red;
}

</style>

</head>

<body>

<c:choose>
<c:when test="${sessionScope.username == null}">
<a href="index.jsp"><h2>Access Denied. Click Here to Login</h2></a> 
</c:when>
<c:when test="${sessionScope.username != null}">



<!-- 	<script src="js/addBooks.js"></script> -->
	<div id="wrapper">
		<!-- header starts-->
		<header>
			<div id="header"
				style="width: 100%; margin: 0; padding: 0; background-color: black; top: 0; left: 0;">
				<a href="#" title="SapeStore" class="logo"><img
					src="img/logo.jpg" height="109" alt="SapeStore"></a>
			<!-- 	<ul class="topLinks hide">
					<li><input name="include_books" type="checkbox"
						value="include_books" checked> <a
						title="Add books from Partner Store" href="#">Include books
							from Partner Store</a></li>
					<li><a class='inline' href="#shoppingCart"><img
							src="img/icon_cart.jpg" width="15" height="12" alt="cart">
					</a></li>
					<li class="cartNum">0</li>
				</ul> -->
				<!-- in case of admin hide this and display another one -->
				<ul class="topLinks">
					<li>
					<c:choose>
					<c:when test="${not empty userId}">
						Welcome ${username}
					</c:when>
					</c:choose>
					</li>
				</ul>
				<nav>
					<ul class="nav">
						<li class="active"><a href="/SapeStore/manageInventory">Manage
								Inventory</a></li>

						<li><a href="/SapeStore/manageOrders">Manage Orders</a></li>

						<li><a href="/SapeStore/adminReport">Manage Reports</a></li>

						<li><a href="/SapeStore/managePages">Manage Pages</a></li>

						<li><a href="/SapeStore/logout">Logout</a></li>
					</ul>
				</nav>
			</div>

		</header>

		<div
			style="min-height: 620px; padding-left: 20px; padding-right: 20px">

			<h2 style="border-bottom: dashed; padding: 20px">Order Status
				Tracking</h2>
			<div class="clearfix"></div>
			<form:form name="orderTrack" action="/SapeStore/updatePayment"
				method="POST">
				<table style="padding-left: 50px">
					<tr></tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td></td>
						<td><label style="font-size: medium;">Order Number</label>
						<output style="color: red;">*</output></td>
					<tr>
						<td></td>
						<td><input type="text" id="orderId" name="orderId"
							placeholder="OrderId" /></td>
						<td><input type="button" value="Update Status"
							style="height: 25px; font-weight: bold; font-size: large;; width: 150px; background-color: #21addd; color: white; height: 30px"
							onclick="validateInput()"></td>
					</tr>
					<tr>
					<td>
					</td>
						<td><c:choose>
										<c:when test="${canupdate==true && dispatch==true}">
										<div id="empty" class="status"></div>
										</c:when>

										<c:when test="${canupdate==false && dispatch==true} ">
											<div class="status" id="empty">No order for this Order Id</div>
										</c:when>
										<c:when test="${canupdate ==false &&  dispatch==false}">
										<div class="status" id="empty">Some items of this order are yet to be dispatched</div>
										</c:when>
										<c:otherwise>
											<div class="status" id="empty">No order for this Order Id</div>
										</c:otherwise>
									</c:choose></td>
					</tr>

				</table>
			</form:form>
		</div>
	</div>
	<footer>
		<div id="footer">
			<div>
				Powered by <img src="img/sapient_nitro.jpg" width="78" height="14"
					alt="sapient nitro">
			</div>
		</div>
	</footer>

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')
	</script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>

</c:when>
</c:choose>
<%@ include file="../footer.jsp"%>
</body>
</html>
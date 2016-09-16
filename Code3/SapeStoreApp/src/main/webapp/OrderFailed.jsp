<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@ include file="../header.jsp"%>
<title>OrderConfirmationPage</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<style type="text/css">
h1 {
	color: black;
	font-size: 22px;
	cursor: pointer;
}

h2 {
	color: black;
	font-size: 18px;
	cursor: pointer;
}

p {
	color: black;
	font-size: 14px;
	cursor: pointer;
}
</style>


</head>
<body>
	<script>
		function changeVal() {
			if (document.getElementById('account').value == "3") {
				window.location.href = "transactionHistory";
			} else if (document.getElementById('account').value == '2') {
				window.location.href = "orderTracking.jsp";
			} else if (document.getElementById('account').value == '1') {
				window.location.href = "editInfo";
			} else if (document.getElementById('account').value == '4') {
				window.location.href = "clientanalytics";
			} else if (document.getElementById('account').value == '5') {
				window.location.href = "unauthUserProfile";
			}
		}
	</script>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<!-- Add your site or application content here -->
	<div id="wrapper">
		<!-- header starts-->
		<header>
			<div id="header">
				<a href="sapestore.com" title="SapeStore" class="logo"><img
					src="img/logo.jpg" width="231" height="109" alt="SapeStore"></a>
				<ul class="topLinks">
					<li><input name="include_books" type="checkbox"
						value="include_books" checked> <a
						title="Add books from Partner Store" href="javacript:void(0)"
						class="partnerStore">Include books from Partner Store</a></li>
					<li><a id="header_shoppingCart" class='inline'
						href="#shoppingCart"><img src="img/icon_cart.jpg" width="15"
							height="12" alt="cart"></a></li>
					<li class="cartNum">${ShoppingCart.totalQuantity}</li>
					<c:choose>
						<c:when test="${not empty userId}">
							<select id="account" name="account" style="font-size: 12px;"
								onchange="changeVal()">
								<option value="-1" style="font-size: 10px;">Welcome
									${username}</option>
								<option value="1" style="font-size: 10px;">Personal
									Information</option>
								<option value="2" style="font-size: 10px;">Order Status</option>
								<option value="3" style="font-size: 10px;">Transaction
									History</option>
								<option value="4" style="font-size: 10px;">Analytics</option>
							</select>
						</c:when>
						<c:otherwise>
							<select id="account" name="account" style="font-size: 12px;"
								onchange="changeVal()">
								<option value="-1" style="font-size: 10px;">Welcome
									${username}</option>
								<option value="5" style="font-size: 10px;">Personal
									Information</option>
								<option value="5" style="font-size: 10px;">Order Status</option>
								<option value="5" style="font-size: 10px;">Transaction
									History</option>
								<option value="4" style="font-size: 10px;">Analytics</option>
							</select>

						</c:otherwise>

					</c:choose>
				</ul>
				<nav>
					<ul class="nav">
						<li class="active"><a id="header_home" href="index.jsp"
							title="Home">Home</a></li>

						<c:choose>
							<c:when test="${not empty userId}">
								<li><a id="header_account" href="/SapeStore/editInfo">Client360</a></li>
							</c:when>
							<c:otherwise>
								<li><a id="header_account"
									href="/SapeStore/clientanalytics">Client360</a></li>
							</c:otherwise>

						</c:choose>

						<li><a id="header_wishList" href="/SapeStore/displayWishList">Wishlist</a></li>
						<li><a id="header_logout" href="/SapeStore/logout"
							class='inline' title="Login">Logout</a></li>
						<li><a id="header_search" href="/SapeStore/getBookSearchForm"><img
								alt="searchImage" src="img/magnifier-icon.png"></a></li>
					</ul>
				</nav>
			</div>
		</header>
		<!-- header ends -->
		<section>
			<div class="leftCol">
				<h2>Client360</h2>
				<nav>
					<!-- left navigation -->
					<ul>
						<li><a id="info_account" href="/SapeStore/editInfo">Personal
								Information</a></li>
						<li><a id="transaction_history"
							href="/SapeStore/transactionHistory">Transaction History</a></li>
						<li><a id="order_tracking" href="orderTracking.jsp">Order
								Status</a></li>
						<li><a id="analytics" href="/SapeStore/clientanalytics">Analytics
						</a></li>

					</ul>
				</nav>
			</div>
			<div id="mainContent">

				<h1>Order Failed</h1>
				<br>
				<hr style="border-bottom: dashed 1px #b7bcbd" />

				<p>We are sorry the requested quantity for the below book is not
					available</p>
				<br>
				<p>${bookvo.bookTitle}</p>
				<br>
				<p>Try changing the quantity or try again once it comes back
					into stock</p>
				<br>
				<form action="clearShoppingCart">
					<input type="submit" value="Shop More">
				</form>
				<div class="clearfix"></div>
		</section>
		<div class="clearfix"></div>
		<footer>
			<div id="footer">
				Powered by <img src="img/sapient_nitro.jpg" width="78" height="14"
					alt="sapient nitro">
			</div>
		</footer>
	</div>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')
	</script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
	<%@ include file="../footer.jsp"%>
</body>
</html>


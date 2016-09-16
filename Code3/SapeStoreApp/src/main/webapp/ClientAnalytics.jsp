<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-i e9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@ include file="../header.jsp"%>
<title>SapeStore-ClientAnalytics</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="css/normalize.css" type="text/css">
<link rel="stylesheet" href="css/main.css" type="text/css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')
</script>
</head>

<body>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("button").click(function(){
			var jsId = getJSessionId();
			setTimeout(function() {
				Ajaxcallad(jsId);
				Ajaxcallrecomm(jsId);
			}, 3000);
			// 	ajaxtimeout = setTimeout( Ajaxcallrecomm(jsId), 100000);
			//clearTimeout(ajaxtimeout);
			// Ajaxcallad(jsId);
			//Ajaxcallrecomm(jsId);
			//});
		});
		/* function Ajaxcall(jsId){
		 url= "/SapeStore/getAdBook" ;
		 $.ajax({
		 type : "GET",
		 url : url,
		 data : {
		 dsessionId : "abc"
		 },
		 //contentType: "application/json",
		 dataType : "json",
		 success : function(data) {
		 alert(data.bookTitle);
		 //document.getElementById("ajaxResponse").style.display="block";
		 //document.getElementById("ajaxResponse").innerHTML=data.bookTitle;
		 var element = document.getElementById("ajaxResponse");
		 var pagerHtml= gethtml(data);
		 element.innerHTML=pagerHtml;
		
		 }
		 });
		 //window.location.reload();
		
		 // 	$.ajax({
		 // 		 type: "GET",
		 // 	     url:"/SapeStore/recommendnew",
		 // 		success:function(data) {
		 // 			alert(data);
		 // 	}
		
		 // });

		 } */

		function Ajaxcallad(jsId) {
			url = "/SapeStore/getAdvBook";
			$.ajax({
				type : "GET",
				url : url,
				data : {
					dsessionId : "abc"
				},
				success : function(response) {
					$("#ajaxResponseAd").html(response);
				}
			});

		}

		function Ajaxcallrecomm(jsId) {
			url = "/SapeStore/getRecommendBooks";
			$.ajax({
				type : "GET",
				url : url,
				data : {
					dsessionId : "abc"
				},
				success : function(response) {
					$("#ajaxResponseRecomm").html(response);
				}
			});

		}

		function getJSessionId() {
			var jsId = document.cookie.match(/JSESSIONID=[^;]+/);
			if (jsId != null) {
				if (jsId instanceof Array)
					jsId = jsId[0].substring(11);
				else
					jsId = jsId.substring(11);
			}
			return jsId;
		}

		/* function gethtml(book){
		
		 var pagerHtml = '<span id="Adbook" style="font-weight: bold;">' + book.bookTitle + '</span> ';
		 pagerHtml += '<img src="'+book.bookFullImage+'" width="131" height="180" />';
		 //console.log(book);
		 return pagerHtml;
		
		 } */
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginPop").click();
		});
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
		<!-- 		<div style="display: none"> -->
		<!-- 			<div id="shoppingCart" class="popup"> -->
		<%-- 				<jsp:include page="DisplayShoppingCart.jsp" flush="true" /> --%>
		<!-- 			</div> -->
		<!-- 		</div> -->
		<form:form name="form" action="bookListByCat" method="post">
			<!-- header starts-->
			<header>
				<div id="header">
					<a href="/SapeStore/welcome?checkMe=${checkMe}" title="SapeStore"
						class="logo"><img src="img/logo_option 01.png" width="231"
						height="109" alt="SapeStore"></a>

					<ul class="topLinks">
						<li><c:choose>
								<c:when test="${checkMe==false}">
									<a href="togglecheckMe"><img src="img/unchecked_ps.png"></a>
									<!-- <input type="checkbox" name="checkMe" id="checkMe" style="font-size: 13px;"
									onclick="toggleCheckMe()"> -->

								</c:when>
								<c:otherwise>
									<a href="togglecheckMe"><img src="img/checked_ps.png"></a>
									<!-- <input type="checkbox" name="checkMe" id="checkMe" style="font-size: 13px;"
									onclick="toggleCheckMe()" checked="checked"> -->
								</c:otherwise>
							</c:choose> <input type="hidden" name="categoryId" value="${categoryId}" />
							<input type="hidden" name="categoryName" value="${categoryName}" />
						</li>
						<!-- 						<li><a id="header_shoppingCart" class='inline' -->
						<!-- 							href="#shoppingCart"><img src="img/icon_cart.jpg" width="15" -->
						<!-- 								height="12" alt="cart"></a></li> -->
						<li><a id="header_shoppingCart"
							href="/SapeStore/shoppingCart"><img src="img/icon_cart.jpg"
								width="15" height="12" alt="cart"></a></li>
						<li class="cartNum">${ShoppingCart.totalQuantity}</li>
						<c:choose>
							<c:when test="${not empty userId}">
								<select id="account" name="account" style="font-size: 12px;"
									onchange="changeVal()">
									<option value="-1" style="font-size: 10px;">Welcome
										${username}</option>
									<option value="1" style="font-size: 10px;">Personal
										Information</option>
									<option value="2" style="font-size: 10px;">Order
										Status</option>
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
									<option value="5" style="font-size: 10px;">Edit
										Profile</option>
									<option value="5" style="font-size: 10px;">Order
										Status Tracking</option>
									<option value="5" style="font-size: 10px;">Transaction
										History</option>
									<option value="4" style="font-size: 10px;">Analytics</option>
								</select>
							</c:otherwise>
						</c:choose>
					</ul>
					<nav>
						<ul class="nav">
							<li class="active"><a id="header_home"
								href="/SapeStore/welcome?checkMe=${checkMe}">home</a></li>
							<c:choose>
								<c:when test="${not empty userId}">
									<li class="active"><a id="header_account"
										href="/SapeStore/editInfo">Client360</a></li>
								</c:when>
								<c:otherwise>
									<li class="active"><a id="header_account"
										href="/SapeStore/clientanalytics">Client360</a></li>
								</c:otherwise>
							</c:choose>

							<li><a id="header_wishList"
								href="/SapeStore/welcome?checkMe=${checkMe}">wishlist</a></li>
							<jsp:include page="Logout.jsp" flush="true" />
							<li><a id="header_search" href="getBookSearchForm"><img
									alt="searchImage" src="img/magnifier-icon.png"></a></li>
						</ul>
					</nav>
				</div>
			</header>
			<!-- header ends -->
			<section>
				<div class="leftCol" style="height: 1000px">
					<h2>Client360</h2>
					<nav>
						<!-- left navigation -->
						<c:choose>
							<c:when test="${not empty userId}">

								<ul>
									<li><a id="info_account" href="/SapeStore/editInfo">Personal
											Information</a></li>
									<li><a id="transaction_history"
										href="/SapeStore/transactionHistory">Transaction History</a></li>
									<li><a id="order_tracking" href="orderTracking.jsp">Order
											Status</a></li>
									<li style="font-weight: bold;"><a id="analytics"
										href="/SapeStore/clientanalytics">Analytics </a></li>

								</ul>
							</c:when>
							<c:otherwise>
								<ul>
									<li><a id="info_account"
										href="/SapeStore/unauthUserProfile">Personal Information</a></li>
									<li><a id="transaction_history"
										href="/SapeStore/unauthUserProfile">Transaction History</a></li>
									<li><a id="order_tracking"
										href="/SapeStore/unauthUserProfile">Order Status</a></li>
									<li style="font-weight: bold;"><a id="analytics"
										href="/SapeStore/clientanalytics">Analytics </a></li>

								</ul>
							</c:otherwise>
						</c:choose>

					</nav>
				</div>
			</section>
		</form:form>
		<section style="margin-left: 262px; margin-top: 40px;">
			<h1 style="margin-top: -5px;">Welcome to Client360 Analytics</h1>
			<div id="analyticsDiv"
				style="margin-left: 2px; margin-top: 25; margin-right: 20px">

				<table border="1" cellpadding="5" width="500px">
					<tr>
						<td style="font-weight: bold;">JsessionId</td>
						<td>${jsessionid}</td>

					</tr>

					<tr>
						<td style="font-weight: bold;">Browsing Time (sec)</td>
						<td>${duration}</td>
					</tr>
					<tr>
						<td style="font-weight: bold;">clientId</td>
						<td>${clientId}</td>

					</tr>
					<tr>
						<td style="font-weight: bold;">Browser Fingerprint</td>
						<td><div id="fingerprint"></div> <script>
							document.getElementById("fingerprint").innerHTML = new Fingerprint(
									{
										screen_resolution : true
									}).get();
						</script></td>
					</tr>
				</table>
				<!-- 				<button>Get External Content</button> -->
				<div id="ajaxResponseAd" style="min-height: 390px;"></div>
				<div id="ajaxResponseRecomm" style="min-height: 390px;"></div>
			</div>

		</section>
		<div class="clearfix"></div>
		<footer>
			<div id="footer">
				<div style="float: left; margin-left: 386px;">
					<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact
						Us</a>
				</div>
				<div style="float: left; margin-left: 6px; color: #21addd">|</div>

				<div style="float: left; margin-left: 7px;">
					<a href="/SapeStore/policyCustomer" style="color: #21addd;">Privacy
						Policy</a>
				</div>
				<div>
					Powered by <img src="img/sapient_nitro.jpg" width="78" height="14"
						alt="sapient nitro">
				</div>
			</div>
		</footer>
	</div>

	<!-- This contains the hidden content for shopping cart popup -->

	<!-- This contains the hidden content for login popup -->
	<div style="display: none">
		<div id="login" class="popup">
			<jsp:include page="login.jsp" flush="true" />
		</div>
	</div>

	<!-- Forgot password-->
	<div style="display: none">
		<form method="post" action="" id="forgotPassword"
			onsubmit="return ValidateForm();">
			<fieldset>
				<label for="email">Enter your email id<span class="required">*</span></label>
				<input type="email" placeholder="Email Id." required="" name="name">
				<input type="submit" value="Submit" class="lightButton">
			</fieldset>
		</form>
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
	<script>
		$(document)
				.ready(
						function() {
							$(".inline").colorbox({
								inline : true,
								width : "auto",
								height : "auto"
							});
							$(".callbacks")
									.colorbox(
											{
												onOpen : function() {
													alert('onOpen: colorbox is about to open');
												},
												onLoad : function() {
													alert('onLoad: colorbox has started to load the targeted content');
												},
												onComplete : function() {
													alert('onComplete: colorbox has displayed the loaded content');
												},
												onCleanup : function() {
													alert('onCleanup: colorbox has begun the close process');
												},
												onClosed : function() {
													alert('onClosed: colorbox has completely closed');
												}
											});
						});
	</script>
	<%@ include file="../footer.jsp"%>
</body>
</html>
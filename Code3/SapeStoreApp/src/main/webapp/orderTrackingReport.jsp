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
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="css/normalize.css" type="text/css">
<link rel="stylesheet" href="css/main.css" type="text/css">

<script type="text/javascript">
function validateInput(){
	var x = document.getElementById("orderId").value;  
	if(x.length<=0)
	{
		document.getElementById("ptype").style.display="none"; 
  		document.getElementById("ptype1").style.display="block";
		
	}
	 else
	{
			 document.orderTrack.submit();
	}  
}

function Pager(tableName, itemsPerPage) {

this.tableName = tableName;

this.itemsPerPage = itemsPerPage;

this.currentPage = 1;

this.pages = 0;

this.inited = false;

this.showRecords = function(from, to) {

var rows = document.getElementById(tableName).rows;

// i starts from 1 to skip table header row

for (var i = 1; i < rows.length; i++) {

if (i < from || i > to)

rows[i].style.display = 'none';

else

rows[i].style.display = '';

}

}

this.showPage = function(pageNumber) {

if (! this.inited) {

alert("not inited");

return;

}

var oldPageAnchor = document.getElementById('pg'+this.currentPage);

oldPageAnchor.className = 'pg-normal';

this.currentPage = pageNumber;

var newPageAnchor = document.getElementById('pg'+this.currentPage);

newPageAnchor.className = 'pg-selected';

var from = (pageNumber - 1) * itemsPerPage + 1;

var to = from + itemsPerPage - 1;

this.showRecords(from, to);

}

this.prev = function() {

if (this.currentPage > 1)

this.showPage(this.currentPage - 1);

}

this.next = function() {

if (this.currentPage < this.pages) {

this.showPage(this.currentPage + 1);

}

}

this.init = function() {

var rows = document.getElementById(tableName).rows;

var records = (rows.length - 1);

this.pages = Math.ceil(records / itemsPerPage);

this.inited = true;

}

this.showPageNav = function(pagerName, positionId) {

if (! this.inited) {

alert("not inited");

return;

}

if(this.pages>1){
var element = document.getElementById(positionId);

var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> Ã‚Â« Prev </span> ';

for (var page = 1; page <= this.pages; page++)

pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';

pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next Ã‚Â»</span>';

element.innerHTML = pagerHtml;
}

}

}

</script>
<style type="text/css">
#pageNavPosition {
	float: right;
	background: #f0f7f8;
	border-right: 1px solid #AAAAAA;
	border-left: 1px solid #AAAAAA;
	border-bottom: 1px solid #AAAAAA;
	padding-left: 774px;
	margin-right: 15px;
	padding-bottom: 0.5em;
	padding-top: 0.5em;
	padding-right: 2px;
}

.pg-normal {
	color: #de2728;
	font-size: 14px;
	cursor: pointer;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

.pg-selected {
	color: #fff;
	font-size: 14px;
	background: #de2728;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

table.yui {
	border-collapse: collapse;
	font-size: small;
}

table.yui td {
	padding: 5px;
}

table.yui .even {
	background-color: #EEE8AC;
}

table.yui .odd {
	background-color: #F9FAD0;
}

table.yui th {
	padding-top: 13px;
	height: auto;
}

table.yui th a {
	text-decoration: none;
	text-align: center;
	padding-right: 20px;
	font-weight: bold;
	white-space: nowrap;
}

table.yui tfoot td {
	background-color: #E1ECF9;
}

table.yui thead td {
	vertical-align: middle;
	background-color: #E1ECF9;
	border: none;
}

table.yui thead .tableHeader {
	font-size: larger;
	font-weight: bold;
}

table.yui thead .filter {
	text-align: right;
}

table.yui tfoot {
	background-color: #E1ECF9;
	text-align: center;
}

table.yui .tablesorterPager {
	padding: 10px 0 10px 0;
}

table.yui .tablesorterPager span {
	padding: 0 5px 0 5px;
}

table.yui .tablesorterPager input.prev {
	width: auto;
	margin-right: 10px;
}

table.yui .tablesorterPager input.next {
	width: auto;
	margin-left: 10px;
}

table.yui .pagedisplay {
	font-size: 10pt;
	width: 30px;
	border: 0px;
	background-color: #E1ECF9;
	text-align: center;
	vertical-align: top;
}
</style>

<script type="text/javascript">
		var pager = new Pager('tablepaging', 10);
		pager.init();
		pager.showPageNav('pager', 'pageNavPosition1');
		pager.showPage(1);
	</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
		window.jQuery
				|| document
						.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')
	</script>
<script src="js/plugins.js"></script>
<script src="js/main.js"></script>
</head>
<body>
	<script>
function changeVal()
{
	if(document.getElementById('account').value == "3"){
		window.location.href = "transactionHistory";
	}
	else if(document.getElementById('account').value == '2')
		{
		window.location.href = "orderTracking.jsp";
		}
	else if(document.getElementById('account').value == '1')
	{
	window.location.href = "editInfo";
	}
	else if(document.getElementById('account').value == '4')
	{
	window.location.href = "clientanalytics";
	}
	else if(document.getElementById('account').value == '5')
	{
	window.location.href = "unauthUserProfile";
	}
}

</script>

	<div id="wrapper">
		<div style="display: none">
			<div id="shoppingCart" class="popup">
				<jsp:include page="DisplayShoppingCart.jsp" flush="true" />
			</div>
		</div>
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
									<a id="toggle_checkMe" href="togglecheckMe"><img
										src="img/unchecked_ps.png"></a>
									<!-- <input type="checkbox" name="checkMe" id="checkMe" style="font-size: 13px;"
									onclick="toggleCheckMe()"> -->

								</c:when>
								<c:otherwise>
									<a id="toggle_checkMe" href="togglecheckMe"><img
										src="img/checked_ps.png"></a>
									<!-- <input type="checkbox" name="checkMe" id="checkMe" style="font-size: 13px;"
									onclick="toggleCheckMe()" checked="checked"> -->
								</c:otherwise>
							</c:choose> <input type="hidden" name="categoryId" value="${categoryId}" />
							<input type="hidden" name="categoryName" value="${categoryName}" />
						</li>
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
									<option value="5" style="font-size: 10px;">Personal
										Information</option>
									<option value="5" style="font-size: 10px;">Order
										Status</option>
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
									<li><a id="header_account" href="/SapeStore/editInfo">Client360</a></li>
								</c:when>
								<c:otherwise>
									<li><a id="header_account"
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
			</section>
		</form:form>
		<section style="margin-left: 262px; margin-top: 40px;">
			<h1 style="margin-top: -5px;">Order Status Tracking</h1>
			<div id="orderDiv"
				style="margin-left: 2px; margin-top: 25; margin-right: 20px">


				Order Number<font color="red">*</font>
				<form name="orderTrack" id="orderTrack"
					action="/SapeStore/ordertrack" method="GET">
					<input type="text" name="orderId" id="orderId" required="required" />
					<input type="button" value="Get Status" onclick="validateInput()" />
				</form>
				<p id="ptype1" style="display: none";>Please provide order ID</p>
				<br>

			</div>

			<c:choose>
				<c:when test="${empty orderinfo}">
					<td><p id="ptype" style="display: block";>Incorrect Order
							Id</p></td>
				</c:when>
				<c:otherwise>
					<p id="ptype" style="display: block";>
						Your Order <u><strong>${orderinfo.orderId}</strong></u> for <strong>${orderItemInfo.purchaseType}</strong>
						is <strong>${orderinfo.orderStatus}</strong>.
					</p>

					<section style="margin-left: 0px; margin-top: 20px;">
						<center>
							<h2>Order Status</h2>
						</center>
						<table id="tablepaging" class="yui" pagesize="10">
							<c:choose>
								<c:when test="${empty orderItemList}">
									<td><h3>No Previous Orders found</h3></td>
								</c:when>
								<c:otherwise>
									<thead>
										<tr style="border-bottom: dashed 1px #b7bcbd">
											<th style="text-align: left; margin-left: 0px">ORDER ID</th>
											<th></th>
											<th style="text-align: center">NAME & AUTHOR</th>
											<th style="text-align: center">PURCHASE TYPE</th>
											<th style="text-align: center">DISPATCH DATE</th>
											<th style="text-align: center">ORDER STATUS</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${orderItemList}" var="current"
											varStatus="loop">
											<tr style="border-bottom: dashed 1px #b7bcbd"
												id="index${current.orderItemId}">
												<td style="text-align: center;"><h4>${current.orderId}</h4></td>
												<td style="text-align: center;"><img
													src="${current.bookThumbImage}" width="56" height="78"></td>
												<td style="text-align: center;"><h4>${current.bookTitle}</h4>${current.bookAuthor}</td>
												<td style="text-align: center">${current.purchaseType}</td>
												<td style="text-align: center">${current.dispatchDate}</td>
												<td style="text-align: center">${orderinfo.orderStatus}</td>

											</tr>

										</c:forEach>
									</tbody>
								</c:otherwise>
							</c:choose>
						</table>
						<div id="pageNavPosition1" align="center"></div>
					</section>
				</c:otherwise>
			</c:choose>
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
	<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
	<script>
	$(document).ready(function(){
		$(".inline").colorbox({inline:true, width:"auto", height:"auto"});
		$(".callbacks").colorbox({
			onOpen:function(){ alert('onOpen: colorbox is about to open'); },
			onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
			onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
			onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
			onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
		});
	});


</script>
	<%@ include file="../footer.jsp"%>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../header.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-OrderConfirmation</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="css/normalize.css" type="text/css">
<link rel="stylesheet" href="css/main.css" type="text/css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>

<style type="text/css">
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

.homeAdmin #mainContent table {
	width: 99%;
}

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
</style>
<script type="text/javascript">

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

var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> « Prev </span> ';

for (var page = 1; page <= this.pages; page++)

pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';

pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next »</span>';

element.innerHTML = pagerHtml;
}

}

}

</script>
<script>

function populateCity(stateId) {

	$.ajax({
		type : 'GET',
		url : "/SapeStore/getCityByStateIdcityId?stateId=" + stateId,
		data : ($("select").serialize()),
		success : function(data) {
			$("#populateCity").html(data);
		}
	});

}

function populate(cityId){
	document.getElementById("secretcity").value=cityId;
	}

function stateChange()
{   
    if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
    {   
        document.getElementById("div_id").innerHTML=xmlHttp.responseText; 
    }   
} 

function checkFields(){
	if (parseInt(document.getElementById("stateName").value) > 0){
		document.getElementById("stateN").style.display="none";
	}
	
	if(parseInt(document.getElementById("stateName").value) == 0){
		document.getElementById("stateN").style.display="block";
		return false;			
	}
	else if(document.getElementById("cityname").value == "0"){
		document.getElementById("cityN").style.display="block";
		return false;			
	}
	if (document.getElementById("cityname").value != "0"){
		document.getElementById("cityN").style.display="none";
	}

	var x=document.getElementById('zipCode').value;
	var patt = /[0-9]/;
    
	 if(!(patt.test(x))) {
    	document.getElementById('zipcodeError').style.display="block";
    	return false;
    	}
	var x1=document.getElementById('phoneNo').value;
	var y1=document.getElementById('mobileNo').value;

	if (x1=="" && y1==""){
		document.getElementById('phoneError').style.display="block";
	return false;}
	else{
		if (x1==""){
			return validatemobile();
			}
		else if (y1==""){
			return validatephone();
			
			}
	return true;
	}
}

function validatephone() {
	var patt = /[0-9]{10}/;
    var x = document.getElementById('phoneNo').value;
    
    if(!(patt.test(x))) {
        	document.getElementById('phoneError').style.display="block";
			return false;
    	    }

}	

function validatemobile() {
	var patt = /[0-9]{10}/;
    var x = document.getElementById('mobileNo').value;
    
    if(!(patt.test(x))) {
        	document.getElementById('mobileError').style.display="block";
			return false;
    	    }
}	

function populateCityBeforeChange(){
	var stateId=document.getElementById("stateName").value;
	var cityId=document.getElementById("secretcity").value;
	$.ajax({
		type : 'GET',
		url : "/SapeStore/getIntialCityList?stateId=" + stateId +"&cityId="+cityId, 
		data : ($("select").serialize()),
		success : function(data) {
			$("#populateCity").html(data);
		}
	});
	


	}

$(document).ready(function(){
	$("#dispatch").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	$("#return").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	
});
</script>
<script>
	function beforeDispatch() {
		document.updateForm.submit();
	}
	function beforeReturn() {
		document.updateForm.submit();
	}

	function dispatchClick(control) {
		var cid=control.id;
		var substr="dispatchCheckIndex";
		if(cid.lastIndexOf(substr, 0) == 0)
			{
			var str2=cid.substring(18);
			var str1 = "dispatchTextIndex";
			var textI = str1.concat(str2);
			document.getElementById(textI).childNodes[0].nextSibling.value = document.getElementById(cid).checked;
			}
	}
	
	function returnClick(control) {
		var cid=control.id;
		var substr="returnCheckIndex";
		if(cid.lastIndexOf(substr, 0) == 0)
			{
			var str2=cid.substring(16);
			var str1 = "returnTextIndex";
			var textI = str1.concat(str2);
			document.getElementById(textI).childNodes[0].nextSibling.value = document.getElementById(cid).checked;
			}
	}
</script>
</head>

<body onload="populateCityBeforeChange()">

<script type="text/javascript">
	dataLayer = [];
</script>

	<script type="text/javascript">
$(document).ready(function(){
	$("#loginPop").click();
});
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
		<!-- 		<div id="shoppingCartContainer" style="display: none"> -->
		<!-- 			<div id="shoppingCart" class="popup"> -->
		<%-- 				<jsp:include page="DisplayShoppingCart.jsp" flush="true" /> --%>
		<!-- 			</div> -->
		<!-- 		</div> -->
		<form:form name="form"
			action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe=false"
			method="GET" commandName="welcome">
			<header>
			<div id="header">
				<a href="/SapeStore/welcome?checkMe=${checkMe}" title="SapeStore"
					class="logo"><img src="img/logo_option 01.png" width="231"
					height="109" alt="SapeStore"></a>

				<ul class="topLinks">
					<li>
						<!--form:checkbox id="checkMe" path="checkMe" value="Include books from Partner Store" onchange="form.submit();"/-->
						<c:choose>
							<c:when test="${welcome.checkMe==false}">
								<input type="checkbox" name="checkMe" id="checkMe"
									style="font-size: 13px;" onclick="form.submit();">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="checkMe" id="checkMe"
									style="font-size: 13px;" onclick="form.submit();"
									checked="checked">
							</c:otherwise>
						</c:choose> <label for="checkMe" style="font-size: 13px;">Include
							books from Partner Store</label> <input type="hidden" name="categoryId"
						value="${categoryId}" /> <input type="hidden" name="categoryName"
						value="${categoryName}" />
					</li>
					<%-- <li><a id="header_shoppingCart"
					 class='inline' href="#shoppingCart"><img
							src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
							<li><a id="header_shoppingCart" href="/SapeStore/shoppingCart"><img
							src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
					<li class="cartNum">${ShoppingCart.totalQuantity}</li> --%>
					<c:choose>
						<c:when test="${not empty userId}">
							<select id="account" name="account" style="font-size: 12px;"
								onchange="changeVal()">
								<option value="-1" style="font-size: 10px;">Welcome
									${username}</option>
								<option value="1" style="font-size: 10px;">Edit Profile</option>
								<option value="2" style="font-size: 10px;">Order Status
									Tracking</option>
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
					<li class="active"><a id="header_home"
						href="/SapeStore/welcome?checkMe=${checkMe}" title="Home">Home</a></li>

					<c:choose>
						<c:when test="${not empty userId}">
							<li><a id="header_account" href="/SapeStore/editInfo">Client360</a></li>
						</c:when>
						<c:otherwise>
							<li><a id="header_account" href="/SapeStore/clientanalytics">Client360</a></li>
						</c:otherwise>

					</c:choose>

					<li><a id="header_wishList" href="/SapeStore/displayWishList">Wishlist</a></li>
					<jsp:include page="Logout.jsp" flush="true" />
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
				<nav> <!-- left navigation -->
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
		</form:form>

		<div id="mainContent">

			<h1>Order Confirmation</h1>
			<br>
			<hr style="border-bottom: dashed 1px #b7bcbd" />

			<h2>SHIPPING ADDRESS</h2>
			<br>
			<hr style="border-bottom: dashed 1px #b7bcbd" />


			<p>${userName}</p>
			<p>${user.phone}</p>
			<p>${user.mobileNumber}</p>
			<p>${address.addressLine1}</p>
			<p>${address.addressLine2}</p>
			<p>${city.cityName}</p>
			<p>${state.stateName}</p>
			<p>${address.postalCode}</p>
			<br>

			<h2>ORDER SUMMARY</h2>
			<br>
			<hr style="border-bottom: dashed 1px #b7bcbd" />

			<table cellpadding="10">
				<tr style="border-bottom: dashed 1px #b7bcbd">
					<th style="width: 30%">ISBN</th>
					<th colspan="2">Book Name</th>
					<th style="width: 10%">Quantity</th>
					<th style="width: 10%">Price</th>
					<th style="width: 10%">Sub Total</th>
					<th style="width: 10%">Purchase Type</th>

				</tr>


				<c:forEach items="${orderitems }" var="orderitems">
					<script>
					dataLayer.push({'orderconfirmation' : { 'booktitle' : '${orderitems.bookTitle}',
						'bookprice' : '${orderitems.bookPrice}',
						'type' : '${orderitems.purchaseType}',
						'bookquantity': '${orderitems.orderQuantity}'}});
					</script>

					<tr style="border-bottom: dashed 1px #b7bcbd">
						<td align="center"><c:out value="${orderitems.isbn }  "></c:out></td>
						<td><c:set var="image" value="${orderitems.bookThumbImage} "></c:set>
							<img alt="image" src="${orderitems.bookThumbImage} " width="78"
							height="108"></td>
						<td><c:out value="${orderitems.bookTitle }"></c:out><br>
							<c:out value="${orderitems.bookAuthor }"></c:out><br>
							Publisher: <c:out value="${orderitems.publisherName }"></c:out></td>
						<td align="center"><c:out
								value="${orderitems.orderQuantity } "></c:out></td>
						<td align="center"><c:out value="$${orderitems.bookPrice }"></c:out></td>
						<td align="center"><c:set var="quantity"
								value="${orderitems.orderQuantity}"></c:set> <c:set
								var="bookprice" value="${orderitems.bookPrice}"></c:set> <c:set
								var="subtotal" value="${quantity * bookprice}"></c:set> <c:out
								value="$${subtotal }"></c:out></td>
						<td align="center"><c:out
								value="${orderitems.purchaseType } "></c:out></td>
				</c:forEach>
				</tr>

			</table>
			<br>
			<h4>Total Payment: ${orderlist.totalPayment }</h4>
			<br>
			<p>Thanks for shopping with us.We have received your request.You
				can track your order using the orderid.</p>
			<br>
			<p>A email with the order details has been sent to your
				registered mail id</p>
			<form action="welcome">
				<input type="submit" value="Shop More">
			</form>
			<div class="clearfix"></div>
			</section>
			<div id="pageNavPosition1" align="center"></div>
			<div class="clearfix"></div>

			<footer>

			<div id="footer">
				<div style="float: left; margin-left: 386px;">
					<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact
						Us</a>
				</div>
				<div style="float: left; margin-left: 6px; color: #21addd">|</div>

				<div style="float: left; margin-left: 7px;">
					<a href="/SapeStore//policyCustomer" style="color: #21addd;">Privacy
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


				<%@include file="login.jsp"%>
			</div>
		</div>

		<script type="text/javascript">
		var pager = new Pager('tablepaging', 10);
		pager.init();
		pager.showPageNav('pager', 'pageNavPosition1');
		pager.showPage(1);
	</script>
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
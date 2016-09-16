<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<%@ include file="../header.jsp"%>


<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script src="js/vendor/jquery-1.9.1.min.js"></script>

<style type="text/css">
.pg-normal {
	color: #de2728;
	font-size: 14px;
	cursor: pointer;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

button {
	background-color: BLUE;
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

/* Edit Braavos for h3*/
h3.sapfont {
	font-weight: normal;
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

#pageNavPosition1 {
	float:right;
	 
	background: #dfe5e6;
	border-right: 1px solid #dfe5e6;
	border-left: 1px solid #dfe5e6;
	border-bottom: 1px solid #dfe5e6;
	padding-left: 20px;
	margin-right: 15px;
	padding-bottom: 0.5em;
	padding-top: 0.5em;
	padding-right: 20px;
}
</style>


<script>
$(document).ready(function(){
	$("#Print").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	$("#Back").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	
});
</script>

<!--  Pagination  -->
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



</head>

<body>

<c:choose>
<c:when test="${sessionScope.username == null}">
<a href="index.jsp"><h2>Access Denied. Click Here to Login</h2></a> 
</c:when>
<c:when test="${sessionScope.username != null}">




	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<!-- Add your site or application content here -->
	<div id="wrapper" class="homeAdmin">
		<!-- header starts-->
		<header>
			<div id="header">
				<a href="#" title="SapeStore" class="logo"><img
					src="img/logo.jpg" width="231" height="109" alt="SapeStore">
				</a>
				<ul class="topLinks hide">
					<li><input name="include_books" type="checkbox"
						value="include_books" checked> <a
						title="Add books from Partner Store" href="javacript:void(0)">Include
							books from Partner Store</a></li>
					<li><a class='inline' href="#shoppingCart"><img
							src="img/icon_cart.jpg" width="15" height="12" alt="cart">
					</a></li>
					<li class="cartNum">0</li>
				</ul>
				<!-- in case of admin hide this and display another one -->
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
		<!-- header ends -->
		<section>

			<div id="mainContent">
				<h2>Dispatch Slip</h2>
				<div></div>
				<form:form method="GET" action="/SapeStore/manageOrders">
					<table id="tablepaging" class="yui"
			style="width: 950px; height: 91px; border-bottom-width: 0px">
						<thead>
							<tr>
								<th>ORDER NUMBER</th>
								<th>CUSTOMER NAME</th>
								<th>SHIPPING ADDRESS</th>
							</tr>
						</thead>

						<c:forEach items="${dispatchSlips}" var="current">
							<tbody style="font-size: 12px">
								<tr align="center">

									<td>${current.orderNumber}</td>
									<td>${current.customerName}</td>
									<td>${current.shippingAddress}</td>
								</tr>

							</tbody>
						</c:forEach>
					</table>


					<div
						style="margin-right: 52em; float: right; margin-bottom: 1em; margin-top: 1em;">
						<input type="submit" id="Back" value="Back" name="Back"
							style="height: 25px; font-size: initial" />
					</div>
					<div
						style="margin-left: 0em; float: left; margin-bottom: 1em; margin-top: 1em;">
						<input type="button" id="Print" value="Print" name="Print"
							style="height: 25px; font-size: initial" />
					</div>
<div id="pageNavPosition1" align="center"></div>
			</div>
				</form:form>
		
		</section>
		<div class="clearfix"></div>
		<footer>
			<div id="footer">

				<div>
					Powered by <img src="img/sapient_nitro.jpg" width="78" height="14"
						alt="sapient nitro">
				</div>
			</div>
		</footer>
	</div>

	<script type="text/javascript">
		var pager = new Pager('tablepaging', 10);
		pager.init();
		pager.showPageNav('pager', 'pageNavPosition1');
		pager.showPage(1);
	</script>
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
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
<%@ include file="../header.jsp"%>
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

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

#pageNavPosition {
	float:left;
	 
	background: #f0f7f8;
	border-right: 1px solid #AAAAAA;
	border-left: 1px solid #AAAAAA;
	border-bottom: 1px solid #AAAAAA;
	padding-left: 774px;
	margin-right: 15px;
	padding-bottom: 0.5em;
	padding-top: 0.5em;
	padding-right: 20px;
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


<!-- table edit REMOVE IF NEED BE -->
<style type="text/css">
body {
	font-family: 'Calibiri', Verdana, Geneva, sans-serif;
	background: #cacfd0;
}

table {
	width: 97%;
	background: #f0f7f8;
	margin-left: 15px;
	border: 1px solid #AAAAAA;
} 
table thead tr th {
	border-bottom: dashed 1px #b7bcbd;
	padding: 10px 10px;
}

table thead tr th {
	text-transform: uppercase;
	font-size: 16px;
	font-family: Georgia, "Times New Roman", Times, serif;
	text-align: left;
}

table tbody tr td {
	font-size: 14px;
	text-align: center;
	text-overflow: ellipsis;
} 
table tbody tr td {
	text-align: left;
	padding: 10px;
}

table tbody tr .quantity_td {
	text-align: center
}
</style>



<script>

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

	var orderList="";
function sendOrderList(){
		
		
		document.getElementById('orderList').value=orderList ;
		 
		document.updateDispatch2.submit();		
	}
	
function dispatchClick(control) {
	var checkId = "dispatchCheckIndex" + control;
	document.getElementById(checkId).disabled=true;
	orderList = orderList+"$"+control;
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

<style type="text/css">
body {
	font-family: 'Calibiri', Verdana, Geneva, sans-serif;
	background: #cacfd0;
}

table {
	width: 97%;
	background: #f0f7f8;
	margin-left: 15px;
	border: 1px solid #AAAAAA;
}

table thead tr th {
	border-bottom: dashed 1px #b7bcbd;
	padding: 10px 10px;
}

table thead tr th {
	text-transform: uppercase;
	font-size: 16px;
	font-family: Georgia, "Times New Roman", Times, serif;
	text-align: left;
}

table tbody tr td {
	font-size: 14px;
	text-align: center;
	text-overflow: ellipsis;
}

table tbody tr td {
	text-align: left;
	padding: 10px;
}

table tbody tr .quantity_td {
	text-align: center
}
</style>

<body>



	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<form:form method="GET" action="/SapeStore/updateRentDispatch"
		name="updateForm"
		style="height: 25px; font-size: initial; width: 1000px">
		<h3 class="sapfont">Here is the list of all the Purchased Orders
			showing their dispatched status and payment status.</h3>
		<table id="tablepaging" class="yui"
			style="width: 950px; height: 91px; border-bottom-width: 0px">
			<thead>
				<tr>
					<th>ORDER NUMBER</th>
					<th>ITEMS</th>
					<th>AMOUNT</th>
					<th>DISPATCH ORDER(S)</th>
					<th>PAYMENT RECEIVED</th>
				</tr>
			</thead>
			<c:forEach items="${purchasedOrdersList}" var="current"
				varStatus="loop">
				<tbody>
					<tr id="index${current.orderId}">
						<td>${current.orderId}</td>
						<td>${current.bookTitle}</td>
						<td>$${current.bookPrice }</td>
						<c:set var="orderStatusVar" value="${current.orderStatus}" />
						<%
							Boolean orderStatusVar = (Boolean) request.getAttribute("orderStatusVar");
						%>
						<td><c:choose>
								<c:when test="${current.orderStatus==true}">
									<input type="checkbox"
										id="dispatchCheckIndex${current.orderItemId}" checked disabled
										onclick="dispatchClick(${current.orderItemId})" />
								</c:when>
								<c:otherwise>
									<input type="checkbox"
										id="dispatchCheckIndex${current.orderItemId}"
										onclick="dispatchClick(${current.orderItemId})" />
								</c:otherwise>
							</c:choose></td>


						<c:set var="paymentReceivedVar" value="${current.paymentStatus}" />
						<%
							Boolean paymentReceivedVar = (Boolean) request.getAttribute("paymentReceivedVar");
						%>
						<td><c:choose>
								<c:when test="${current.paymentStatus=='true'}">
									<input type="checkbox" id="returnCheckIndex${current.orderId}"
										checked disabled onclick="returnClick(this)" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="returnCheckIndex${current.orderId}"
										disabled onclick="returnClick(this)" />
								</c:otherwise>
							</c:choose></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>


		<div
			style="margin-left: 0em; float: left; margin-bottom: 1em; margin-top: 5em; height: 30px;">
			<input type="button" onclick="sendOrderList()" name="dispatch"
				style="font-family: Georgia;" id="dispatch"
				value="DISPATCH ORDER(S)" />
		</div>
		<div
			style="float: left; margin-bottom: 1em; margin-top: 5em; margin-left: 1em; height: 30px;">
			<input type="button" name="return" style="font-family: Georgia;"
				id="return" value="PAYMENT RECEIVED"
				onclick="location.href= '/SapeStore/trackPayment'" />
		</div>
		<div id="pageNavPosition1" align="center"></div>
	</form:form>

	<form:form name="updateDispatch2"
		action="/SapeStore/updateRentDispatch" method="GET">
		<input type="hidden" name="orderList" id="orderList" />
		<input type="button" id="braavos2" hidden="hidden">
	</form:form>


	<script type="text/javascript">
		var pager = new Pager('tablepaging', 20);
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


<%@ include file="../footer.jsp"%>

</body>
</html>
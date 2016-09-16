<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../header.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Book Details</title>
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

var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> Ã‚Â« Prev </span> ';

for (var page = 1; page <= this.pages; page++)

pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';

pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next Ã‚Â»</span>';

element.innerHTML = pagerHtml;
}

}

}

</script>
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

<body>

	<script type="text/javascript">
$(document).ready(function(){
	$("#loginPop").click();
});
$(document).ready(function(){
	
	$("#reviewPop").click();
});
function changeVal()
{

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
					<li><c:choose>
							<c:when test="${checkMe==false}">
								<a id="toggle_checkMe" href="togglecheckMe"><img
									src="img/unchecked_ps.png"></a>

							</c:when>
							<c:otherwise>
								<a id="toggle_checkMe" href="togglecheckMe"><img
									src="img/checked_ps.png"></a>
							</c:otherwise>
						</c:choose> <input type="hidden" name="categoryId" value="${categoryId}" />
						<input type="hidden" name="categoryName" value="${categoryName}" />
					</li>
					<!-- 					<li><a id="header_shoppingCart" class='inline' -->
					<!-- 						href="#shoppingCart"><img src="img/icon_cart.jpg" width="15" -->
					<!-- 							height="12" alt="cart"></a></li> -->
					<li><a id="header_shoppingCart" href="/SapeStore/shoppingCart"><img
							src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
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
					<li class="active"><a id="header_home"
						href="/SapeStore/welcome?checkMe=${checkMe}">HOME</a></li>
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
				<h2>Book Categories</h2>
				<nav> <!-- left navigation -->
				<ul>
					<c:forEach items="${catList}" var="current">
						<li
							<c:if test="${categoryName==current.categoryName}">
         		class="highlighted"
         		</c:if>>
							<a
							href="/SapeStore/bookListByCat?categoryId=${current.categoryId}&categoryName=${current.categoryName}&checkMe=${checkMe}"
							title="${current.categoryName}"> ${current.categoryName }</a>
						</li>
					</c:forEach>

				</ul>
				</nav>
			</div>
		</form:form>

		<div id="mainContent">
			<div class="clearfix"></div>

			<form:form action="" modelAttribute="book" method="post">
				<div>
					<table align="left">
						<tr>
							<td style="padding: 0 5px 0 5px;" rowspan="6"><img
								src="${book.bookFullImage}" width="152" height="222"
								alt="${book.bookTitle}" /></td>
							<td style="overflow: hidden; padding: 0 5px 0 5px;"><b>${book.bookTitle }</b></td>
							<td style="overflow: hidden; padding: 0 5px 0 5px;"><a
								href="/SapeStore/addToWishList?isbn=${book.isbn}"><font
									size="2" color="red"><u>+AddToWishlist</u></font></a></td>
							<td style="overflow: hidden; padding: 0 5px 0 5px;"><c:choose>
									<c:when test="${not empty userId}">
										<a href="#review" class='inline' title="Review" id="reviewPop">
											<font size="2" color="red"><u>! Write a Review</u></font>
										</a>
									</c:when>
									<c:otherwise>
										<script type="text/javascript">
		            		function alertIt()
		            		{
		            			alert("Please login to be able to use the cart.");
		            		}
		            </script>
										<a href="#login" class='inline' title="Review" id="reviewPop"><font
											size="2" color="red"><u>! Write a Review</u></font></a>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td style="padding: 0 5px 0 5px;">${book.bookAuthor }</td>
						</tr>
						<tr>
							<td style="padding: 0 5px 0 5px;"><img
								src="img/ratings-${book.averageRating}.jpg" width="98"
								height="14" alt="ratings" /></td>
						</tr>
						<tr>
							<td style="padding: 0 5px 0 5px;">Publisher :
								${book.publisherName}</td>
						</tr>
						<tr>
							<td style="padding: 0 5px 0 5px;"><a
								class="priceBookDetails">Buy For $${book.bookPrice}</a> <c:choose>
									<c:when test="${not empty userId}">
										<a
											href="/SapeStore/addToShoppingCart?categoryId=${bookVo.categoryId}&categoryName=${categoryName}&checkMe=${checkMe}&isbn=${bookVo.isbn}&type=Purchase"
											title="Add To Cart" class="bookDetailsaddCart"> <img
											style="margin-top: 7px; margin-bottom: -10px;"
											src="img/add_cart.jpg" width="15" height="13"
											alt="add to cart" />
										</a>
									</c:when>
									<c:otherwise>
										<script type="text/javascript">
		            	function alertIt()
		            	{
		            		alert("Please login to be able to use the cart.");
		            	}
		            </script>
										<a href="#login" title="Add To Cart"
											class="bookDetailsaddCart inline" id="addToCart"> <img
											src="img/add_cart.jpg" width="15" height="13"
											alt="add to cart"
											style="margin-top: 7px; margin-bottom: -10px;" />
										</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${book.rentAvailability == 'Y' }">
									<td style="padding: 0 5px 0 5px;"><a
										class="priceBookDetails">Rent For $${book.rentPrice}</a> <c:choose>
											<c:when test="${not empty userId}">
												<a
													href="/SapeStore/addToShoppingCart?categoryId=${book.categoryId}&categoryName=${categoryName}&checkMe=${checkMe}&isbn=${book.isbn}&type=Rent"
													title="Add To Cart" class="bookDetailsaddCart"> <img
													style="margin-top: 7px; margin-bottom: -10px;"
													src="img/add_cart.jpg" width="15" height="13"
													alt="add to cart" />
												</a>
											</c:when>
											<c:otherwise>
												<script type="text/javascript">
		            		function alertIt()
		            		{
		            			alert("Please login to be able to use the cart.");
		            		}
		            </script>
												<a href="#login" title="Add To Cart"
													class="bookDetailsaddCart inline" id="addToCart"><img
													src="img/add_cart.jpg" width="15" height="13"
													alt="add to cart"
													style="margin-top: 7px; margin-bottom: -10px;" /></a>
											</c:otherwise>
										</c:choose>
								</c:when>
							</c:choose>

							<td><c:choose>
									<c:when test="${book.rentAvailability == 'Y' }">
										<td><a class="priceBookDetails" style="width: 150px">Customized
												Rent</a> <c:choose>
												<c:when test="${not empty userId}">
													<a href="#rent" id="rentPop" title="Add To Cart"
														class="bookDetailsaddCart"><img
														style="margin-top: 7px; margin-bottom: -10px;"
														src="img/add_cart.jpg" width="15" height="13"
														alt="add to cart" style="padding-top: 1px" /> </a>
												</c:when>
												<c:otherwise>
													<script type="text/javascript">
		            		function alertIt()
		            		{
		            			alert("Please login to be able to use the cart.");
		            		}
		            </script>
													<a href="#login" title="Add To Cart"
														class="bookDetailsaddCart inline" id="addToCart"><img
														src="img/add_cart.jpg" width="15" height="13"
														alt="add to cart"
														style="margin-top: 7px; margin-bottom: -10px;" /></a>
												</c:otherwise>
											</c:choose></td>
									</c:when>
								</c:choose></td>
						</tr>
					</table>
				</div>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<hr style="border-bottom: dotted 1px #000" />
				<div>
					<table align="left">
						<tr>
							<th align="left"><span style="font-size: 20px">Customer
									Reviews</span></th>
							<c:forEach items="${bookRatingCommentList}" var="current">
								<tr>
									<td style="padding-top: 1em"><strong>${current.userId}</strong></td>
									<td style="padding-top: 1em"><c:choose>
											<c:when test="${current.bookRating == null}">
												<img src="img/ratings-0.jpg" width="98" height="14"
													alt="ratings" />
											</c:when>
											<c:otherwise>
												<img src="img/ratings-${current.bookRating}.jpg" width="98"
													height="14" alt="ratings" />
											</c:otherwise>
										</c:choose></td>
									<td
										style="padding-top: 1em; font-size: 12px; line-height: 100%">
										<p>
											<fmt:formatDate value="${current.bookCommentDate }"
												type="date" />
										</p>
									</td>
								<tr>
									<td style="line-height: 100%">${current.bookComments }</td>
								</tr>
							</c:forEach>
						</tr>
					</table>
				</div>
			</form:form>

		</div>

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

	<!-- This contains the hidden content for review popup -->
	<div style="display: none">
		<div id="review" class="popup">

			<%@include file="review.jsp"%>
		</div>
	</div>

	<!-- Forgot password-->
	<div style="display: none">
		<form method="post" action="Validate" id="forgotPassword" onsubmit="">
			<fieldset>
				<label for="email">Enter your email id<span class="required">*</span></label>
				<input type="email" placeholder="Name" required="" name="name">
				<input type="submit" value="Submit" class="lightButton">
				<!-- <div id="someHiddenDiv" style="display:none">Your password has been sent to your registered mail.</div> -->
				<!--  <div style="display:none"> -->

			</fieldset>
		</form>
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
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
<title>SapeStore-ShoppingCart</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="css/normalize.css" type="text/css">
<link rel="stylesheet" href="css/main.css" type="text/css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script type="text/javascript">
	dataLayer = [];
</script>

<style type="text/css">
.rent {
	font-size: 14px;
	text-align: center;
}

.colorred {
	color: red;
	font-size: 14px;
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
history.forward();
</script>
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

/* $(document).ready(function(){
	$("#dispatch").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	$("#return").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	
}); */

$(document).ready(function(){
	
	$("#wishListPop").click();
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
<script type="text/javascript">

function myFunction(index,isbn, oldquan, rentprice, bookprice, availQuantity, oldtype) {
	var price;
	var oldsubtotal;
	var type=$("#transactiontype-"+index).val();
	
	var quantity = $("#book_quantity-"+index).val();
	var flag=0;
	if(parseInt(availQuantity)<parseInt(quantity))
		{
			alert("Quantity you entered is more than available quantity.");
			flag=1;
			window.location.reload();
		}
	if(isNaN(quantity)){
		alert("Please enter a valid number");
		flag=1;
		window.location.reload();
		}
	if(quantity.length<=0){
		alert("Quantity field can't be left empty");
		flag=1;
		window.location.reload();
		}
	if(parseInt(quantity)<=0){

		alert("Quantity must be greater than 0");
		flag=1;
		window.location.reload();
		}

	if(quantity.indexOf('.')>=0){

		alert("Enter a valid amount");
		flag=1;
		window.location.reload();
		}
	if(flag==0){
	document.getElementById("transtype-"+index).innerHTML=type;
	if(type=="Rent") {
		document.getElementById("bookprice-"+index).innerHTML=rentprice.toString();	
		price=rentprice;
	}
	else if(type=="Purchase") {
		document.getElementById("bookprice-"+index).innerHTML=bookprice.toString();
		price=bookprice;
	}
	var subtotal=parseInt(price)*parseInt(quantity);
	var oldtotal= parseInt(document.getElementById("total").innerHTML);
	document.getElementById("subamount-"+index).innerHTML= subtotal.toString();	
	url ="/SapeStore/updateController?uisbn="+isbn+"&uquantity="+quantity+"&utype="+type+"&oldtype="+oldtype;
	$.ajax({
		 type: "GET",
	     url:url,
		success:function(data) {
			alert(type);
			alert(quantity);
	}
	
});
	
	window.location.reload();
	}
	else {
			document.getElementById("book_quantity-"+index).innerHTML=oldquan.toString();
			document.getElementById("transtype-"+index).innerHTML=type.toString();
			document.getElementById("bookprice-"+index).innerHTML=price.toString();
			document.getElementById("subamount-"+index).innerHTML=subtotal.toString();
		}
}

 function removeBook(index,isbn,type) {
	 var quantity = $("#book_quantity-"+index).val();
	 url= "/SapeStore/removeFromCart" ;
	$.ajax({
		  type: "GET",
		  url:url,
			dataType : "html",
		  data: { disbn : isbn, dquantity : quantity, dtype: type },
		 success:function(data){
			alert("success");
		  }
		});
	window.location.reload();
} 


	 function continueShop(){
			window.location.href="/SapeStore/welcome";
		}

	 function shippingAddress(){
		 	dataLayer.push({'event': 'checkout'});
			window.location.href="/SapeStore/shippingAddress";

			}
		
</script>
</head>

<body>

	<script type="text/javascript">
$(document).ready(function(){
	$("#loginPop").click();
});
function changeVal()
{
	var sel = document.getElementById('subs');
	var value=sel.options[sel.selectedIndex].value;
	if(value==-1){
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

						<form id="checkMeForm" action="togglecheckMe">
							<c:choose>
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
							</c:choose>
						</form> <input type="hidden" name="categoryId" value="${categoryId}" />
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
								onchange="location = this.options[this.selectedIndex].value;">
								<option value="-1" style="font-size: 10px;">Welcome
									${username}</option>
								<option value="./editInfo" style="font-size: 10px;">Edit
									Profile</option>
								<option value="orderTracking.jsp" style="font-size: 10px;">Order
									Status Tracking</option>
								<option value="./transactionHistory" style="font-size: 10px;">Transaction
									History</option>
								<option value="./clientanalytics" style="font-size: 10px;">Analytics</option>
							</select>
						</c:when>
						<c:otherwise>
							<select id="account" name="account" style="font-size: 12px;"
								onchange="location = this.options[this.selectedIndex].value;">
								<option value="-1" style="font-size: 10px;">Welcome
									${username}</option>
								<option value="./unauthUserProfile" style="font-size: 10px;">Edit
									Profile</option>
								<option value="./unauthUserProfile" style="font-size: 10px;">Order
									Status Tracking</option>
								<option value="./unauthUserProfile" style="font-size: 10px;">Transaction
									History</option>
								<option value="./clientanalytics" style="font-size: 10px;">Analytics</option>
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
							<li><a id="header_account" href="./clientanalytics">Client360</a></li>
						</c:otherwise>

					</c:choose>

					<c:choose>
						<c:when test="${not empty userId}">

							<li><a id="header_wishList" href="displayWishList">Wishlist</a></li>

						</c:when>
						<c:otherwise>
							<script type="text/javascript">
		            		function alertIt()
		            		{
		            			alert("Please login to access your WishList");
		            		}
		            </script>
							<li><a id="header_wishList" href="#login" class='inline'
								title="WishList" id="wishListPop">Wishlist</a></li>
						</c:otherwise>
					</c:choose>



					<!-- <li><a href="displayWishList">Wishlist</a></li> -->
					<jsp:include page="Logout.jsp" flush="true" />
					<li><a id="header_search" href="/SapeStore/getBookSearchForm""><img
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


			<%-- DisplayShoppingCart.jsp  --%>
			<%@page import="com.sapestore.vo.BookVO"%>
			<%@ page contentType="text/html;charset=UTF-8" language="java"%>
			<%@ taglib prefix="form"
				uri="http://www.springframework.org/tags/form"%>
			<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


			<!-- <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script> -->
			<script src="js/jQuery.js "></script>
			<div id="shoppingcart">

				<div id="formdiv">
					<form:form name="cart" action="displayShoppingCart" method="GET">
						<c:choose>
							<c:when
								test="${ShoppingCart.totalQuantity ==0 || empty ShoppingCart}">
								<div class="colorred">
									<h1 align="center">Cart is Empty</h1>
								</div>
							</c:when>
							<c:otherwise>
								<h2>Your Cart</h2>
								<div class="scroller">
									<table id="tablepaging" class="yui" cellspacing="10">
										<%-- Display the heading of the shoppingCart --%>
										<thead>
											<tr>

												<th></th>
												<th>Item(s)</th>

												<th>Qty.</th>

												<th>Type</th>

												<th>Price</th>

												<th>Subtotal</th>

											</tr>
										</thead>
										<c:forEach items="${ShoppingCart.booksInCart}" var="current"
											varStatus="loop">

											<tbody>
												<tr>

													<td style="width: 60px"><img
														src="${current.thumbPath}" width="81" height="112"
														alt="product name"></td>

													<td><div id="product_name">${current.bookTitle}</div>
														<c:choose>
															<c:when test="${current.type =='Purchase'}">
																<script>
																	dataLayer.push({'cart' : {
																	    'booktitle' : '${current.bookTitle}',
																	    'bookprice' : '${current.bookPrice}',
																		'type' : '${current.type}',
																		'bookquantity': '${current.quantity}'
																	  }});
																</script>
															</c:when>
															<c:otherwise>
																<div id="rentdate-${loop.index}" class="rent">
																	Return by:<span class="colorred">
																		${current.expectedReturnDate}</span>
																</div>
																<script>
																	dataLayer.push({'cart' : {
																	    'booktitle' : '${current.bookTitle}',
																	    'bookprice' : '${current.bookPrice}',
																	    'rentprice' : '${current.rentPrice}',
																		'type' : '${current.type}',
																		'bookquantity': '${current.quantity}'
																	  }});
																</script>
															</c:otherwise>
														</c:choose></td>

													<td><input id="book_quantity-${loop.index}"
														class="quantity" name="quantity" +type="text" min="1"
														onchange="myFunction('${loop.index}','${current.isbn}','${current.quantity}','${current.rentPrice}', '${current.bookPrice}', '${current.availableQuantity}', '${current.type}');"
														value="${current.quantity}" size="4" /></td>
													<td><div id="transtype-${loop.index}">
															<select id="transactiontype-${loop.index}"
																class="transactiontype"
																onchange="myFunction('${loop.index}','${current.isbn}','${current.quantity}','${current.rentPrice}', '${current.bookPrice}', '${current.availableQuantity}', '${current.type}');">
																<option value="${current.type }">${current.type }</option>
																<c:choose>
																	<c:when
																		test="${current.type =='Purchase' && current.rentAvailable=='Y'}">
																		<option value="Rent">Rent</option>
																	</c:when>
																	<c:when test="${current.type =='Rent'}">
																		<option value="Purchase">Purchase</option>
																	</c:when>

																</c:choose>
															</select>
														</div></td>

													<td>
														<div id="bookprice-${loop.index}" class="bookprice">${current.bookPrice}</div>
													</td>
													<td><div id="subamount-${loop.index}"
															class="subamount">${current.bookPrice * current.quantity }</div>

													</td>
													<td><input type="hidden" name="item" value="" /> <input
														id="removeButton" type="button" value="Remove"
														class="darkButton" name="remove"
														onclick="removeBook('${loop.index}', '${current.isbn}', '${current.type}');">
													</td>

												</tr>

											</tbody>
										</c:forEach>

									</table>
								</div>
								<!-- <div id="pageNavPosition"></div> -->

								<div class="clearfix"></div>
								<div align="right_not">

									<p id="total">
										Total Amount Payable :<span class="colorred" id="totalamount">$${ShoppingCart.totalPrice}</span>
									</p>
								</div>

								<input type="button" value="Continue Shopping"
									id="continueButton" class="lightButton"
									onclick="continueShop();">
								<input type="button" value="Checkout" id="checkoutButton"
									class="lightButton checkout" onclick="shippingAddress();">

								<script type="text/javascript">
		var pager = new Pager('tablepaging', 10);
		pager.init();
		pager.showPageNav('pager', 'pageNavPosition');
		pager.showPage(1);
	</script>
							</c:otherwise>
						</c:choose>
					</form:form>
				</div>
			</div>
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


	<!-- Forgot password-->
	<div style="display: none">
		<form method="post" action="Validate" id="forgotPassword" onsubmit="">
			<fieldset>
				<label for="email">Enter your email id<span class="required">*</span></label>
				<input type="email" placeholder="Email.Id" required="" name="name">
				<input type="submit" value="Submit" class="lightButton">

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
	<%-- <%@ include file="../CustomDivolte.jsp"%>
	<%@ include file="../CustomPiwik.jsp"%>
	<%@ include file="../CustomOmniture.jsp"%> --%>
	<script type="text/javascript">
		$( document ).ready(function() { 
			if ('${userId}' != "") {
			
			_paq.push(['setUserId', '${userId}']);
			_paq.push(['setCustom_var_k1', '${cookieId}']);
			_paq.push(['trackPageView']);
			
			testFunction();
			}
		
		});
		
			
	</script>
	<!-- <script src="//10.209.18.177:8290/divolte.js" defer async></script> -->
	<script type="text/javascript">
	function testFunction() {
		setTimeout(function(){
			
			divolte.signal('userLoggedIn', { userId : '${userId}' , cookieId : '${cookieId}'});
			
			
		}, 0);
		
	}
			</script>
	<%@ include file="../footer.jsp"%>

</body>
</html>
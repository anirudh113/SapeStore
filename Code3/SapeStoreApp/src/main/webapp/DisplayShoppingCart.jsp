
<%-- DisplayShoppingCart.jsp  --%>
<%@page import="com.sapestore.vo.BookVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
.pg-normal {
	color: #de2728;
	font-size: 14px;
	cursor: pointer;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

.rent {
	font-size: 14px;
	text-align: center;
}

.colorred {
	color: red;
	font-size: 14px;
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
	padding: 5px;
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
	padding-left: 774px;
	padding-bottom: 2em;
	padding-top: 0.5em;
	padding-right: 2px;
	padding-left: 740px;
	margin-right: 7px;
}
</style>
<!-- <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script> -->
<script src="js/jQuery.js "></script>
<script type="text/javascript">
history.forward();
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
if(pages>1){
var element = document.getElementById(positionId);

var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> Â« Prev </span> ';

for (var page = 1; page <= this.pages; page++)

pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';

pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next Â»</span>';

element.innerHTML = pagerHtml;
}
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
			window.location.href="/SapeStore/shippingAddress";

			}
		
</script>
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

									<td style="width: 60px"><img src="${current.thumbPath}"
										width="81" height="112" alt="product name"></td>
									
									<td id="product_name">${current.bookTitle}<br> <c:choose>
											<c:when test="${current.type =='Purchase'}">
											</c:when>
											<c:otherwise>
												<div id="rentdate-${loop.index}" class="rent">Return by:<span class="colorred">
													${current.expectedReturnDate}</span></div>
											</c:otherwise>
										</c:choose>
									</td>
									
									<td><input id="book_quantity-${loop.index}"
										name="quantity" +type="text" min="1"
										onchange="myFunction('${loop.index}','${current.isbn}','${current.quantity}','${current.rentPrice}', '${current.bookPrice}', '${current.availableQuantity}', '${current.type}');"
										value="${current.quantity}" size="4" /></td>
									<td><div id="transtype-${loop.index}">
											<select id="transactiontype-${loop.index}"
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
										<div id="bookprice-${loop.index}">${current.bookPrice}</div>
									</td>
									<td><div id="subamount-${loop.index}">${current.bookPrice * current.quantity }</div>

									</td>
									<td><input type="hidden" name="item" value="" /> <input
										type="button" value="Remove" class="darkButton" name="remove"
										onclick="removeBook('${loop.index}', '${current.isbn}', '${current.type}');">
									</td>

								</tr>

							</tbody>
						</c:forEach>

					</table>
				</div>
				<!-- <div id="pageNavPosition"></div> -->

				<div align="right">
					<p id="total">Total Amount Payable :<span class="colorred">$${ShoppingCart.totalPrice}</span>
					</p>
				</div>

				<input type="button" value="Continue Shopping" class="lightButton"
					onclick="continueShop();">
				<input type="button" value="Checkout" class="lightButton checkout"
					onclick="shippingAddress();">

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
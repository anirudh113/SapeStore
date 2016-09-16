<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%@ include file="../header.jsp"%>
<title>SapeStore-Edit Profile</title>
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
	color: #1d1d1d;
	font-size: 15px;
	font-family: SapientSansMedium;
}

#fnm {
	width: 300px;
	height: 30px;
	background-color: #f5f5f5;
}

#tx {
	width: 200px;
	height: 30px;
	background-color: #f5f5f5;
}

#zp {
	width: 80px;
	height: 30px;
	background-color: #f5f5f5;
}

#but {
	height: 22px;
	background-color: #21addd;
	color: #ffffff;
	font-weight: bold;
}

#list {
	color: #1d1d1d;
	font-size: 13px;
	font-family: SapientSansMedium;
	background-color: #f5f5f5;
	width: 200px;
	height: 30px;
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

var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> Â« Prev </span> ';

for (var page = 1; page <= this.pages; page++)

pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';

pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next Â»</span>';

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
	else if (document.getElementById('account').value == '4') {
		window.location.href = "clientanalytics";
	}
	else if (document.getElementById('account').value == '5') {
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
			method="POST" commandName="welcome">
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
							</c:choose><input type="hidden" name="categoryId" value="${categoryId}" />
							<input type="hidden" name="categoryName" value="${categoryName}" />
						</li>
						<!-- <li><a id="header_shoppingCart" class='inline'
							href="#shoppingCart"><img src="img/icon_cart.jpg" width="15"
								height="12" alt="cart"></a></li> -->
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
										Information></option>
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
								href="/SapeStore/welcome?checkMe=${checkMe}">Home</a></li>


							<li><a id="header_wishList"
								href="/SapeStore/displayWishList">Wishlist</a></li>
							<jsp:include page="Logout.jsp" flush="true" />
							<li><a id="header_search"
								href="/SapeStore/getBookSearchForm"><img alt="searchImage"
									src="img/magnifier-icon.png"></a></li>

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
							<li
								<c:if test="${RequiredName}=='personalinformation'">
         		class="highlighted"
         		</c:if>>
								<a href="editInfo" title="personalinformation">Personal
									Information </a>
							</li>
							<li
								<c:if test="${RequiredName}=='transactionhistory'">
         		class="highlighted"
         		</c:if>>
								<a href="transactionHistory" title="transactionhistory">Transaction
									History </a>
							</li>
							<li
								<c:if test="${RequiredName}=='orderstatus'">
         		class="highlighted"
         		</c:if>>
								<a href="orderTracking.jsp" title="track">Order Status</a>
							</li>
							<li
								<c:if test="${RequiredName}=='analytics'">
         		class="highlighted"
         		</c:if>>
								<a href="clientanalytics" title="analytics">Analytics </a>
							</li>
						</ul>
					</nav>
				</div>
		</form:form>
		<script type="text/javascript">
		function checkName(){
				
				var flag=true;
				var name = document.forms["Myform"]["name"].value;
				if(name.length<1){
					document.getElementById('reName').innerHTML="Invalid Name Length";
					flag=false;
					}
				return flag;
			}
	function checkphone() {
		

	    var x = document.forms["Myform"]["phone"].value;
	    var flag = true;
	    if(x.length<10){
	    	document.getElementById('rePhone').innerHTML="Enter 10 digit phone number";
	    	flag = false;
	    }else{
	    	re = /[0-9]/;
	        if(!(re.test(x))) {
	        	document.getElementById('rePhone').innerHTML="Invalid Input";
	        	flag=false;
	           }
	       
	    }
	    return flag;
	}

	function checkAddress(){
		

		var flag =true;
		if(document.forms["Myform"]["addressLine1"].value.length<1){
			flag = false;
			document.getElementById('reAddress').innerHTML= "please enter address";
		}
		return flag;
	}
	function populateCity(stateId){
		

		 
		 $.ajax({
			url:"/SapeStore/getCityByState?stateId="+stateId,
			data:($("select").serialize()),
			success:function(data){
				 $("#populateCity").html(data); 
			}
		}); 
		
	}

	    function checkmobile() {
			

	    	var flag = true;
	        var x = document.forms["Myform"]["mobileNumber"].value;
	        if(x.length<10){
	        	flag=false;
	        	document.getElementById('reMobile').innerHTML="Enter 10 digit mobile number";
	        }else{
	        	re = /[0-9]/;
	            if(!(re.test(x))) {
	            	flag=false;
	            	document.getElementById('reMobile').innerHTML="Invalid Input";
	              
	            }
	        }
	        return flag;
	    }
	    function count() {
			

	        var flag=true;
	    	re = /[0-9]/;
	        if(!(re.test(Myform.postalCode.value))) {
	        	flag=false;
	        	document.getElementById('rePostal').innerHTML="Zip code contain only numbers";
	        	}
        	return flag;
	    }
	            
	    function checkpassword(){
			

	    	var flag=true;
	   	 var x = document.forms["Myform"]["password"].value;
	   	 var y = document.forms["Myform"]["cfmpassword"].value;
	   	 if(x!=y){
	   		 flag=false;
	   		 document.getElementById('repass').innerHTML="password does not match";
	   	 }
	   	 return flag;
	   }

		 function checkPasswordSize(){
			

				var flag=true;
				var x = document.forms["Myform"]["password"].value;
				if(x.length<6){
					flag=false;
					document.getElementById('rePassLength').innerHTML="invalid password length ";
					}
				return flag;
			 }
		 function validateUpdateForm() {
			var flag = [];
			var finalflag=true;
			 flag[0]=checkName();
			 flag[1]=checkPasswordSize();
			 flag[2]=checkpassword();
			 flag[3]=checkAddress();
			 flag[4]=count();
			 flag[5]=checkphone();
			 flag[6]=checkmobile();
			 var i;
			 for(i=0;i<flag.length;i++){
				 if(!flag[i]){
					 	
					 return false;
				 }
			 }
			 document.Myform.action = "/SapeStore/updateProfile";
				      
				    document.Myform.submit();             
				    return true;
			 
		}
		 function editInfo(){
			 document.Myform.action = "/SapeStore/editInfo";
				     
				    document.Myform.submit();             
				    return true;
		 }
		 
	</script>
		<script type="text/javascript">

	function checkUpdateForm(){

		
		}

	</script>
		<div id="mainContent">
			<h2>
				<label> <strong>Edit Profile</strong></label>
			</h2>
			<br>
			<hr style="border-top: dotted 1px;" />

			<div class="clearfix"></div>

			<form:form id="Myform" method="POST" commandName="profilevo"
				name="Myform">
				<table id="tablepaging" class="yui"
					style="width: 650px; height: 91px; border-bottom-width: 0px">
					<tr>
						<td><label>Fullname</strong></label><font color="red">*</font></td>
						<td><input id="fnm" type="text" name="name"
							value="${profilevo.name}" onchange="" /> <form:errors
								path="name" cssClass="error" />
							<div id="reName" style="color: red; font-size: 2"></div></td>
					</tr>
					<tr>
						<td><label>Login Name</strong></label> <font color="red">*</font></td>
						<td><input id="tx" type="text" name="userId"
							readonly="readonly" value="${userId}" /> <form:errors
								path="userId" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Login Password</strong></label><font color="red">*</font></td>
						<td><input id="tx" type="password" name="password"
							title="Six or more characters"
							placeholder="Six or more characters" />
							<div id="rePassLength" style="color: red; font-size: 2"></div> <form:errors
								path="password" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Retype Password</strong></label> <font color="red">*</font>
						</td>
						<td><input id="tx" type="password" name="cfmpassword"
							placeholder="Retype Password" onblur="checkpassword()" />
							<div id="repass" style="color: red; font-size: 2"></div></td>
					</tr>
					<tr>
						<td><label>Email</strong></label> <font color="red">*</font></td>
						<td><input id="tx" type="email" name="emailAddress"
							value="${profilevo.emailAddress}" readonly="readonly" /></td>
					</tr>
					<tr>


						<td><label>Address Line1</strong></label><font color="red">*</font>
							<div id="reAddress" style="color: red; font-size: 2"></div></td>
						<td><input id="address" type="text" name="addressLine1"
							value="${profilevo.addressLine1}"> <form:errors
								path="addressLine1" cssClass="error" />
							<div id="reAddress" style="color: red; font-size: 2"></div></td>
					</tr>
					<tr>
						<td><label>Address Line2</strong></label></td>
						<td><input id="tx" type="text" name="addressLine2"
							value="${profilevo.addressLine2}" /></td>
					</tr>
					<tr>
						<td><label>State</strong></label><font color="red">*</font></td>
						<td><select name="stateId" id="list"
							onchange="populateCity(this.value)">
								<option value="" disabled="disabled">Select Your State</option>
								<c:forEach items="${statelist}" var="current">
									<option value="${current.stateId}">${current.stateName}</option>
								</c:forEach>
						</select> <form:errors path="stateId" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>City</strong></label><font color="red">*</font></td>
						<td><div id="populateCity">
								<select name="cityId" id="list">
									<option value="" disabled="disabled">Select Your City</option>
									<c:forEach items="${citylist}" var="current">
										<option value="${current.cityId}">${current.cityName}</option>
									</c:forEach>
								</select>
							</div> <form:errors path="cityId" cssClass="error" /></td>
					</tr>

					<tr>
						<td style="font-size: 13px;"><label> Zipcode</label><font
							color="red">*</font></td>
						<td><input id="zp" type="text" name="postalCode"
							value="${profilevo.postalCode}" maxlength="5" onblur="count()" />
							<form:errors path="postalCode" cssClass="error" />
							<div id="rePostal" style="color: red; font-size: 2"></div></td>
					</tr>
					<tr>
						<td><label>Phone Number</strong></label></td>
						<td><input id="tx" type="text" name="phone"
							value="${profilevo.phone}" maxlength="10" onblur="checkphone()" />
							<form:errors path="phone" cssClass="error" />
							<div id="rePhone" style="color: red; font-size: 2"></div></td>
					</tr>
					<tr>
						<td><label>Mobile Number</strong></label></td>
						<td><input id="tx" type="text" name="mobileNumber"
							value="${profilevo.mobileNumber}" maxlength="10"
							onblur="checkmobile()" /> <form:errors path="mobileNumber"
								cssClass="error" />
							<div id="reMobile" style="color: red; font-size: 2"></div></td>
					</tr>


					<tr>
						<td style="text-align: left; color: red;"><font size="1">*
								Mandatory Fields</font></td>
					</tr>

					<br>
					<div style="width: 200px; margin-left: auto; margin-right: auto;">
						<tr>
							<td><input id="but" name="but" type="button"
								value="Update Profile" onclick="return validateUpdateForm()"></td>
							<td><input id="but" name="but" type="button" value="Cancel"
								onclick="return editInfo()"></td>
						</tr>
				</table>
		</div>
		</form:form>

		<form name="addtoshoppingcartForm" action="addToShoppingCart"
			method="POST">
			<ul>
				<c:forEach items="${bookList}" var="current">
					<li><a href="javacript:void(0)" title="${current.bookTitle}">
							<img src="${current.bookFullImage}" width="131" height="180"
							alt="${current.bookTitle}" /> <span>${current.bookTitle}</span>
							<p>${current.bookAuthor}</p>
					</a>
						<figure>
							<img src="img/ratings.jpg" width="98" height="14" alt="ratings" />
						</figure>
						<p class="price">$${current.bookPrice}</p> <c:choose>
							<c:when test="${not empty userId}">
								<a
									href="/SapeStore/addToShoppingCart?categoryId=${current.categoryId}&categoryName=${categoryName}&checkMe=${checkMe}&isbn=${current.isbn}"
									title="Add To Cart" class="addCart"> <img
									src="img/add_cart.jpg" width="15" height="13" alt="add to cart" />
								</a>
							</c:when>
							<c:otherwise>
								<script type="text/javascript">
		            	function alertIt()
		            	{
		            		alert("Please login to be able to use the cart.");
		            	}
		            </script>
								<a href="#login" title="Add To Cart" class="addCart inline"
									id="addToCart"><img src="img/add_cart.jpg" width="15"
									height="13" alt="add to cart" /></a>
							</c:otherwise>
						</c:choose></li>
				</c:forEach>
			</ul>
		</form>
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
		<form method="post" action="" id="forgotPassword"
			onsubmit="return ValidateForm();">
			<fieldset>
				<label for="email">Enter your email id<span class="required">*</span></label>
				<input type="email" placeholder="Name" required="" name="name">
				<input type="submit" value="Submit" class="lightButton">
				<div id="someHiddenDiv" style="display: none">Your password
					has been sent to your registered mail.</div>
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
















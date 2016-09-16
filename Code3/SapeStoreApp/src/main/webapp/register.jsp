<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-Registration</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="css/normalize.css" type="text/css">
<link rel="stylesheet" href="css/main.css" type="text/css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<style type="text/css">
table {
	border-collapse: collapse;
}

table tr td {
	padding: 10px;
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
/* $(document).ready(function(){
	$("#loginPop").click();
}); */
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
var z;

function validatename(){
	var x=document.getElementById('fullName').value;
	if (x==""){
		/* document.getElementById('userIdmessage').style="color: red; font-size: 10px;"; */
		document.getElementById('nameError').innerHTML="Please provide Full Name";
	}
	else{
		/* document.getElementById('userIdmessage').style=""; */
		document.getElementById('nameError').innerHTML="";
		z++;
	}
	
}

function validateusername(){
	var x=document.getElementById('loginName').value;
		checkuseridavail(x);
		if (document.getElementById('redmessage').innerHTML=="")
		z++;
}

function validatepass(){
	
	var x1 = document.getElementById('loginPassword').value;
	
	if (x1==""){
		document.getElementById('passError').innerHTML="Please provide Password";
	}
	else{
		document.getElementById('passError').innerHTML="";
		 validatepasscond(x1); 
	}
}

function validatematchpass(x1){
  	 var x2 = document.getElementById('retypePassword').value; 
  	 if(x1!=x2){
  		 document.getElementById('repassError').innerHTML="Please re-type Password";
  	 }
  	 else{
  		document.getElementById('repassError').innerHTML="";
		z++;
  	 }
  }

function validatepasscond(x1){
	
	var patt=/^(?=.*[0-9])[A-Za-z0-9!@#$%^&*]{8,}$/;
	if(!patt.test(x1))
		document.getElementById('passError').innerHTML="Password should have at least 8 characters and at least one number should be included";
	else{
		document.getElementById('passError').innerHTML="";
		validatematchpass(x1);
	}
}

function validategender(){
	var x=document.getElementById('gender').value;
	if ((document.registerForm.gender[0].checked == false ) && ( document.registerForm.gender[1].checked == false ) && ( document.registerForm.gender[2].checked == false ) )
		document.getElementById('genderError').innerHTML="Please select Gender";
	else{
		document.getElementById('genderError').innerHTML="";
		z++;
	}
}

function validateemail(){
	var x=document.getElementById('email').value;
	var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
   
	if (x=="")
		document.getElementById('emailError').innerHTML="Please provide Email ID";
	else if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
		document.getElementById('emailError').innerHTML="Please provide valid e-mail ID";
	else{
		document.getElementById('emailError').innerHTML="";
		z++;
	}
}

function validateaddress(){

	var x=document.getElementById('addressLine1').value;
	if (x=="")
		document.getElementById('addressError').innerHTML="Please provide Address";
	else{
		document.getElementById('addressError').innerHTML="";
		z++;
	}
}

function validatestate(){
	var x=document.getElementById('statename').value;
	if(x=="0")
		document.getElementById('stateError').innerHTML="Please provide State";
	else{
		document.getElementById('stateError').innerHTML="";
		z++;
	}
}

function validatecity(){
	var x=document.getElementById('cityname').value;
	if(x=="0")
		document.getElementById('cityError').innerHTML="Please provide City";
	else{
		document.getElementById('cityError').innerHTML="";
		z++;
	}
}

function validatezipcode(){
	var x=document.getElementById('zipCode').value;
	var patt = /[0-9]{5}/;
    
	if (x=="")
		document.getElementById('zipcodeError').innerHTML="Please provide Zip Code";
	else if(!(patt.test(x))) {
    	document.getElementById('zipcodeError').innerHTML="Incorrect format";
    	}
	else{
		document.getElementById('zipcodeError').innerHTML="";
		z++;
	}
}

function validatephonemobile(){

	var x=document.getElementById('phoneNo').value;
	var y=document.getElementById('mobileNo').value;
	if (x=="" && y=="")
		document.getElementById('phoneError').innerHTML="Please provide either the Phone number or Mobile number";
	else{
		document.getElementById('phoneError').innerHTML="";
		if (x=="")
			validatemobile();
		else if (y=="")
			validatephone();
		else
			z++;
	}
}

function validatephone() {
	var patt = /[0-9]{10}/;
    var x = document.getElementById('phoneNo').value;
    
    if(!(patt.test(x))) 
        	document.getElementById('phoneError').innerHTML="Incorrect format";
    else {
    	document.getElementById('phoneError').innerHTML="";
    	z++;
    }
       
}



    function validatemobile() {
    	var patt = /[0-9]{10}/;
    	var x=document.getElementById('mobileNo').value;
    	if(!(patt.test(x))) 
        	document.getElementById('mobileError').innerHTML="Incorrect format";
    else {
    	document.getElementById('mobileError').innerHTML="";
        z++;
    }
    }
         


    function validate(){
		z=0;
    	validatename();
    	validatepass();
    	validategender();
    	validateemail();
    	validateaddress();
    	validatestate();
    	validatecity();
    	validatezipcode();
    	validatephonemobile();
    	validateusername();

    	if (z==10)
    		document.registerForm.submit();
    }
    

    function checksearchform(){
  	if((document.searchForm.bookTitle.value=="")&&(document.searchForm.bookAuthor.value=="")&&(document.searchForm.isbn.value=="")&&(document.searchForm.publisherName.value=="")&&(document.searchForm.categoryId.value=="0")){
  		alert("Please enter any one field");
  	}
  	else{
  		document.searchForm.submit();
  	}
  }


   

</script>
<script type="text/javascript">
var r={'special':/[\W]/g}
function valid(o,w)
{
o.value = o.value.replace(r[w],'');
}
function onlyAlphabets(evt) {
    var charCode;
    if (window.event)
        charCode = window.event.keyCode;  //for IE
    else
        charCode = evt.which;  //for firefox
    if (charCode == 32) //for &lt;space&gt; symbol
        return true;
    if (charCode > 31 && charCode < 65) //for characters before 'A' in ASCII Table
        return false;
    if (charCode > 90 && charCode < 97) //for characters between 'Z' and 'a' in ASCII Table
        return false;
    if (charCode > 122) //for characters beyond 'z' in ASCII Table
        return false;
    return true;
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

function checkuseridavail(inputuser){

	$.ajax({
		url:"/SapeStore/checkuseravail?inputuser="+inputuser,
		data:($("select").serialize()),
		success:function(data){
			 $("#userIdmessage").html(data); 
		}
	}); 
	return true;

}


</script>
</head>

<body>

	<script type="text/javascript">

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
					<!-- 					<li><a href="#shoppingCart -->
					<!-- 					 class='inline' href="#shoppingCart"><img -->
					<!-- 							src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li> -->
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

					<%-- <li><a href="/SapeStore/welcome?checkMe=${checkMe}">Wishlist</a></li>
					<jsp:include page="Logout.jsp" flush="true" /> --%>
					<li><a id="header_search"
						href="/SapeStore/welcome?checkMe=${checkMe}"><img
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
			<div id="headerccc"
				style="border-style: none none dashed none; border-bottom-width: thin; border-bottom-color: gray;">
				<font size="5"> Customer Registration </font> <br> <br>
			</div>
			<br>
			<form:form name="registerForm" action="/SapeStore/customerRegister"
				method="POST" commandName="profileVO">
				<fieldset></fieldset>
				<table class="wwFormTable">
					<tr>
						<td><label id="name">Full Name<output
									style="color: red; vertical-align: super; font-size: small;">*</output></td>
						<td><input type="text" id="fullName" name="name"></td>
						<td><div id='nameError' style="color: red; font-size: 10px;"></div>
							<form:errors path="name" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label id="preferedLoginName">Preferred Login
								Name</label> <output
								style="color: red; vertical-align: super; font-size: small;">*</td>
						<td><input type="text" id="loginName" name="userId">
							<div id="userIdmessage">
								<div style="color: red; font-size: 10px;">${userIdmessage}</div>
							</div></td>
						<td><input type="button" value="Check Availability"
							onclick="checkuseridavail(loginName.value)" class="lightButton"
							style="background-color: #38ACEC; color: white; font-size: 10px" /></td>
						<td><div id='loginnameError'
								style="color: red; font-size: 10px;"></div> <form:errors
								path="userId" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label id="password">Login Password</label> <output
								style="color: red; vertical-align: super; font-size: small;">*</output></td>
						<td><input type="password" id="loginPassword" name="password"></td>
						<td><div id="passError" style="color: red; font-size: 10px;"></div>
							<form:errors path="password" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label id="repassword">Retype Password</label> <output
								style="color: red; vertical-align: super; font-size: small;">*</output></td>
						<td><input type="password" id="retypePassword"
							name="retypePassword"></td>
						<td><div id="repassError"
								style="color: red; font-size: 10px;"></div> <form:errors
								path="retypePassword" cssClass="error" /></td>
						</td>
					</tr>
					<tr>
						<td><label id="genderId">Gender<output
									style="color: red; vertical-align: super; font-size: small;">*</output></td>
						<td><input type="radio" id="gender" name="genderId" value="1">Male
							<input type="radio" id="gender" name="genderId" value="2">Female
							<input type="radio" id="gender" name="genderId" value="3">Others
							<form:errors path="genderId" cssClass="error" /></td>
						<td><div id='genderError'
								style="color: red; font-size: 10px;"></div></td>

					</tr>
					<tr>
						<td><label id="email_">Email</label> <output
								style="color: red; vertical-align: super; font-size: small;">*</td>
						<td><input type="text" id="email" name="emailAddress">
							<div style="color: red; font-size: 10px;">${emailmessage}</div></td>
						<td><div id='emailError' style="color: red; font-size: 10px;">
							</div> <form:errors path="emailAddress" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label id="address1">Address Line 1</label> <output
								style="color: red; vertical-align: super; font-size: small;">*</td>
						<td><input type="text" id="addressLine1" name="addressLine1">
						</td>
						<td><div id="addressError"
								style="color: red; font-size: 10px;"></div> <form:errors
								path="addressLine1" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label id="address2">Address Line 2</label></td>
						<td><input type="text" id="addressLine2" name="addressLine2">
							<form:errors path="addressLine2" cssClass="error" /></td>
					</tr>
					<tr>
					<tr>
						<td><label id="state_">State</label> <output
								style="color: red; vertical-align: super; font-size: small;">*</output></td>
						<td><select name="stateId" id="statename"
							onchange="populateCity(this.value)">
								<option value="0">Select Your State</option>
								<c:forEach items="${stateList}" var="current">
									<option value="${current.stateId}">${current.stateName}</option>
								</c:forEach>
						</select></td>
						<td><div id="stateError" style="color: red; font-size: 10px;"></div>
							<form:errors path="stateId" cssClass="error" /></td>
					</tr>
					<td><label id="city_">City</label> <output
							style="color: red; vertical-align: super; font-size: small;">*</output></td>
					<td><div id="populateCity">
							<select name="cityId" id="cityname" required="required">
								<option value="0">Select Your City</option>

							</select>
						</div></td>
					<td><div id="cityError" style="color: red; font-size: 10px;"></div>
						<form:errors path="cityId" cssClass="error" /></td>
					</tr>

					<tr>
						<td><label id="zip">Zip Code</label> <output
								style="color: red; vertical-align: super; font-size: small;">*</output></td>
						<td><input type="text" id="zipCode" name="postalCode"
							maxlength="5">
							<div style="color: red; font-size: 10px;">${zipcodemessage}</div></td>
						<td><div id="zipcodeError"
								style="color: red; font-size: 10px;"></div> <form:errors
								path="postalCode" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label id="phNo">Phone Number</label></td>
						<td><input type="text" id="phoneNo" name="phone"
							maxlength="10"></td>
						<td>
							<div id="phoneError" style="color: red; font-size: 10px;"></div>
							<form:errors path="phone" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td><label id="mobNo">Mobile Number</label></td>
						<td><input type="text" id="mobileNo" name="mobileNumber"
							maxlength="10"></td>
						<td>
							<div id="mobileError" style="color: red; font-size: 10px;"></div>
							<form:errors path="mobileNumber" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: left;"><div>
								<%-- <spring:message code="label.createprofile" var="labelSubmit"></spring:message> --%>
								<!-- <input type="submit" value="Create Profile" class="lightButton"
									style="background-color: #38ACEC; color: white; font-size: 20px"> -->
								<input type="button" value="Create Profile" onclick="validate()"
									class="lightButton"
									style="background-color: #38ACEC; color: white; font-size: 20px">
							</div></td>
					</tr>
					<tr>
						<td style="text-align: left; color: red;"><font size="1">*
								Mandatory Fields</font></td>
				</table>

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
	<%-- <div style="display:none">
  <div id="login" class="popup">

  	
<%@include file="login.jsp" %>
  </div>
</div>
 --%>
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
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Rent</title>
<script src="js/rent.js"></script>
<script src="js/vendor/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>

</head>

<style type="text/css">
table.yui { 

border-collapse:collapse; 

border-spacing: 0; 
}
.wwFormTable
{
margin-left:-6px;
}
table.wwFormTable tr{
margin: 0 0 10px 0;
}
reviewBody {
text-align: left;
}
.underlined {
    text-decoration: underline;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="js/vendor/jquery-1.9.1.min.js"></script>


<body>
<div id="something">

<form:form  name="rentform"  method="GET" >

<table class="wwFormTable" >

	<fieldset>
	 <tr >
   		 <td class="tdLabel"></td>
   		 <td ><input type="radio"  name="scheme"   value="daily" /><label id="day" style="font-size: 16px;font-weight: bold">Daily Scheme</label></td>    	    	
   		 <td ><input type="radio"  name="scheme"  value="weekly" /><label id="week" style="font-size: 16px;font-weight: bold">Weekly Scheme</label></td>   
   		 <td ><input type="radio"  name="scheme"  value="monthly" /><label id="month" style="font-size: 16px;font-weight: bold">Monthly Scheme</label></td>    	
   	 
	 </tr>
  
  </table>
 </form:form>

 <div class="red box" ><label style="font-size: 16px;font-weight: bold">
No. of Days</label><input type="text" id="days" />
<input type="button" onclick="validatedays()" value="Go"> <br><br>
<strike><div id="id1" align="right" style="font-weight: 400;"></div></strike> <br><br>
<div id="totalcostdays" align="right" style="font-weight: 500;"> </div><br><br>

<div id="id2" align="right" style="font-weight: bold;"></div> <br>

<div id="priceDays" align="center">
<input type="button" value="Confirm" id="save1"  onClick="doAjax()" style="display:none;"/> 
</div>
</div>


<div class="green box"><label style="font-size: 16px;font-weight: bold">
No of weeks </label> <input type="text" id="weeks" />
<input type="button" onclick="validateweeks()" value="Go"> <br><br>
<strike><div id="id3" align="right" style="font-weight: 400;"></div></strike> <br><br>
<div id="totalcostweeks" align="right"> </div><br><br>
<div id="id4" align="right" style="font-weight: bold;"></div> <br>

<div id="priceWeeks" align="center">
<input type="button" value="Confirm" id="save2" onClick="doAjax()" style="display:none;"/> 
</div>
</div>

 <div class="blue box">
 <label style="font-size: 16px;font-weight: bold">
No of months </label> <input type="text" id="months" />
<input type="button" onclick="validatemonths()" value="Go"> <br><br>
<strike><div id="id5" align="right" style="font-weight: 400;"></div></strike> <br><br>

<div id="totalcostmonths" align="right"> </div><br><br>
<div id="id6" align="right" style="font-weight: bold;"></div> <br>

<div id="priceMonths" align="center">

<input type="button" value="Confirm" id="save3" onClick="doAjax()" style="display:none;"/>

</div>
 </div>
<p id="bookprice" style="display:none;">${book.bookPrice}</p>
<div id="container"></div>
 </div>
 

</body>
</html>

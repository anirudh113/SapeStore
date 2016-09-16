<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
.popup
{
padding:20px;
}
<head>
<%@ include file="../header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingCart</title>
 <script>
      function callDisplay() {
    	  modalWin.ShowURL('DisplayShoppingCart.htm'); 
//     	  var w1 = window.open('DisplayShoppingCart.jsp','wind1');  
//     	  w1.location.href='http://www.google.com';
	} 
  </script> 
</head>
<body onload="callDisplay();">

<%@ include file="../footer.jsp"%>

</body>
</html>
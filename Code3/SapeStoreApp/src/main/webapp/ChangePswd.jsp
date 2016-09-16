<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Change Password Form</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<%@ include file="../header.jsp"%>

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="css/normalize.css" type="text/css">
<link rel="stylesheet" href="css/main.css" type="text/css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>
<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]--> 

        

<!-- Add your site or application content here -->
<div id="wrapper"> 
  <!-- header starts-->
  <header>
    <div id="header"> <a href="sapestore.com" title="SapeStore" class="logo"><img src="img/logo.jpg" width="231" height="109" alt="SapeStore"></a>
      <nav>
        <ul class="nav">
         
        </ul>
      </nav>
    </div>
  </header>
  <!-- header ends -->
  <script type="text/javascript">
		
	     
	    function checkpassword(){
			

	    	var flag=true;
	   	 var x = document.forms["ChangeMyform"]["newpassword"].value;
	   	 var y = document.forms["ChangeMyform"]["confirmpassword"].value;
	   	 
	   	if(x.length<6){
			flag=false;
			document.getElementById('rePassLength').innerHTML="invalid password length ";
			}
	
	    else if(x!=y){
	   		 flag=false;
	   		 document.getElementById('repass').innerHTML="password does not match";
	   	 }
	   	 return flag;
	   	 
	   	/* if(flag=="true")
	   	document.ChangeMyform.submit(); */
	   }

		 
				
		/*  function validatePasswordForm() {
			var flag = [];
			var finalflag=true;
			 
			 flag[0]=checkPasswordSize();
			 flag[1]=checkpassword();
			 flag[2]=checkAddress();
			 
			 var i;
			 for(i=0;i<flag.length;i++){
				 if(!flag[i]){
					 	
					 return false;
				 }
			 }
			 document.ChangeMyform.action = "/SapeStore/ChangePswd";
				      
				    document.ChangeMyform.submit();             
				    return true;
			 
		} */
		 
		 
	</script>
	
	<div id="mainContent">
			<h2>
				<label> <strong>Hi ${username}</strong></label>
			</h2>
			<br>
	
		<div id="mainContent">
			<h2>
				<label> <strong>Change Password</strong></label>
			</h2>
			<br>
			<hr style="border-top: dotted 1px;" />

			<div class="clearfix"></div>

			 <form:form  id="ChangeMyform" name="ChangePswd" method="POST" onsubmit="return checkpassword()" action="changepassword"  >
				<table id="tablepaging" class="yui"
					style="width: 650px; height: 91px; border-bottom-width: 0px">
					
					
					<tr>
						<td><label> <strong>Enter New-Password</strong></label><font color="red">*</font>
						</td>
						<td><input id="tx" type="password" name="newpassword" 
							  title="Six or more characters" placeholder="Six or more characters" />
							  <div id="rePassLength" style="color: red; font-size: 2"></div>
							 <form:errors path="password"
											cssClass="error" />
						</td>
					</tr>
					<tr>
						<td><label> <strong>Confirm New-Password</strong></label> <font color="red">*</font>
						</td>
						<td><input id="tx" type="password" name="confirmpassword" placeholder="Retype Password" onblur="checkpassword()"/>
						<div id="repass" style="color: red; font-size: 2"></div></td>
						<form:errors path="password"
											cssClass="error" />
					</tr>
					
				
				<br>
				<div style="width: 200px; margin-left: auto; margin-right: auto;"> 
				<tr><td>
					<input id="but" name="but" type="submit"  value="Update Password"  ></td>
					</tr>
					</table>
				</div> 
			</form:form> 
  
   
  
 
  <div class="clearfix"></div>
  <footer>
  			<div id="footer">
		     	<div style=" float: left; margin-left: 386px;">
		     		<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact Us</a>
		       	</div>
		       	<div style="float: left;margin-left: 6px; color: #21addd"> | </div>
		       	
		       	<div style="float: left;margin-left: 7px;">
		 			<a href="/SapeStore//policyCustomer" style="color: #21addd;">Policy</a>
		  		</div>
		  		<div>Powered by <img src="img/sapient_nitro.jpg" width="78" height="14" alt="sapient nitro"></div>
		  	</div>
  
  </footer>
</div>

<%@ include file="../footer.jsp"%>

</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review and Comment</title>
</head>
<style type="text/css">
	 .rating {
    overflow: hidden;
    display: inline-block;
    font-size: 0;
    position: relative;
}
.rating-input {
    float: right;
    width: 16px;
    height: 16px;
    padding: 0;
    margin: 0 0 0 -16px;
    opacity: 0;
}
.rating:hover .rating-star:hover,
.rating:hover .rating-star:hover ~ .rating-star,
.rating-input:checked ~ .rating-star {
    background-position: 0 0;
}
.rating-star,
.rating:hover .rating-star {
    position: relative;
    float: right;
    display: block;
    width: 16px;
    height: 16px;
    background: url('img/star.png') 0 -16px;
}
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
<body>
<span id="perror" style="display:none"></span>
</script>
<form:form id="review" name="reviewform" action="/SapeStore/addReviewController"  method="POST" >
<table class="wwFormTable">
	
   <fieldset>
	 <tr>
    	<td class="tdLabel"></td>
    	<td><label id="reviewHead" style="font-size: 16px;font-weight: bold">Write a Review</label></td>   	
	  </tr>
     <tr>
   		 <td class="tdLabel"></td>
   		 <td rowspan="2"><figure><img src="${book.bookThumbImage}" width="150" height="180" alt="image" align="middle"/></figure></td>
   		 <td><label id="reviewBody" style="font-size: 15px">Your Comment:   </label></td>    	
	 </tr>
	 
     <tr>
    	 <td class="tdLabel"></td>    	     	 
      	 <td colspan="3"><textarea rows="9" cols="50" name="bookComments" id="comment" required></textarea>
      	 </td>
	 </tr>	 
	 <tr>
    	<td class="tdLabel"></td>
    	<td rowspan="2"><label id="title" style="font-size: 16px;font-weight: bold">${book.bookTitle }<br>${book.bookAuthor }</label></td>
    	<td rowspan="2"><label id="rating" style="font-size: 15px">Your Rating:           </label><br>    	
    	<span class="rating" id="rate">
        	<input type="radio" class="rating-input"
    			id="rating-input-1-5" name="rating-input-1" value="5"  required/>
        	<label for="rating-input-1-5" class="rating-star"></label>
        	<input type="radio" class="rating-input"
                id="rating-input-1-4" name="rating-input-1" value="4" required/>
        	<label for="rating-input-1-4" class="rating-star" ></label>
        	<input type="radio" class="rating-input"
                id="rating-input-1-3" name="rating-input-1" value="3" required/>
        	<label for="rating-input-1-3" class="rating-star"></label>
        	<input type="radio" class="rating-input"
                id="rating-input-1-2" name="rating-input-1" value="2" required/>
       		<label for="rating-input-1-2" class="rating-star"></label>
        	<input type="radio" class="rating-input"
                id="rating-input-1-1" name="rating-input-1" value="1" required/>
        	<label for="rating-input-1-1" class="rating-star"></label>
		</span>		
    	<td><div align="right"><input type="submit" value="Post Comment" class="lightButton" id="submitButton"/>    	
	 </tr>	   
    </fieldset>
  </table>
  </form:form>
  
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<select name="cityId" id="cityname" required=""
		onchange="populate(this.value)">
		<option value="0">Select Your City</option>
		<c:forEach var="city" items="${cities}">
			<c:choose>
				<c:when test="${city.cityId == cityNa}">
					<option value="${city.cityId }" selected="selected">${city.cityName}</option>
				</c:when>
				<c:otherwise>
					<option value="${city.cityId }">${city.cityName}</option>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</select>
</body>
</html>
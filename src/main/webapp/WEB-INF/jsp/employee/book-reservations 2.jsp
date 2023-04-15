<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Book Reservations</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Customer Reservations</h2>
<c:if test="${books.size() gt 0}">
<table id="tablestyle" align="center" border="1" cellpadding="1" cellspacing="1">
	<th>ISBN</th>
	<th>Title</th>
	<th>Author</th>
	<th>Reserved From</th>
	<th>Reserved Until</th>
	<th>Return Date</th>
	<th>Reserved By</th>
	<th>Action</th>
	
	<c:forEach items="${books}" var="book">
	<tr>
		<td>${book.key.isbn}</td>
		<td>${book.key.title}</td>
		<td>${book.key.author}</td>
		<td>${book.key.bookingStartDate}</td>
		<td>${book.key.bookingEndDate}</td>
		<td>${book.key.returnDate}</td>
		<td>${book.value}</td>
		<td>	
 		<a href="confirm-pickup.htm?bookId=${book.key.bookId}&username=${book.value}">Pickup By Customer</a>
		</td>
	</tr>
	</c:forEach>

</table>
</c:if>
</body>
</html>
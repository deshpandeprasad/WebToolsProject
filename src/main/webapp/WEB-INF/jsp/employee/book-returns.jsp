<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Returns</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Book Returns</h2>
<c:choose>
<c:when test="${books.size() gt 0}">
<table id="tablestyle" border="1" cellpadding="1" cellspacing="1" align="center">
	<th></th>
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
		<td><img width="100" height="100" src="/lib2/images/${book.key.imagePath}"/></td>
		<td>${book.key.isbn}</td>
		<td>${book.key.title}</td>
		<td>${book.key.author}</td>
		<td>${book.key.bookingStartDate}</td>
		<td>${book.key.bookingEndDate}</td>
		<td>${book.key.returnDate}</td>
		<td>${book.value}</td>
		<td>	
 		<a href="confirm-return.htm?bookId=${book.key.bookId}&username=${book.value}">Returned By Customer</a>
		</td>
	</tr>
	</c:forEach>

</table>
</c:when>
<c:otherwise><p align="center">There are no books currently in use.</p></c:otherwise>
</c:choose>
</body>
</html>
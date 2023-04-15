<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<meta charset="UTF-8">
<title>Browse Books</title>
</head>
<body>
<jsp:include page="customer-menu.jsp"/>
<h2 align="center">Browse Books</h2>
<c:if test="${userbooks.size() eq 3}">
<p style="color:red" align="center">Cannot reserve more books since you already have 3 books reserved/in use.</p>
</c:if>
<c:choose>
<c:when test="${books.size() gt 0}">
<table id="tablestyle" border="1" cellpadding="1" cellspacing="1" align="center">
	<th></th>
	<th>ISBN</th>
	<th>Title</th>
	<th>Author</th>
	<th>Return Date</th>
	<th>Reserved Until</th>
	<th>Action</th>
	
	
	<c:forEach items="${books}" var="book">
	<tr>
		<td><img width="100" height="100" src="/lib2/images/${book.imagePath}"/></td>
		<td>${book.isbn}</td>
		<td>${book.title}</td>
		<td>${book.author}</td>
		<td>${book.returnDate}</td>
		<td>${book.bookingEndDate}</td>
		<td>	
 		<c:choose>
		<c:when test= "${book.bookingEndDate != null || book.returnDate !=null}">Reserved</c:when>
		<c:when test="${userbooks.size() eq 3}">
		</c:when>
		<c:otherwise>
		<a href="confirm-reservation.htm?bookId=${book.bookId}&username=${sessionScope.username}">Select</a>
		</c:otherwise>
		</c:choose>
		</td>
	</tr>
	</c:forEach>
</table>
</c:when>
	<c:otherwise>
	<p align="center">There are no books available.</p>
	</c:otherwise>
</c:choose>
</body>
</html>
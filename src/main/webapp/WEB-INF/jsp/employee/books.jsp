<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Books</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Books</h2>
<p align="center"><a href="add-books.htm">Add Books</a></p>
<br><br>
<c:choose>
<c:when test="${books.size() gt 0}">
<table id="tablestyle" align="center" border="1" cellpadding="1" cellspacing="1">
	<th></th>
	<th>ISBN</th>
	<th>Title</th>
	<th>Author</th>
	<th>Reserved From</th>
	<th>Reserved Until</th>
	<th>Return By</th>
	<th>Reserved By</th>
	<th>In Use By</th>
	<th>Action</th>
	

	<c:forEach items="${books}" var="book">
	<tr>
		<td><img width="100" height="100" src="/lib2/images/${book.imagePath}"/></td>
		<td>${book.isbn}</td>
		<td>${book.title}</td>
		<td>${book.author}</td>
		<td>${book.bookingStartDate}</td>
		<td>${book.bookingEndDate}</td>
		<td>${book.returnDate}</td>
		<td>${book.getReservedByUser().getUsername()}</td>
		<td>${book.getTheUser().getUsername()}</td>
		<td><a href="confirm-edit.htm?bookId=${book.bookId}">Edit</a>
		<a href="confirm-delete.htm?bookId=${book.bookId}">Delete</a>
		</td>
	</tr>
	</c:forEach>
	

</table>
</c:when>
<c:otherwise><p align="center">There are no books in the catalog. Click Add Books to add books.</c:otherwise>
</c:choose>
</body>
</html>
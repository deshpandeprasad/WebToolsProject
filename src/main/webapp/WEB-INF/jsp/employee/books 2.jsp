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
<c:if test="${books.size() gt 0}">
<table id="tablestyle" align="center" border="1" cellpadding="1" cellspacing="1">
	<th>Image</th>
	<th>ISBN</th>
	<th>Title</th>
	<th>Author</th>
	<th>Action</th>
	

	<c:forEach items="${books}" var="book">
	<tr>
		<td><img align="middle" width="100" height="100" src="/lib/images/${book.imagePath}"/></td>
		<td>${book.isbn}</td>
		<td>${book.title}</td>
		<td>${book.author}</td>
		<td><a href="confirm-edit.htm?bookId=${book.bookId}">Edit</a>
		<a href="confirm-delete.htm?bookId=${book.bookId}">Delete</a>
		</td>
	</tr>
	</c:forEach>
	

</table>
</c:if>
</body>
</html>
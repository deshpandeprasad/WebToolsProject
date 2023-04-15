<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Confirm Delete</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Confirm Delete</h2>

<table id="tablestyle" cellpadding="1" cellspacing="1" align="center">
	<tr>
	<td></td>
	<td><img align="middle" width="100" height="100" src="/lib/images/${book.imagePath}"/></td>
	<tr>
	<td>ISBN: </td>
	<td>${book.getIsbn()}</td>
	</tr>
	<tr>
	<td>Title: </td>
	<td>${book.getTitle()}</td>
	</tr>
	<tr>
	<td>Author: </td>
	<td>${book.getAuthor()}</td>
	</tr>
	<tr>
	<td>Reserved From: </td>
	<td>${book.getBookingStartDate()}</td>
	</tr>
	<tr>
	<td>Reserved Until: </td>
	<td>${book.getBookingEndDate()}</td>
	</tr>
	<tr>
	<td>Return Date: </td>
	<td>${book.getReturnDate()}</td>
	</tr>
	<tr>
	<td>Reserved By: </td>
	<td>${book.getReservedByUser().getUsername()}</td>
	</tr>
	<tr>
	<td>Current User: </td>
	<td>${book.getTheUser().getUsername()}</td>
	</tr>
</table>
<form action="confirm-delete.htm" method="POST">
	<input type="hidden" name="id" value="${book.getBookId()}"/>
    <input type="hidden" name="isbn" value="${book.getIsbn()}"/>
	<input type="hidden" name="title" value="${book.getTitle()}"/>
	<input type="hidden" name="author" value="${book.getAuthor()}"/>
	<input type="hidden" name="bookingStartDate" value="${book.getBookingStartDate()}"/>
	<input type="hidden" name="bookingEndDate" value="${book.getBookingEndDate()}"/>
	<input type="hidden" name="returnDate" value="${book.getReturnDate()}"/>
	<input type="hidden" name="username" value="${book.getReservedByUser().getUsername()}"/>
<p align="center"><input type="submit" value="Confirm"></p>  
</form>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Confirm Edit</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Edit Book Details</h2>

<%-- <table cellpadding="1" cellspacing="1">
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
	<td>${book.getTitle()}</td>
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
</table> --%>


<%-- <form style="text-align:left" action="confirm-edit.htm" method="POST">
	<input type="hidden" name="id" value="${book.getBookId()}"/>
    ISBN: ${book.getIsbn()}
    <br><br>
	Title: <input type="text" name="title" value="${book.getTitle()}" style="font-weight: bold"/>
	<br><br>
	Author: <input type="text" name="author" value="${book.getAuthor()}"style="font-weight: bold"/>
	<br><br>
	Reserved From: ${book.getBookingStartDate()}
	<br><br>
	Reserved Until: ${book.getBookingEndDate()}
	<br><br>
	Return By: ${book.getReturnDate()}
	<br><br>
	Reserved By: ${book.getReservedByUser().getUsername()}
	<input type="checkbox" id="removeReservation" name="removeReservation" value="removeReservation"> Remove Reservation
	<br><br>
	Current User: ${book.getTheUser().getUsername()}
	<input type="checkbox" id="removeCurrentUser" name="removeCurrentUser" value="removeCurrentUser"> Remove Current User
	<br><br>
	<input type="submit" value="Edit"> 
</form>
 --%>

<form style="text-align:left" action="confirm-edit.htm" method="POST">
<input type="hidden" name="id" value="${book.getBookId()}"/>
<table id="tablestyle" cellpadding="1" cellspacing="1" align="center">
	<tr><td>ISBN</td><td>${book.getIsbn()}</td></tr>
	<tr><td>Title:</td><td><input type="text" name="title" value="${book.getTitle()}" style="font-weight: bold" required="required"/></td></tr>
	<tr><td>Author:</td><td><input type="text" name="author" value="${book.getAuthor()}"style="font-weight: bold" required="required"/></td></tr>
	<tr><td>Reserved From:</td><td>${book.getBookingStartDate()}</td></tr>
	<tr><td>Reserved Until:</td><td>${book.getBookingEndDate()}</td></tr>
	<tr><td>Return By:</td><td>${book.getReturnDate()}</td></tr>
	<tr><td>Reserved By:</td><td>${book.getReservedByUser().getUsername()}</td>
	<td><input type="checkbox" id="removeReservation" name="removeReservation" value="removeReservation"> Remove Reservation</td>
	</tr>
	<tr><td>Current User:</td><td>${book.getTheUser().getUsername()}</td>
	<td><input type="checkbox" id="removeCurrentUser" name="removeCurrentUser" value="removeCurrentUser"> Remove Current User</td>
	</tr>
	<tr>
	<td>
	<input style="width:100px"  type="submit" value="Edit"> 
	</td>
	</tr>
</table>
</form>
 



</body>
</html>
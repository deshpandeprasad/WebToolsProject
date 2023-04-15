<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<meta charset="UTF-8">
<title>Confirm Edit</title>
</head>
<body>
<jsp:include page="admin-menu.jsp"/>
<h3 align="center">Edit User Details</h3>
<form action="user-edit.htm" method="POST">
<input type="hidden" name="id" value="${user.getUserid()}"/>
<table id="tablestyle" cellpadding="1" cellspacing="1" align="center">
	<tr>
	<td>Username:</td>
	<td>${user.getUsername()}</td>
	</tr>
	<tr>
	<td>Name:</td>
	<td>${user.getName()}</td>
	</tr>
	<tr>
	<td>Address:</td>
<%-- 	<td><form:input type="text" path="address" value="${user.getAddress()}" style="font-weight: bold" required="required"/></td> --%>
	<td><input type="text" name="address" value="${user.getAddress()}" style="font-weight: bold" required="required"/>
	<p style="color:red">${addressErr}</p>
	</td>
	</tr>
	<tr>
	<td>Contact: (10 digits)</td>
	<td><input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" maxlength="10" name="contact" value="${user.getContact()}" style="font-weight: bold" required="required"/>
	<p style="color:red">${contactErr}</p>
	</td>
	</tr>
	<tr>
	<td>Role:</td>
	<td>${user.getRole()}</td>
	</tr>
</table>
<br>
	<p align="center"><input style="width:100px"  type="submit" value="Edit"></p>
</form>
</body>
</html>
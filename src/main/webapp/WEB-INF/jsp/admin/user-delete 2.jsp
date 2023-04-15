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
<jsp:include page="admin-menu.jsp"/>
<h2 align="center">Delete User Details</h2>
<br>
<form style="text-align:center" action="user-delete.htm" method="POST">
<input type="hidden" name="id" value="${user.userid}"/>
<table id="tablestyle" align="center">
	<tr><td>Username:</td><td>${user.username}</td></tr>
	<tr><td>Name:</td><td>${user.name}</td></tr>
	<tr><td>Address:</td><td>${user.address}</td></tr>
	<tr><td>Contact:</td><td>${user.contact}</td></tr>
	<tr><td>Role:</td><td>${user.role}</td></tr>
</table>
<input type="hidden" name="username" value="${user.username}"/>
<input type="hidden" name="name" value="${user.name}"/>
<input type="hidden" name="password" value="${user.password}"/>
<input type="hidden" name="address" value="${user.address}"/>
<input type="hidden" name="contact" value="${user.contact}"/>
<input type="hidden" name="role" value="${user.role}"/>
<br>
<input style="width:100px"  type="submit" value="Delete"> 
</form>
</body>
</html>
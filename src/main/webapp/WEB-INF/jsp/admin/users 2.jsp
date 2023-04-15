<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Users</title>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
</head>
<body>
<jsp:include page="admin-menu.jsp"/>
<h2 align="center">All Users</h2>
<br>
<table id="tablestyle" border="1" cellpadding="1" cellspacing="1" align="center">
	<th>ID</th>
	<th>Username</th>
	<th>Name</th>
	<th>Address</th>
	<th>Contact</th>
	<th>Role</th>
	<th>Action</th>
	
	<c:forEach items="${users}" var="user">
	<c:if test="${user.role ne 'admin'}">
	<tr>
		<td>${user.userid}</td>
		<td>${user.username}</td>
		<td>${user.name}</td>
		<td>${user.address}</td>
		<td>${user.contact}</td>
		<td>${user.role}</td>
		<td><a href="user-edit.htm?userId=${user.userid}">Edit</a> | 
		<a href="user-delete.htm?userId=${user.userid}">Delete</a>
		</td>
	</tr>
	</c:if>
	</c:forEach>

</table>

</body>
</html>
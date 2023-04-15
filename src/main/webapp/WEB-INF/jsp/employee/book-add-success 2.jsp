<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Add Success</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h3 align="center">Book added successfully</h3>
<br><br>
<p align="center"><a href="add-books.htm?username=${sessionScope.username}">Add another book</a></p></body>
</body>
</html>
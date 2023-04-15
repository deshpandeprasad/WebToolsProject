<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Add Book</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Add Book</h2>
<p align="center" style="color:red">${isbnExistsErr}</p>
<%-- <form:form modelAttribute="book" method="post">
	ISBN: <form:input path="isbn" required="required" minlength="10" maxlength="10"/>
	<br><br>
	Book Title: <form:input path="title" required="required"/>
	<br><br>
	Book Author: <form:input path="author" required="required"/>
	
	<input type="submit" value="Add"/>

</form:form> --%>

<form:form modelAttribute="book" method="post" enctype="multipart/form-data">
<table id="tablestyle" align="center" cellpadding="1" cellspacing="1">
	<tr><td>
	ISBN: 
	</td><td>
	<form:input path="isbn" required="required" minlength="10" maxlength="10"/>
	</td></tr>
	<tr><td>
	Book Title: 
	</td><td>
	<form:input path="title" required="required"/>
	</td></tr>
	<tr><td>
	Book Author: 
	</td><td>
	<form:input path="author" required="required"/>
	</td></tr>
	<tr><td>Select Item Photo:</td>
	<td><input type="file" name="imageFile" accept="image/jpeg"required="required" /> <font color="red"><%-- <form:errors path="user"/> --%></font></td>
</table>	
	<p align="center"><input type="submit" value="Add"/></p>

</form:form>

</body>
</html>
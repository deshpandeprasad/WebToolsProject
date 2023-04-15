<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
</head>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em"> 
    <a style="color:white;text-decoration:none" href="admin-dashboard.htm?username=${sessionScope.username}">Home</a> |
   	<a style="color:white;text-decoration:none" href="users.htm?username=${sessionScope.username}">Manage Users</a>
     <a style="color:white;text-decoration:none;float:right" href="logout.htm?username=${sessionScope.username}">Logout</a> 
     <label style="float:right">Hi ${sessionScope.username} |</label>
     
</div>
</html>
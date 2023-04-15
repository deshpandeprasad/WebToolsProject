<style><%@include file="/WEB-INF/css/layout.css"%></style>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">   
    <a style="color:white;text-decoration:none" href="customer-dashboard.htm?username=${sessionScope.username}">Home</a> |
   	<a style="color:white;text-decoration:none" href="browse-books.htm?username=${sessionScope.username}">Browse</a> | 
    <a style="color:white;text-decoration:none" href="my-books.htm?username=${sessionScope.username}">My Books</a> | 
    <a style="color:white;text-decoration:none" href="my-reservations.htm?username=${sessionScope.username}">My Reservations</a>
     <a style="color:white;text-decoration:none;float:right" href="logout.htm?username=${sessionScope.username}">Logout</a> 
     <label style="float:right">Hi ${sessionScope.username} |</label>
     
</div>
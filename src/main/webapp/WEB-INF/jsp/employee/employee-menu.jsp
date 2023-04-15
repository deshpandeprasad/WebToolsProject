<style><%@include file="/WEB-INF/css/layout.css"%></style>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">
<%--     Hi ${sessionScope.username} |  --%>
    <a style="color:white;text-decoration:none" href="employee-dashboard.htm?username=${sessionScope.username}">Home</a> | 
    <a style="color:white;text-decoration:none" href = "books.htm?username=${sessionScope.username}">Books</a> |
    <a style="color:white;text-decoration:none" href = "book-reservations.htm?username=${sessionScope.username}">Reservations</a> | 
    <a style="color:white;text-decoration:none" href = "book-returns.htm?username=${sessionScope.username}">Returns</a>
    <a style="color:white;text-decoration:none;float:right" href="logout.htm?username=${sessionScope.username}">Logout</a>
    <label style="float:right">Hi ${sessionScope.username} |</label>
</div>
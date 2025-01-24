<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="./static/css/style.css">
</head>
<body>
<div class="container">
    <h1>Welcome, <%= username %>!</h1>
    <nav class="menu">
        <ul>
            <li><a href="hello-servlet">Hello Servlet</a></li>
            <li><a href="keiba_welcome.jsp">馬券ジェネレーター</a></li>
        </ul>
    </nav>
</div>

</body>
</html>

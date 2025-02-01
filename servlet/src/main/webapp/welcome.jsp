<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    String email = (String) session.getAttribute("email");
    String role = (String) session.getAttribute("role");

    if (email == null) {
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
    <h1>Welcome, <%= email %>!</h1>
    <nav class="menu">
        <ul>
            <li><a href="hello-servlet">Hello Servlet</a></li>
            <li><a href="keiba_welcome.jsp">馬券ジェネレーター</a></li>
            <li><a href="syogi.jsp">将棋</a></li>
            <li><a href="playlist.jsp">プレイリスト</a></li>
            <li><a href="logout.jsp">Logout</a></li>
            <% if ("ADMIN".equals(role)) { %>
            <li><a href="admin/users">User List</a></li>
            <% } %>
        </ul>
    </nav>
</div>
</body>
</html>

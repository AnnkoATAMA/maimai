<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String email = (String) session.getAttribute("email");
  if (email == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>User List</title>
  <link rel="stylesheet" type="text/css" href="../static/css/users.css">
</head>
<body>
<div class="container">
  <h1>Admin - User List</h1>
  <table border="1">
    <tr>
      <th>Email</th>
    </tr>
    <%
      List<String> users = (List<String>) request.getAttribute("users");
      if (users != null) {
        for (String user : users) {
    %>
    <tr>
      <td><%= user %></td>
    </tr>
    <%
        }
      }
    %>
  </table>
</div>
</body>
</html>
